package com.tribel.gamelogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.tribel.entity.Card;
import com.tribel.entity.Rank;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Named
public class PlayerServiceImpl implements PlayerService<Card>{

	@Inject
	private Deck<Card> deck;
	
	@XmlElementWrapper
	@XmlElement
	private List<Card> playerCards;
	@XmlElementWrapper
	@XmlElement
	private List<Card> dealerCards;
	@XmlElementWrapper
	@XmlElement
	private List<Card> onHandsCards; 
	@XmlElement
	private Integer playerCardSum;
	@XmlElement
	private Integer dealerCardSum;
	
	public static final int BLACK_JACK = 21;
	
	public PlayerServiceImpl() {
		playerCards = new ArrayList<>();
		dealerCards = new ArrayList<>();
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
		return onHandsCards;
	}
	
	@Override
	public int deal() {
		deck.deckInit();
		
		for(int i = 0; i < 2; i++) {
			playerCards.add(deck.getDeckCards().pollFirst());
			dealerCards.add(deck.getDeckCards().pollFirst());
		}	
		onHandsCards = new ArrayList<>();
		onHandsCards.addAll(playerCards);
		onHandsCards.addAll(dealerCards);
		
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
		playerCardSum = aceRankCounting(playerCards, playerCardSum);
		dealerCardSum = aceRankCounting(dealerCards, dealerCardSum);
		
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
		onHandsCards.clear();
		playerCards.clear();
		dealerCards.clear();
	}
	
	private int aceRankCounting(List<Card> cards, int sum) {
		if (sum > 21 && cards.contains(new Card(null, Rank.ACE))) {
			int aceCount = Collections.frequency(cards, new Card(null, Rank.ACE));
			
			while (sum > 21 && aceCount != 0) {
				sum -= 9;
				aceCount--;
			}
		}	
		return sum;
	}



}
