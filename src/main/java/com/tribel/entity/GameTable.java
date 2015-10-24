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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
@Entity
public class GameTable {
	@XmlAttribute
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@XmlElement
	@ManyToOne
	@JoinColumn(name="userId")
	private Users user;
	
	@XmlElement
	@Enumerated(EnumType.STRING)
	private ActionType actionType;
	
	@XmlElement
	private double actionSum;
	
	@XmlElement
	private Date actionDate;
	
	public GameTable() {}
	
	public GameTable(Users user, ActionType type, double sum, Date date) {
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

	public double getActionSum() {
		return actionSum;
	}

	public void setActionSum(double actionSum) {
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
