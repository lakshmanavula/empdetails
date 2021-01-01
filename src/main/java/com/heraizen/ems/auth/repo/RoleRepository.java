package com.heraizen.ems.auth.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.heraizen.ems.auth.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	Role findByRole(String role);
}
