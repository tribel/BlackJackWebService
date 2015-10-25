package gamelogic;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tribel.entity.Card;
import com.tribel.gamelogic.PlayerService;


@ContextConfiguration("/beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
public class TestPlayerService {

	@Inject
	private PlayerService<Card> playerService;
	
	
	@Test
	public void testGetCards() {
		assertNotNull(playerService.getPlayerCards());
		assertNotNull(playerService.getDealerCards());
	}


	@Test
	public void testDeal() {
		playerService.deal();
		assertEquals(playerService.getPlayerCards().size(), 2);
		assertEquals(playerService.getDealerCards().size(), 2);
		List<Card> testList = playerService.getPlayerCards();
		testList.addAll(playerService.getDealerCards());
		assertArrayEquals(playerService.getCardsOnHands().toArray(), testList.toArray());
	}

	@Test
	public void testHit() {
		playerService.hit();
		assertEquals(3, playerService.getPlayerCards().size());
		
	}


	@Test
	public void testClearDeck() {
		playerService.clearDeck();
		assertEquals(0, playerService.getDealerCards().size());
		assertEquals(0, playerService.getPlayerCards().size());
		assertEquals(0, playerService.getCardsOnHands().size());
	}

}
