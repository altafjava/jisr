package com.jisr.entity;

import java.math.BigDecimal;
import com.jisr.entity.core.AuditableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "measurement_history")
public class MeasurementHistory extends AuditableEntity<Long> {

	private static final long serialVersionUID = -3196937724626232968L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_profile_id", nullable = false)
	private PatientProfile patientProfile;

	@Column(name = "height", precision = 5, scale = 2)
	private BigDecimal height; // in cm

	@Column(name = "weight", precision = 5, scale = 2)
	private BigDecimal weight; // in kg

	@Column(name = "bmi", precision = 5, scale = 2)
	private BigDecimal bmi;

}
