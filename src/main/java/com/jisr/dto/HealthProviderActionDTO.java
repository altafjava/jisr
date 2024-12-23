package com.jisr.dto;

import com.jisr.constant.ProviderStatus;
import com.jisr.validator.EnumValidator;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HealthProviderActionDTO {
	private Long userId;
	@NotBlank(message = "Action is mandatory")
	@EnumValidator(enumClass = ProviderStatus.class, message = "Action must be either PENDING, APPROVED, or REJECTED")
	private String action;
}
