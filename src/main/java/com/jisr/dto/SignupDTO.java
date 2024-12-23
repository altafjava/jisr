package com.jisr.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupDTO {

	@NotBlank(message = "Username is mandatory")
	@Pattern(regexp = "^[a-zA-Z0-9]{5,30}$", message = "Username must be alphanumeric and between 5-30 characters")
	private String username;

	@NotBlank(message = "First name is mandatory")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "First name should contain only alphabetic characters")
	private String firstName;

	@NotBlank(message = "Last name is mandatory")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should contain only alphabetic characters")
	private String lastName;

	private String fatherName;

	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
//	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@(gmail|yahoo)\\.com$", message = "Allowed email domains are gmail.com and yahoo.com")
	private String email;

	@NotBlank(message = "Phone number is mandatory")
	@Pattern(regexp = "^\\+[0-9]{10,15}$", message = "Phone number must be in international format")
	private String phoneNumber;

	@NotBlank(message = "Password is mandatory")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain uppercase, lowercase, numbers, and special characters")
	private String password;

	@NotNull(message = "Role is mandatory")
	private String role;

}
