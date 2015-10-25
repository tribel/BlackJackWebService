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
	private double balance;
	
	@XmlElement
	private int gameNumber;
	

	@OneToMany(cascade= CascadeType.PERSIST,mappedBy="user")
	private Collection<GameTable> gameTables;
	
	public Users(){}
	
	public Users(String name) {
		this.name = name;
	}
	
	public Users(String name, double balance) {
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (Double.doubleToLongBits(balance) != Double
				.doubleToLongBits(other.balance))
			return false;
		if (gameNumber != other.gameNumber)
			return false;
		if (gameTables == null) {
			if (other.gameTables != null)
				return false;
		} else if (!gameTables.equals(other.gameTables))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
}
