package com.jisr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "profile_field_weights")
public class ProfileFieldWeight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "entity_name", nullable = false)
	private String entityName;

	@Column(name = "field_name", nullable = false)
	private String fieldName;

	@Column(name = "weight", nullable = false)
	private Integer weight;
	
}
