package com.jisr.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationResponse {
	private boolean isActive;
	private String message;

	public RegistrationResponse(boolean isActive, String message) {
		this.isActive = isActive;
		this.message = message;
	}

}
