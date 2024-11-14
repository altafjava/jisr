package com.jisr.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class PatientDTO {
	private String username;
	private String fullName;
	private String email;
	private String phoneNumber;
	private String cancerType;
	private String cancerTreatment;
	private String medicinesAndDoses;
	private Integer chemotherapyHistory;
	private Boolean hasCentralCatheter;
	private Integer height;
	private Integer weight;
	private Double bmi;
	private LocalDate dateOfBirth;
	private String gender;
	private String healthcareRegion;
	private List<AppointmentDTO> previousAppointments;
	private List<AppointmentDTO> upcomingAppointments;
}
