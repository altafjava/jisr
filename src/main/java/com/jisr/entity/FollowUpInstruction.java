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
@Table(name = "follow_up_instructions")
public class FollowUpInstruction extends AuditableEntity<Long> {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "appointment_id", referencedColumnName = "id", nullable = false)
	private Appointment appointment;

	@ManyToOne
	@JoinColumn(name = "provider_id", referencedColumnName = "id", nullable = false)
	private HealthcareProvider healthcareProvider;

	@Column(name = "instructions", nullable = false)
	private String instructions;

}
