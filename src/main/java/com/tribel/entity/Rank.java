package com.tribel.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum Rank {
		
	TWO(2), TREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
    NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10), ACE(10); 
	
	private int value;
	
	Rank(int value) {
		this.value = value;
	}
	
	public int getValue() { return value; }
}
