package com.tribel.dao;

import java.util.List;

import com.tribel.entity.GameTable;

public interface GameTableDao {
	
	public void addRecord(GameTable record);
	
	public GameTable getById(int id);
	
	public List<GameTable> getAllList();
	
	
}
