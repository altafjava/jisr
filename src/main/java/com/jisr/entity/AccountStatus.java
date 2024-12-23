package com.jisr.entity;

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
@Table(name = "account_status")
public class AccountStatus extends AuditableEntity<Long> {

	private static final long serialVersionUID = 4707467561108061571L;

	@Column(name = "is_disabled", nullable = false)
	private Boolean isDisabled = false;

	@Column(name = "disabled_reason")
	private String disabledReason;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

}
