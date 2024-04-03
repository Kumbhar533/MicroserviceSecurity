package com.microservices.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservices.security.entity.userEntity;
import com.microservices.security.repository.userRepository;

@Service
public class userService {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private userRepository userRepository;

	@Autowired
	private jwtService service;

	public String saveUser(userEntity userentity) {
		userentity.setPassword(encoder.encode(userentity.getPassword()));
		userRepository.save(userentity);
		return "user added successfully";
	}

	public String generateToken(String name) {
		String generateToken = service.generateToken(name);
		return generateToken;
	}

	public void validateToken(String token) {

		service.validateToken(token);
	}

}
