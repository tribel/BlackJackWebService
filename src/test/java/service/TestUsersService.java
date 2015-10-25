package service;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tribel.entity.ActionType;
import com.tribel.entity.GameTable;
import com.tribel.entity.Users;
import com.tribel.service.GameTableService;
import com.tribel.service.UsersService;


@ContextConfiguration("/beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@Transactional(rollbackFor = Exception.class)
public class TestUsersService {
	
	@Inject
	private UsersService usersService; 
	@Inject
	private GameTableService gameTableService;
	private Users user;
	
	@Before
	public void init() {
		user = new Users("Lenon", 40);
		user = usersService.addUser(user);
	}
	
	@Test
	public void testAddUser() {
		assertNotNull(user);
		assertNotEquals(0, user.getId());
	}

	@Test
	public void testEditBalance() {
		usersService.editBalance(user, 5, ActionType.RefillAcount);
		assertEquals(45, usersService.getBalance(user.getId()), 0.1);
		int gameTableSize = gameTableService.getAllList().size();
		GameTable testGameTable = gameTableService.getAllList().get(gameTableSize -1);
		assertEquals(user, testGameTable.getUser());
		assertEquals(ActionType.RefillAcount, testGameTable.getActionType());
	}

	@Test
	public void testGetBalance() {
		assertEquals(40, usersService.getBalance(user.getId()), 0.1);
	}

	@Test
	public void testGameCountIncrement() {
		usersService.gameCountIncrement(user);
		assertEquals(1, user.getGameNumber());
	}

}
