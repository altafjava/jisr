package com.jisr.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.jisr.validator.Gender;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientProfileData {
	private String username;
	private String firstName;
	private String fatherName;
	private String lastName;
	private String fullName;
	private String email;
	private String phoneNumber;
	private Gender gender;
	private LocalDate dateOfBirth;
	private Integer age;
	private String relationship;
	private String cancerType;
	private String treatmentType;
	private String medicinesAndDoses;
	private String chemotherapyHistory;
	private Boolean hasCentralCatheter;
	private String centralCatheterType;
	private String healthcareRegion;
	private BigDecimal height;
	private BigDecimal weight;
	private BigDecimal bmi;
}
