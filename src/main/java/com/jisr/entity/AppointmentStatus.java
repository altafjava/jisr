package com.jisr.entity;

import com.jisr.entity.core.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "appointment_status")
public class AppointmentStatus extends AuditableEntity<Long> {

	private static final long serialVersionUID = -4494251868603971416L;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

}
