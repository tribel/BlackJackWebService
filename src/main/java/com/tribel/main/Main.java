package com.tribel.main;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tribel.entity.ActionType;
import com.tribel.entity.GameTable;
import com.tribel.entity.Users;
import com.tribel.service.GameTableService;
import com.tribel.service.UsersService;

public class Main {
		
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("application-config.xml");
		
		UsersService usersService = context.getBean(UsersService.class);
		GameTableService gameTableService = context.getBean(GameTableService.class);
		Users users = new Users("Artem", 10);
		//users.setId(1);
		
		Date date = new Date();
		GameTable gameTable = new GameTable(users, ActionType.RefillAcount, 5,new java.sql.Date(date.getTime()));
		
		//gameTableService.addRecord(gameTable);
		ArrayList<GameTable> arrayList = new ArrayList<GameTable>();
		arrayList.add(gameTable);
		//users.setGameTables(arrayList);
		//usersService.addUser(users);   
		//usersService.gameCountIncrement(usersService.getById(1));
		//System.out.println(usersService.getById(1));
		//System.out.println(usersService.getAllList());
		//usersService.editBalance(usersService.getById(1), 2, ActionType.RefillAcount);
		//System.out.println(gameTableService.getAllList());
		System.out.println(gameTableService.getById(1));
	}
}
