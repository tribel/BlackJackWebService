package com.tribel.service;

import java.util.List;

import com.tribel.entity.ActionType;
import com.tribel.entity.Users;


public interface UsersService {

	public Users getById(int id);

	public Users addUser(Users user);

	public void editBalance(Users user, double balance, ActionType action);

	public double getBalance(int id);

	public void gameCountIncrement(Users user);

	public List<Users> getAllList();
}
