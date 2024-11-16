package com.jisr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.jisr.dto.PatientProfileDTO;
import com.jisr.service.impl.PatientServiceImpl;
import com.jisr.validator.PatchValidationGroup;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

	private final PatientServiceImpl patientService;

	@GetMapping("/{patientId}/profile")
	public ResponseEntity<PatientProfileDTO> getPatientProfile(@PathVariable Long patientId) {
		PatientProfileDTO patientProfileDTO = patientService.getPatientProfile(patientId);
		return ResponseEntity.ok(patientProfileDTO);
	}

	@PatchMapping("/{patientId}/profile")
	public ResponseEntity<?> updatePatientProfile(@PathVariable Long patientId,
			@Validated(PatchValidationGroup.class) @RequestBody PatientProfileDTO patientProfileDTO) {
		patientService.updatePatientProfile(patientId, patientProfileDTO);
		return ResponseEntity.ok("Profile updated successfully.");
	}

	// Upload files (medical reports, etc.)
	@PostMapping("/{patientId}/upload")
	public ResponseEntity<?> uploadPatientFile(@PathVariable Long patientId, @RequestParam("file") MultipartFile file) {
		patientService.uploadPatientFile(patientId, file);
		return ResponseEntity.ok("File uploaded successfully.");
	}
}
