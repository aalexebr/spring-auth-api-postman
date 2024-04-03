package org.java.spring.controller;

import org.java.spring.db.User;
import org.java.spring.serv.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/api")
public class AuthApiController {
	
	@Autowired private UserService userService;
	
	
	@GetMapping("test")
	public ResponseEntity<String> test2(){
	
		 return new ResponseEntity<>("AUTHENTICATED", HttpStatus.OK);
	}

}
