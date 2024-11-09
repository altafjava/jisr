package com.jisr.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
	@NotBlank
	private String emailOrPhone;
	@NotBlank
	private String password;
}
