package com.tribel.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.tribel.dao.GameTableDao;
import com.tribel.entity.GameTable;

@Named
public class GameTableServiceImpl implements GameTableService{

	@Inject
	private GameTableDao gameTableDao;
	
	@Override
	@Transactional
	public void addRecord(GameTable record) {
		gameTableDao.addRecord(record);
	}

	@Override
	public GameTable getById(int id) {
		return gameTableDao.getById(id);
	}

	@Override
	public List<GameTable> getAllList() {
		return gameTableDao.getAllList();
	}

}
