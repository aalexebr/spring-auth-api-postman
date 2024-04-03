package org.java.spring.serv;

import org.java.spring.DTO.AuthRequest;
import org.java.spring.DTO.AuthResponse;
import org.java.spring.DTO.RegisterRequest;
import org.java.spring.auth.conf.ApplicationConfig;
import org.java.spring.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	@Autowired UserService userService;
	@Autowired JwtService jwtService;
	@Autowired private AuthenticationManager authenticationManager;
	
	public AuthResponse register (RegisterRequest request) {
		User u = new User();
		u.setUsername(request.getUsername());
		u.setPassword(ApplicationConfig.passwordEncoder().encode(request.getPassword()));
		userService.save(u);
		
		String jwt = jwtService.generateToken(u);
		
		AuthResponse res = new AuthResponse();
		res.setAccessToken(jwt);
		return res;
	}
  
	 public AuthResponse authenticate(AuthRequest request) {
		    authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(
		            request.getUsername(),
		            request.getPassword()
		        )
		    );
		    var user = userService.loadUserByUsername(request.getUsername());
		    var jwtToken = jwtService.generateToken(user);
		    var refreshToken = jwtService.generateRefreshToken(user);
		    
		    AuthResponse res = new AuthResponse();
		    res.setAccessToken(jwtToken);
		    res.setRefreshToken(refreshToken);
		        
		    return res;
		  }
	
}