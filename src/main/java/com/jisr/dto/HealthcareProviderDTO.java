package com.jisr.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jisr.constant.AppConstant;
import com.jisr.constant.ValidGender;
import com.jisr.validator.PatchValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class HealthcareProviderDTO {

	@Pattern(regexp = "^[a-zA-Z0-9]{5,20}$", message = "Username must be alphanumeric and between 5-20 characters", groups = { PatchValidationGroup.class })
	private String firstName;

	private String fatherName;

	@Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should contain only alphabetic characters", groups = { PatchValidationGroup.class })
	private String lastName;

	@Email(message = "Email should be valid", groups = { PatchValidationGroup.class })
	private String email;

	@Pattern(regexp = "^\\+[0-9]{10,15}$", message = "Phone number must be in international format", groups = { PatchValidationGroup.class })
	private String phoneNumber;

	@ValidGender(message = "Gender must be either 'Male' or 'Female'", groups = { PatchValidationGroup.class })
	private String gender;

	@Past(message = "Date of birth must be a past date", groups = { PatchValidationGroup.class })
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConstant.DATE_FORMAT_dd_MM_yyyy)
	private LocalDate dateOfBirth;
	private String specialization;
	private String qualification;
	private Integer experience;
	private Integer noOfMedicalLicenses;
	private String personalPhotoUrl;
	private String cvUrl;

}
