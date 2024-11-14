package com.jisr.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class AppointmentDTO {
	private LocalDate date;
	private String healthcareProvider;
	private String followUpInstructions;
}
