package com.tribel.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tribel.entity.Users;

@Repository
public class UsersDaoImpl implements UsersDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Users getById(int id) {
		return em.find(Users.class, id);
	}

	@Override
	public void addUser(Users user) {
		em.persist(user);
	}

	@Override
	public void editBalance(Users user, int balance) {
		user.setBalance(user.getBalance() + balance);
		em.merge(user);
	}

	@Override
	public void gameCountIncrement(Users user) {
		user.setGameNumber(user.getGameNumber() + 1);
		em.merge(user);
	}

	@Override
	public int getBalance(int id) {
		Users users = em.find(Users.class, id);
		return users.getBalance();
	}

	@Override
	public List<Users> getAllList() {
		TypedQuery<Users> query = em.createQuery("SELECT u From Users u", Users.class);
		return query.getResultList();
	}

}
