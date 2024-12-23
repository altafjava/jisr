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
@Table(name = "system_settings")
public class SystemSetting extends AuditableEntity<Long> {

	private static final long serialVersionUID = -2198384933057565053L;

	@Column(name = "name", length = 100, unique = true, nullable = false)
	private String name;

	@Column(name = "display_name", length = 100)
	private String displayName;

	@Column(name = "value", length = 100)
	private String value;

	@Column(name = "enabled", nullable = false)
	private boolean enabled = false;

	@Column(name = "description", length = 300)
	private String description;

}
