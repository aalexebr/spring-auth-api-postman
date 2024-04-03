package org.java.spring.controller;

import org.java.spring.DTO.AuthRequest;
import org.java.spring.DTO.AuthResponse;
import org.java.spring.DTO.RegisterRequest;
import org.java.spring.db.User;
import org.java.spring.serv.AuthService;
import org.java.spring.serv.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired private UserService userService;
	@Autowired private AuthService authService;
	
	 @GetMapping("test")
	public ResponseEntity<String> test(){
		 return new ResponseEntity<>("test", HttpStatus.OK);
	}
	 
	@GetMapping("test2")
	public ResponseEntity<User> test2(){
		User u = userService.findById(1);
		 return new ResponseEntity<>(u, HttpStatus.OK);
	}

	@PostMapping("login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
		return ResponseEntity.ok(authService.authenticate(request));
	}
	
	@PostMapping("register")
	public ResponseEntity<AuthResponse> registr(@RequestBody RegisterRequest request){
		return ResponseEntity.ok(authService.register(request));
	}
}
