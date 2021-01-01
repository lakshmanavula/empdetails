package com.heraizen.ems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.heraizen.ems.auth.model.Role;
import com.heraizen.ems.auth.model.User;
import com.heraizen.ems.auth.repo.RoleRepository;
import com.heraizen.ems.auth.repo.UserRepository;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class EmpdetailApplication implements CommandLineRunner {


	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(EmpdetailApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		roleRepository.deleteAll();
		userRepository.deleteAll();
		Role role1 = new Role();
		role1.setRole("ROLE_ADMIN");
		role1 = roleRepository.save(role1);
		Role role2 = new Role();
		role2.setRole("ROLE_SUPERADMIN");
		role2 = roleRepository.save(role2);
		
		User user = new User();
		user.setEmail("admin@heraizen.com");
		user.setPassword(bCryptPasswordEncoder.encode("admin@123!"));
		user.setEnabled(true);
		user.addRole(role1);
		userRepository.save(user);
		User user2 = new User();
		user2.setEmail("superadmin@heraizen.com");
		user2.setPassword(bCryptPasswordEncoder.encode("admin@123!"));
		user2.setEnabled(true);
		user2.addRole(role2);
		userRepository.save(user2);
	
	}
}
