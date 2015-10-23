package com.tribel.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tribel.entity.Card;
import com.tribel.entity.Users;
import com.tribel.gamelogic.PlayerService;
import com.tribel.service.UsersService;


@RestController
public class BlackJackController {
	
	@Inject
	private UsersService usersService;
	
	@Inject
	private PlayerService<Card> playerService;
	
	private int currentUserId;
	private int gameResult;
	
	
	
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
	
	@RequestMapping(value = "game/deal/{bet}")
	@ResponseBody
	public ResponseEntity<List<Card>> startGame(@PathVariable("bet") int bet) {
		gameResult = playerService.deal();
		
		return new ResponseEntity<List<Card>>(
				playerService.getCardsOnHands(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "game/hit") 
	@ResponseBody
	public ResponseEntity<List<Card>> hit() {
		gameResult = playerService.hit();
		return new ResponseEntity<List<Card>>(playerService.getPlayerCards(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "game/stand")
	@ResponseBody
	public ResponseEntity<List<Card>> stand() {
		gameResult = playerService.stand();
		return new ResponseEntity<List<Card>>(playerService.getDealerCards(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "game/result")
	@ResponseBody
	private ResponseEntity<String> getGameCondition() {
		String condition = "";
		switch (gameResult) {
		case PlayerService.WIN:
			condition = "YOU WIN!";
			playerService.clearDeck();
			break;

		case PlayerService.PUSH:
			condition = "PUSH";
			playerService.clearDeck();
			break;
			
		case PlayerService.LOSE:
			condition = "YOU LOSE!";
			playerService.clearDeck();
			break;
			
		case PlayerService.CONTINUE:
			condition = "The game continues";
			break;
		}
		
		return new ResponseEntity<String>(condition, HttpStatus.OK);
	}
	
	
}
