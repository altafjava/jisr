package com.jisr.entity;

import java.time.LocalDate;
import com.jisr.constant.HealthProviderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "healthcare_providers_old")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthcareProviderOld {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;
	private String fatherName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String fullName;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String phoneNumber;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String specialization;

	@Column(nullable = false)
	private String qualification;

	@Column(nullable = false)
	private String gender;

	@Column(nullable = false)
	private LocalDate dateOfBirth;

	private String photoPath;
	private String cvPath;
	private Integer noOfMedicalLicenses;

	@Enumerated(EnumType.STRING)
	private HealthProviderStatus status;

}
