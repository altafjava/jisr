package com.jisr.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SystemSettingRequest {
	private String name;
	private String value;
	private boolean enabled;
	private String displayName;
	private String description;
}
