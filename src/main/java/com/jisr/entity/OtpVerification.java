package com.jisr.entity;

import java.time.LocalDateTime;
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
@Table(name = "otp_verification")
public class OtpVerification extends AuditableEntity<Long> {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(name = "otp_code", length = 10, nullable = false)
	private String otpCode;

	@Column(name = "expiration_time", nullable = false)
	private LocalDateTime expirationTime;

	@Column(name = "is_verified", nullable = false)
	private Boolean isVerified = false;

}
