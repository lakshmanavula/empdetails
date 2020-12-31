package com.heraizen.ems.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService{

	private Map<String, UserDetails> map = new HashMap<>();



	@Autowired
	public AppUserService(BCryptPasswordEncoder encoder) {
	
		UserDetails userDetails1 = User.withUsername("krish").password("krish@123").roles("USER")
				.passwordEncoder(encoder::encode).build();
		UserDetails userDetails2 = User.withUsername("manoj").password("manoj@123").passwordEncoder(encoder::encode)
				.roles("USER").build();
		UserDetails userDetails3 = User.withUsername("admin").password("admin@123").passwordEncoder(encoder::encode)
				.roles("ADMIN").build();
		map.put(userDetails1.getUsername(), userDetails1);
		map.put(userDetails2.getUsername(), userDetails2);
		map.put(userDetails3.getUsername(), userDetails3);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return map.get(username);
	}
	
	

}
