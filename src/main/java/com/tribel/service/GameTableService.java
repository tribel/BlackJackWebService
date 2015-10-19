package com.tribel.service;

import java.util.List;

import com.tribel.entity.GameTable;

                                                               
public interface GameTableService {
	
	public void addRecord(GameTable record);
	
	public GameTable getById(int id);
	
	public List<GameTable> getAllList();
}
