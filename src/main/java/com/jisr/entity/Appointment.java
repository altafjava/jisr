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
@Table(name = "appointments")
public class Appointment extends AuditableEntity<Long> {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
	private PatientProfile patientProfile;

	@ManyToOne
	@JoinColumn(name = "provider_id", referencedColumnName = "id", nullable = false)
	private HealthcareProvider healthcareProvider;

	@ManyToOne
	@JoinColumn(name = "appointment_status_id", referencedColumnName = "id", nullable = false)
	private AppointmentStatus appointmentStatus;

	@Column(name = "appointment_time", nullable = false)
	private java.time.LocalDateTime appointmentTime;

	@Column(name = "reason_for_visit")
	private String reasonForVisit;

	@Column(name = "ms_teams_link")
	private String msTeamsLink;

}
