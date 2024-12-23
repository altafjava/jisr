package com.jisr.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.jisr.entity.core.AuditableEntity;
import com.jisr.validator.Gender;
import jakarta.persistence.CascadeType;
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
@Table(name = "patient_profiles")
public class PatientProfile extends AuditableEntity<Long> {

	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(name = "gender", length = 10)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "height", precision = 5, scale = 2)
	private BigDecimal height; // in cm

	@Column(name = "weight", precision = 5, scale = 2)
	private BigDecimal weight; // in kg

	@Column(name = "bmi", precision = 5, scale = 2)
	private BigDecimal bmi;

	@Column(name = "relationship", length = 100)
	private String relationship;

	@Column(name = "cancer_type", length = 100)
	private String cancerType;

	@Column(name = "treatment_type", length = 100)
	private String treatmentType;

	@Column(name = "medicines_and_doses", columnDefinition = "TEXT")
	private String medicinesAndDoses;

	@Column(name = "chemotherapy_history", columnDefinition = "TEXT")
	private Integer chemotherapyHistory;

	@Column(name = "has_central_catheter")
	private Boolean hasCentralCatheter;

	@Column(name = "central_catheter_type")
	private String centralCatheterType;

	@Column(name = "healthcare_region", length = 100)
	private String healthcareRegion;

}
