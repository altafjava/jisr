package com.jisr.entity;

import java.time.LocalDate;
import com.jisr.entity.core.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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

	@Column(name = "specialization", length = 100)
	private String specialization;

	@Column(name = "qualification", length = 100)
	private String qualification;

	@Column(name = "experience")
	private Integer experience;

	@Column(name = "gender", length = 10)
	private String gender;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Lob
	@Column(name = "personal_photo")
	private byte[] personalPhoto;

	@Lob
	@Column(name = "cv")
	private byte[] cv;
}
