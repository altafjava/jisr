package com.jisr.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import com.jisr.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Profile2 {
//	User
	private String username;
	private String firstName;
	private String fatherName;
	private String lastName;
	private String fullName;
	private String email;
	private String phoneNumber;
	private String relationship;
	private Set<Role> roles;
	private Boolean firstLogin;
//	PatientProfile
	private String cancerType;
	private String treatmentType;
	private String medicinesAndDoses;
	private String chemotherapyHistory;
	private Boolean hasCentralCatheter;
	private String catheterType;
	private BigDecimal height;
	private BigDecimal weight;
	private LocalDate dateOfBirth;
	private String gender;
	private String healthcareRegion;
	private BigDecimal bmi;
	private Integer age;
// Healthcare Provider
	private String specialization;
	private String qualification;
	private Integer experience;

}
