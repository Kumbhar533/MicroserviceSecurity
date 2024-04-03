package com.microservices.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.security.entity.userEntity;

public interface userRepository extends JpaRepository<userEntity, Integer> {

	Optional<userEntity> findByName(String name);

}
