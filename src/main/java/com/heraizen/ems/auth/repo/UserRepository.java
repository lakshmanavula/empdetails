package com.heraizen.ems.auth.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.heraizen.ems.auth.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	User findByEmail(String email);

}