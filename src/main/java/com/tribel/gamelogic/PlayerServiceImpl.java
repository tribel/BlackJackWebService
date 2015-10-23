package com.tribel.gamelogic;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.tribel.entity.Card;


@Named
public class PlayerServiceImpl implements PlayerService<Card>{

	@Inject
	private Deck<Card> deck;
	
	private List<Card> playerCards = new ArrayList<>();
	private List<Card> dealerCards = new ArrayList<>();
	private List<Card> onHendsCards; 
	
	private Integer playerCardSum;
	private Integer dealerCardSum;
	
	public static final int BLACK_JACK = 21;
	
	public PlayerServiceImpl() {
	}
	
	@Override
	public List<Card> getPlayerCards() {
		return playerCards;
	}

	@Override
	public List<Card> getDealerCards() {
		return dealerCards;
	}
	
	@Override
	public List<Card> getCardsOnHands() {
		return onHendsCards;
	}
	
	@Override
	public int deal() {
		deck.deckInit();
		
		for(int i = 0; i < 2; i++) {
			playerCards.add(deck.getDeckCards().pollFirst());
			dealerCards.add(deck.getDeckCards().pollFirst());
		}	
		onHendsCards = new ArrayList<>();
		onHendsCards.addAll(playerCards);
		onHendsCards.addAll(dealerCards);
		
		if(checkWinner() != 0 && playerCardSum == BLACK_JACK) return WIN;
		else if (checkWinner() == 0 && playerCardSum == BLACK_JACK) return PUSH;
		else if (checkWinner() < 0 && dealerCardSum == BLACK_JACK) return LOSE;
		else return CONTINUE;
	}

	@Override
	public int hit() {
		playerCards.add(deck.getDeckCards().pollFirst());
		checkWinner();
		
		if(playerCardSum > BLACK_JACK) return LOSE;
		else if(playerCardSum == BLACK_JACK && dealerMove() == 1) return PUSH;
		else if(playerCardSum == BLACK_JACK && dealerMove() != 1) return WIN;
		else return CONTINUE;
	}

	@Override
	public int stand() {
		int dealerHand = dealerMove();
		
		if(dealerHand == -1) return WIN;
		else if(dealerHand == 0 && playerCardSum > dealerCardSum) return WIN;
		else if(dealerHand == 0 && playerCardSum == dealerCardSum) return PUSH;
		else return LOSE;
	 
	}

	protected int checkWinner() {
		playerCardSum = playerCards.stream().mapToInt((x) -> x.getRank().getValue()).sum();
		dealerCardSum = dealerCards.stream().mapToInt((x) -> x.getRank().getValue()).sum();
		return playerCardSum.compareTo(dealerCardSum);
	}
	
	protected int dealerMove() {
		while(dealerCardSum <= 17) {
			dealerCards.add(deck.getDeckCards().pollFirst());
			checkWinner();
		}
		
		if(dealerCardSum == BLACK_JACK) return 1;
		else if(dealerCardSum > 21) return -1;
		else return 0;
 	}
	
	@Override
	public void clearDeck() {
		deck.clearDeck();
		onHendsCards.clear();
		playerCards.clear();
		dealerCards.clear();
	}



}
