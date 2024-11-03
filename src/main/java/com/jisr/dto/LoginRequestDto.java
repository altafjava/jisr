package com.jisr.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
	private String email;
    private String phoneNumber;
    private String password;
}
