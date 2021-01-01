package com.heraizen.ems.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationFacade implements IAuthenticationFacade {

	@Override
	public Authentication authentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
