package com.jisr.entity.core;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class AuditableEntity<PK extends Serializable> extends BaseEntity<PK> {

	private static final long serialVersionUID = 4656725029663282560L;

	@Column(name = "created_by", updatable = false)
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;

	@Column(name = "updated_date")
	private LocalDateTime updatedDate;

	@PrePersist
	protected void prePersist() {
		this.createdDate = LocalDateTime.now();
		// Set createdBy here if user context is available
	}

	@PreUpdate
	protected void preUpdate() {
		this.updatedDate = LocalDateTime.now();
		// Set updatedBy here if user context is available
	}

}
