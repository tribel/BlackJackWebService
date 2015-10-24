package com.tribel.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.tribel.dao.UsersDao;
import com.tribel.entity.ActionType;
import com.tribel.entity.GameTable;
import com.tribel.entity.Users;

@Named
public class UsersServiceIml implements UsersService{

	@Inject
	private UsersDao userDao;
	
	@Inject
	private GameTableService gameTable;
	
	@Override
	public Users getById(int id) {
		return userDao.getById(id);
	}

	@Override
	@Transactional
	public Users addUser(Users user) {
		return userDao.addUser(user);
	}

	@Override
	@Transactional
	public void editBalance(Users user, double balance , ActionType action) {
		userDao.editBalance(user, balance);
		java.util.Date currentDate = new Date();
		gameTable.addRecord(new GameTable(user, action, balance, new java.sql.Date(currentDate.getTime())));
	}

	@Override
	public double getBalance(int id) {
		return userDao.getBalance(id);
	}

	@Override
	@Transactional
	public void gameCountIncrement(Users user) {
		userDao.gameCountIncrement(user);
	}

	@Override
	public List<Users> getAllList() {
		return userDao.getAllList();
	}
	
}
