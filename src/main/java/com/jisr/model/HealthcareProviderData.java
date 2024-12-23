package com.jisr.model;

import java.time.LocalDate;
import com.jisr.validator.Gender;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HealthcareProviderData {
	private Gender gender;
	private LocalDate dateOfBirth;
	private String specialization;
	private String qualification;
	private Integer experience;
	private Integer noOfMedicalLicenses;
	private String personalPhotoUrl;
	private String cvUrl;
}
