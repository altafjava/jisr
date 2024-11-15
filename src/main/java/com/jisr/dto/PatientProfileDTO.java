package com.jisr.dto;

import java.time.LocalDate;
import com.jisr.entity.RoleEnum;
import com.jisr.validator.PatchValidationGroup;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class PatientProfileDTO {

	@Pattern(regexp = "^[a-zA-Z0-9]{5,20}$", message = "Username must be alphanumeric and between 5-20 characters", groups = { PatchValidationGroup.class })
	private String username;

	@Pattern(regexp = "^[a-zA-Z]+$", message = "First name should contain only alphabetic characters", groups = { PatchValidationGroup.class })
	private String firstName;

	private String fatherName;

	@Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should contain only alphabetic characters", groups = { PatchValidationGroup.class })
	private String lastName;

	private String fullName;

	@Email(message = "Email should be valid", groups = { PatchValidationGroup.class })
	private String email;

	@Pattern(regexp = "^\\+[0-9]{10,15}$", message = "Phone number must be in international format", groups = { PatchValidationGroup.class })
	private String phoneNumber;

	private String relationship;

	@Enumerated(EnumType.STRING)
	private RoleEnum role;

	private Boolean firstLogin;

	private String cancerType;

	private String cancerTreatment;

	private String medicinesAndDoses;

	@PositiveOrZero(message = "Chemotherapy history must be a positive integer", groups = { PatchValidationGroup.class })
	private Integer chemotherapyHistory;

	private Boolean hasCentralCatheter;

	private String catheterType;

	@Min(value = 50, message = "Height must be at least 50 cm", groups = { PatchValidationGroup.class })
	@Max(value = 250, message = "Height must be at most 250 cm", groups = { PatchValidationGroup.class })
	private Integer height;

	@Min(value = 20, message = "Weight must be at least 20 kg", groups = { PatchValidationGroup.class })
	@Max(value = 300, message = "Weight must be at most 300 kg", groups = { PatchValidationGroup.class })
	private Integer weight;

	@Past(message = "Date of birth must be a past date", groups = { PatchValidationGroup.class })
	private LocalDate dateOfBirth;

	@Pattern(regexp = "Male|Female", message = "Gender must be either 'Male' or 'Female'", groups = { PatchValidationGroup.class })
	private String gender;

	private String healthcareRegion;

	private Double bmi;

	private Integer age;
}
