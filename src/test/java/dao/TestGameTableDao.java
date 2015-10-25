package dao;

import static org.junit.Assert.*;

import java.util.Date;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tribel.dao.GameTableDao;
import com.tribel.dao.UsersDao;
import com.tribel.entity.ActionType;
import com.tribel.entity.GameTable;


@ContextConfiguration("/beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@Transactional(rollbackFor = Exception.class)
public class TestGameTableDao {

	@Inject
	private GameTableDao gameTableDao;
	@Inject
	private UsersDao usersDao;
	private GameTable gameTable;

	@Before
	public void init() {
		java.util.Date currentDate = new Date();
		gameTable = new GameTable(usersDao.getById(1), ActionType.RefillAcount,
				5, new java.sql.Date(currentDate.getTime()));
	}

	@Test
	public void testAddTable() {
		gameTable = gameTableDao.addRecord(gameTable);
		assertNotNull(gameTable);
		assertNotEquals(0, gameTable.getId());
	}

}
