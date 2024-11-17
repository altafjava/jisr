package com.jisr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompletedField {
	private String entity;
	private String fieldName;
	private String displayName;
	private Object value;
	private Integer weight;
}
