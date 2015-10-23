package com.tribel.gamelogic;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

import javax.inject.Named;

import com.tribel.entity.Card;
import com.tribel.entity.Rank;
import com.tribel.entity.Suit;

@Named
public class DeckImpl implements Deck<Card>{
	
	private LinkedList<Card> deckList; 
	
	public DeckImpl() {
		deckList = new LinkedList<>();
	}
	
	@Override
	public void deckInit() {
		for(Suit s: Suit.values()) {
			for(Rank r: Rank.values()) {
				deckList.add(new Card(s, r));
			}
		}
		
		Collections.shuffle(deckList);
	}

	@Override
	public Deque<Card> getDeckCards() {
		return deckList;
	}
	
	@Override
	public void clearDeck() {
		deckList.clear();
	}

}
