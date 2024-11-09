package com.jisr.dto;

import lombok.Data;

@Data
public class PasswordResetDTO {
	private String token; // Reset token received via email/SMS
	private String newPassword;
}
