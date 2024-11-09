package com.jisr.security;

import lombok.Data;

@Data
public class JwtResponse {

	private String token;
	private String username;
	private final String type = "Bearer";

	public JwtResponse(String username, String token) {
		this.username = username;
		this.token = token;
	}

}
