package com.jisr.entity;

import com.jisr.entity.core.AuditableEntity;
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
@Table(name = "clinical_notes")
public class ClinicalNote extends AuditableEntity<Long> {

	private static final long serialVersionUID = -4823821354408689867L;

	@ManyToOne
	@JoinColumn(name = "appointment_id", referencedColumnName = "id", nullable = false)
	private Appointment appointment;

	@ManyToOne
	@JoinColumn(name = "provider_id", referencedColumnName = "id", nullable = false)
	private HealthcareProvider healthcareProvider;

	@Column(name = "notes", nullable = false)
	private String notes;

}
