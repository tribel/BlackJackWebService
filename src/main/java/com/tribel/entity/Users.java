package com.tribel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private int balance;
	private int gameNumber;
	
	public Users(){}
	
	public Users(String name) {
		this.name = name;
	}
	
	public Users(String name, int balance) {
		this.name = name;
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getGameNumber() {
		return gameNumber;
	}

	public void setGameNumber(int gameNumber) {
		this.gameNumber = gameNumber;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", balance=" + balance
				+ ", gameNumber=" + gameNumber + "]";
	}
	
	
	
}
