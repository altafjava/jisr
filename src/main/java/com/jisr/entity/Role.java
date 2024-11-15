package com.jisr.entity;

import com.jisr.entity.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role extends BaseEntity<Long> {

	private static final long serialVersionUID = -9218710373017577644L;

	@Column(name = "name", unique = true, nullable = false)
	private String name;
}
