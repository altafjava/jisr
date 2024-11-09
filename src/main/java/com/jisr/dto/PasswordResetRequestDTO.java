package com.jisr.dto;

import lombok.Data;

@Data
public class PasswordResetRequestDTO {
	private String emailOrPhone;
}
