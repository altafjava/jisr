package com.jisr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EmailNotification {
	private String to;
	private String subject;
	private String body;
}
