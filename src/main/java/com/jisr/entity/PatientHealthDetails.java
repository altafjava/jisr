package com.jisr.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "patient_health_details")
public class PatientHealthDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cancer_type", length = 100)
	private String cancerType;

	@Column(name = "cancer_treatment", length = 100)
	private String cancerTreatment;

	@Column(name = "medicines_and_doses", columnDefinition = "TEXT")
	private String medicinesAndDoses;

	@Column(name = "chemotherapy_history")
	private Integer chemotherapyHistory;

	@Column(name = "has_central_catheter")
	private Boolean hasCentralCatheter;

	@Column(name = "catheter_type", length = 100)
	private String catheterType;

	@Column(name = "height")
	private Integer height;

	@Column(name = "weight")
	private Integer weight;

	@Column(name = "bmi", columnDefinition = "DOUBLE PRECISION")
	private Double bmi;

	@Column(name = "age")
	private Integer age;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(length = 10)
	private String gender;

	@Column(name = "healthcare_region", length = 100)
	private String healthcareRegion;

	@OneToOne
	@JoinColumn(name = "patient_id", nullable = false, unique = true)
	private Patient patient;

	@Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime updatedAt = LocalDateTime.now();

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public void calculateDerivedData() {
		if (height != null && weight != null && height > 0) {
			this.bmi = weight / Math.pow(height / 100.0, 2); // BMI formula in metric system
		}
		if (dateOfBirth != null) {
			this.age = LocalDate.now().getYear() - dateOfBirth.getYear();
		}
	}
}
