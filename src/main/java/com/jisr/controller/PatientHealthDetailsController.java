//package com.jisr.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.jisr.dto.PatientHealthDetailsDTO;
//import com.jisr.service.PatientAuthService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/patients")
//public class PatientHealthDetailsController {
//
//	private final PatientAuthService patientService;
//
//	@PostMapping("/{patientId}/health-details")
//	public ResponseEntity<?> patientHealthDetails(@PathVariable Long patientId, @Valid @RequestBody PatientHealthDetailsDTO patientHealthDetailsDTO) {
//		patientService.savePatientHealthDetails(patientId, patientHealthDetailsDTO);
//		return ResponseEntity.ok("Patient Health Details submitted successfully.");
//	}
//}
