package com.jisr.entity;

import java.time.LocalDate;
import com.jisr.constant.ProviderStatus;
import com.jisr.entity.core.AuditableEntity;
import com.jisr.validator.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "healthcare_providers")
public class HealthcareProvider extends AuditableEntity<Long> {

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(name = "gender", length = 10)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "specialization", length = 100)
	private String specialization;

	@Column(name = "qualification", length = 100)
	private String qualification;

	@Column(name = "experience")
	private Integer experience;

	@Column(name = "no_of_medical_licenses")
	private Integer noOfMedicalLicenses;

	@Column(name = "personal_photo_url")
	private String personalPhotoUrl;

	@Column(name = "cv_url")
	private String cvUrl;
	
	@Enumerated(EnumType.STRING)
	private ProviderStatus status;

}
