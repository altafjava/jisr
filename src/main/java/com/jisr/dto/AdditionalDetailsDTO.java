package com.jisr.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdditionalDetailsDTO {
	@NotBlank
	private String cancerType;

	@NotBlank
	private String cancerTreatmentType;

	@NotBlank
	private String medicinesAndDoses;

	@NotNull
	private Integer chemotherapyHistory;

	@NotNull
	private Boolean centralCatheter;

	private String centralCatheterType;

	@NotNull
	private Integer height;

	@NotNull
	private Integer weight;

	@NotNull
	private LocalDate dateOfBirth;

	@NotBlank
	private String gender;

	@NotBlank
	private String healthcareRegion;
}
