package com.jisr.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.jisr.entity.core.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

	@OneToOne
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private User patient;

	@Column(name = "relationship", length = 100)
	private String relationship;

	@Column(name = "cancer_type", length = 100)
	private String cancerType;

	@Column(name = "treatment_type", length = 100)
	private String treatmentType;

	@Column(name = "medicines", columnDefinition = "TEXT")
	private String medicines;

	@Column(name = "chemo_history", columnDefinition = "TEXT")
	private String chemoHistory;

	@Column(name = "central_catheter_info")
	private Boolean centralCatheterInfo;

	@Column(name = "height", precision = 5, scale = 2)
	private BigDecimal height;

	@Column(name = "weight", precision = 5, scale = 2)
	private BigDecimal weight;

	@Column(name = "bmi", precision = 5, scale = 2)
	private BigDecimal bmi;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "gender", length = 10)
	private String gender;

	@Column(name = "region", length = 100)
	private String region;
}
