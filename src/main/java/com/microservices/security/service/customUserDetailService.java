package com.microservices.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.microservices.security.entity.userEntity;
import com.microservices.security.repository.userRepository;

@Component
public class customUserDetailService implements UserDetailsService {

	@Autowired
	private userRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<userEntity> credentils = repository.findByName(username);

		return credentils.map(customUserDetail::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found" + username));

	}

}
