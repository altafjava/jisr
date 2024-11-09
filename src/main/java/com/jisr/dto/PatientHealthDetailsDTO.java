package com.jisr.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jisr.validator.PastDate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class PatientHealthDetailsDTO {

	@NotBlank(message = "Type of cancer is mandatory")
	private String cancerType;

	@NotBlank(message = "Type of cancer treatment is mandatory")
	private String cancerTreatment;

	@NotBlank(message = "Medicines and doses are mandatory")
	private String medicinesAndDoses;

	@NotNull(message = "Chemotherapy history is mandatory")
	@PositiveOrZero(message = "Chemotherapy history must be a positive integer")
	private Integer chemotherapyHistory;

	@NotNull(message = "Central catheter information is mandatory")
	private Boolean hasCentralCatheter;

	private String catheterType; // Optional, depends on hasCentralCatheter

	@NotNull(message = "Height is mandatory")
	@Min(value = 50, message = "Height must be at least 50 cm")
	@Max(value = 250, message = "Height must be at most 250 cm")
	private Integer height;

	@NotNull(message = "Weight is mandatory")
	@Min(value = 20, message = "Weight must be at least 20 kg")
	@Max(value = 300, message = "Weight must be at most 300 kg")
	private Integer weight;

	@NotNull(message = "Date of birth is mandatory")
	@PastDate(message = "Date of birth must be a past date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;

	@NotBlank(message = "Gender is mandatory")
	@Pattern(regexp = "Male|Female", message = "Gender must be either 'Male' or 'Female'")
	private String gender;

	@NotBlank(message = "Region of taking healthcare is mandatory")
	private String healthcareRegion;
}
