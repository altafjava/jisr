package com.jisr.security;

import lombok.Data;

@Data
public class JwtResponse {
	private final String tokenType = "Bearer";
	private String username;
	private String accessToken;
	private String refreshToken;

	public JwtResponse(String username, String accessToken, String refreshToken) {
		this.username = username;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

}
