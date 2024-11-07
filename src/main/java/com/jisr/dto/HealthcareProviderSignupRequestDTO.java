package com.jisr.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jisr.validator.PastDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HealthcareProviderSignupRequestDTO {

	@NotBlank(message = "First name is mandatory")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "First name should contain only alphabetic characters")
	private String firstName;

	private String fatherName;

	@NotBlank(message = "Last name is mandatory")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should contain only alphabetic characters")
	private String lastName;

	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@(gmail|yahoo)\\.com$", message = "Allowed email domains are gmail.com and yahoo.com")
	private String email;

	@NotBlank(message = "Phone number is mandatory")
	@Pattern(regexp = "^\\+[0-9]{10,15}$", message = "Phone number must be in international format")
	private String phoneNumber;

	@NotBlank(message = "Password is mandatory")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain uppercase, lowercase, numbers, and special characters")
	private String password;

	@NotBlank(message = "Specialization is mandatory")
	private String specialization;

	@NotBlank(message = "Qualification is mandatory")
	private String qualification;

	@NotBlank
	@Pattern(regexp = "^(Male|Female)$", message = "Gender must be either 'Male' or 'Female'")
	private String gender;

	@NotNull(message = "Date of birth is mandatory")
	@PastDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;

	private String photoPath;
	private String cvPath;
	private Integer noOfMedicalLicenses;
}
