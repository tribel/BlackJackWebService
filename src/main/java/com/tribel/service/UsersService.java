package com.tribel.service;

import java.util.List;

import com.tribel.entity.ActionType;
import com.tribel.entity.Users;


public interface UsersService {

	public Users getById(int id);

	public void addUser(Users user);

	public void editBalance(Users user, int balance, ActionType action);

	public int getBalance(int id);

	public void gameCountIncrement(Users user);

	public List<Users> getAllList();
}
