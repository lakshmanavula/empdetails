package com.heraizen.ems.auth.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponse {

		private String token;
}
