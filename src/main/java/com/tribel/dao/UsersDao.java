package com.tribel.dao;

import java.util.List;

import com.tribel.entity.Users;

public interface UsersDao {

	public Users getById(int id);

	public Users addUser(Users user);

	public void editBalance(Users user, double balance);
	
	public double getBalance(int  id);
	
	public void gameCountIncrement(Users user);
	
	public List<Users> getAllList();
}
