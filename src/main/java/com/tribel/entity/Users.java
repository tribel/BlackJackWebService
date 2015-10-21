package com.tribel.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
@Entity
public class Users {
	
	@XmlAttribute
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@XmlElement
	private String name;
	
	@XmlElement
	private int balance;
	
	@XmlElement
	private int gameNumber;
	

	@OneToMany(cascade= CascadeType.PERSIST,mappedBy="user")
	private Collection<GameTable> gameTables;
	
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

	public Collection<GameTable> getGameTables() {
		return gameTables;
	}

	public void setGameTables(Collection<GameTable> gameTables) {
		this.gameTables = gameTables;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", balance=" + balance
				+ ", gameNumber=" + gameNumber + "]";
	}
	
	
	
}
