package com.tribel.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tribel.entity.Users;
import com.tribel.service.UsersService;


@RestController
public class BlackJackController {
	
	@Inject
	private UsersService usersService;
	
	private int currentUserId;
	
	@RequestMapping(value= "users/{id}" , method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Users> getUserById(@PathVariable("id") int id) {
		Users retUser = usersService.getById(id);
		System.out.println(retUser + "hi sluty");
		return new ResponseEntity<Users>(retUser, HttpStatus.OK);
	}
	
	public ResponseEntity<String> addNewUser(Users user) {
		return null;
	}
	
	public ResponseEntity<Users> autorizedById(int id) {
		return null;
	}
	
	public ResponseEntity<Integer> getBalans() {
		return null;
	}
	
	public ResponseEntity<String> refillBalance(int sum) {
		return null;
	}
	
	
}
