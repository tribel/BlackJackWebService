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

import com.tribel.entity.ActionType;
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
	private double currentBet;
	
	
	@RequestMapping(value= "users/{id}" , method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Users> getUserById(@PathVariable("id") int id) {
		Users retUser = usersService.getById(id);
		return new ResponseEntity<Users>(retUser, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "users/add/{name}/{balance}")
	@ResponseBody
	public ResponseEntity<Users> addNewUser(@PathVariable("name") String name,
			@PathVariable("balance") double balance) {
		
		Users tmpUser = new Users(name, balance);
		return new ResponseEntity<>(usersService.addUser(tmpUser),
				HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "users/signin/{id}")
	@ResponseBody
	public ResponseEntity<Users> autorizedById(@PathVariable("id")int id) {
		Users tmpUser;
		if((tmpUser = usersService.getById(id)) != null) 
			currentUserId = id;
		
		return new ResponseEntity<>(tmpUser, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "users/balance")
	@ResponseBody
	public ResponseEntity<Double> getBalans() {
		return new ResponseEntity<>(usersService.getBalance(currentUserId), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "users/edit_balance/{sum}")
	@ResponseBody
	public ResponseEntity<Users> refillBalance(@PathVariable("sum") Double sum) {
		if(sum > 0)
			usersService.editBalance(usersService.getById(currentUserId), sum, ActionType.RefillAcount);
		return new ResponseEntity<>(usersService.getById(currentUserId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "game/deal/{bet}")
	@ResponseBody
	public ResponseEntity<List<Card>> startGame(@PathVariable("bet") double bet) {
		if((currentBet = bet) > usersService.getBalance(currentUserId)) 
			return null;
		
		Users tmpUser = usersService.getById(currentUserId);
		usersService.gameCountIncrement(tmpUser);
		usersService.editBalance(tmpUser, -bet, ActionType.FillFromGame);
		
		if((gameResult = playerService.deal()) == PlayerService.WIN)
			usersService.editBalance(tmpUser, 2.5 * currentBet, ActionType.FillFromGame);	
		else if(gameResult == PlayerService.PUSH)
			usersService.editBalance(tmpUser, currentBet, ActionType.FillFromGame);	
		
		return new ResponseEntity<List<Card>>(playerService.getCardsOnHands(),
				HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "game/hit") 
	@ResponseBody
	public ResponseEntity<List<Card>> hit() {
		if(gameResult != PlayerService.CONTINUE) return null;
		gameResult = playerService.hit();
		doBalanceCounting();
		return new ResponseEntity<List<Card>>(playerService.getPlayerCards(), HttpStatus.OK);
	}
	
	 
	@RequestMapping(value = "game/stand")
	@ResponseBody
	public ResponseEntity<List<Card>> stand() {
		if(gameResult != PlayerService.CONTINUE) return null;
		gameResult = playerService.stand();
		doBalanceCounting();
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
	
	
	private void doBalanceCounting() {
		Users tmpUser = usersService.getById(currentUserId);
		if(gameResult  == PlayerService.WIN)
			usersService.editBalance(tmpUser, 2 * currentBet, ActionType.FillFromGame);	
		else if(gameResult == PlayerService.PUSH)
			usersService.editBalance(tmpUser, currentBet, ActionType.FillFromGame);	
	}
	
}
