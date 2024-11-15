package com.jisr.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
	@NotBlank
	private String emailOrPhone;
	@NotBlank
	private String password;
}
