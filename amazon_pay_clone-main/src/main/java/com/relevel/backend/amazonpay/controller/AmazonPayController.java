package com.relevel.backend.amazonpay.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.relevel.backend.amazonpay.beans.User;
import com.relevel.backend.amazonpay.services.UserDetailServiceImpl;

@RestController
public class AmazonPayController {
	
	
	
	@Autowired
	private UserDetailServiceImpl userServiceImpl;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/ping")
	public String ping() {
		return "Ping is Successful!!";
	}
	
	@GetMapping("/test")
	public  String  test() {
		return "testing done";
		
	}
	
	@GetMapping("/pinged")
	public String pinged() {
		return "Pinged was Successful!!";
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {

		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userServiceImpl.register(user);
			return new ResponseEntity<User>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/user/{userID}")
	public  Optional<User>  getUser(@PathVariable Long userID) {
		return userServiceImpl.getUser(userID);
		
	}
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userServiceImpl.getAllUser();
	}
}

