package dao;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tribel.dao.UsersDao;
import com.tribel.entity.Users;

@ContextConfiguration("/beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@Transactional(rollbackFor = Exception.class)
public class TestUsersDao {

	@Inject
	UsersDao usersDao;
	Users testUser;
	
	@Before
	public void init() {
		testUser = new Users("Testenko", 20);
		testUser = usersDao.addUser(testUser);
	}
	
	@Test
	public void testAddUser() {
		assertNotNull(testUser);
		assertNotEquals(0, testUser.getId());
	}

	@Test
	public void testEditBalance() {
		usersDao.editBalance(testUser, 10);
		assertEquals(30, usersDao.getBalance(testUser.getId()), 0.1);
	}

	@Test
	public void testGameCountIncrement() {
		usersDao.gameCountIncrement(testUser);
		assertEquals(1, testUser.getGameNumber());
	}

	@Test
	public void testGetBalance() {
		double balnce = usersDao.getBalance(testUser.getId());
		assertEquals(20, balnce, 0.1);
	}

}
