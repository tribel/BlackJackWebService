package com.tribel.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tribel.entity.GameTable;

@Repository
public class GameTableDaoImpl implements GameTableDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public GameTable addRecord(GameTable record) {
		em.persist(record);
		em.flush();
		return record;
	}

	@Override
	public GameTable getById(int id) {
		return em.find(GameTable.class, id);
	}

	@Override
	public List<GameTable> getAllList() {
		TypedQuery<GameTable> query = em.createQuery("SELECT g FROM GameTable g", GameTable.class);
		return query.getResultList();
	}
	
	
}
