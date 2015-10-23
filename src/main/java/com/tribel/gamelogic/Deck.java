package com.tribel.gamelogic;

import java.util.Deque;

import com.tribel.entity.Card;

public interface Deck<T extends Card> {
	
	public void deckInit();
	
	public Deque<T> getDeckCards();
	
	public void clearDeck();
	 
}
