package com.jisr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RemainingField {
	private String entity;
	private String fieldName;
	private String displayName;
	private Integer weight;
}
