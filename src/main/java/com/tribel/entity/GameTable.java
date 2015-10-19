package com.tribel.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GameTable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private Users user;
	
	@Enumerated(EnumType.STRING)
	private ActionType actionType;
	
	private int actionSum;
	private Date actionDate;
	
	public GameTable() {}
	
	public GameTable(Users user, ActionType type, int sum, Date date) {
		this.user = user;
		this.actionType = type;
		this.actionSum = sum;
		this.actionDate = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public int getActionSum() {
		return actionSum;
	}

	public void setActionSum(int actionSum) {
		this.actionSum = actionSum;
	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	@Override
	public String toString() {
		return "GameTable [id=" + id + ", user=" + user + ", actionType="
				+ actionType + ", actionSum=" + actionSum + ", actionDate="
				+ actionDate + "]";
	}
	
	
}
