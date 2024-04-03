package com.microservices.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.security.dto.authDto;
import com.microservices.security.entity.userEntity;
import com.microservices.security.service.userService;

@RestController
@RequestMapping("api/auth/")
public class AuthController {

	@Autowired
	private userService userservice;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public String addUser(@RequestBody userEntity userentity) {
		userservice.saveUser(userentity);
		return "User added Successufully";
	}

	@GetMapping("/validate")
	public String validToken(@RequestParam("Token") String Token) {
		userservice.validateToken(Token);
		return "Token is valid";
	}

	@PostMapping("/token")
	public String generateToken(@RequestBody authDto entity) {

		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(entity.getUsername(), entity.getPassword()));
		if (authenticate.isAuthenticated()) {
			return userservice.generateToken(entity.getUsername());
		} else {
			throw new RuntimeException("user not found exception");
		}
	}
}
