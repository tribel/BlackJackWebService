package com.tribel.gamelogic;

import java.util.List;
import com.tribel.entity.Card;


public interface PlayerService<T extends Card> {

	public static final int WIN = 2;

	public static final int LOSE = -1;

	public static final int PUSH = 1;
	
	public static final int CONTINUE = 0;
	
	public List<T> getPlayerCards();
	
	public List<T> getDealerCards();
	
	public List<T> getCardsOnHands();

	public int deal();

	public int hit();

	public int stand();

	public void clearDeck();
}
