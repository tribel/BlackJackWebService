package gamelogic;

import static org.junit.Assert.*;

import java.util.Deque;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tribel.entity.Card;
import com.tribel.gamelogic.Deck;

@ContextConfiguration("/beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
public class TestDeckImpl {

	@Inject
	private Deck<Card> deck;
	
	@Test
	public void testDeckInit() {
		deck.deckInit();
		Deque<Card> testList =	deck.getDeckCards();
		assertNotNull(testList);
		assertNotEquals(0, testList.size());
	}


	@Test
	public void testClearDeck() {
		deck.clearDeck();
		Deque<Card> testList =	deck.getDeckCards();
		assertEquals(0, testList.size());
	}

}
