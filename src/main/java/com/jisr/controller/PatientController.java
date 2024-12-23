package com.jisr.controller;

import java.util.List;
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
import com.jisr.dto.MeasurementHistoryDTO;
import com.jisr.dto.PatientProfileDTO;
import com.jisr.model.PatientProfileResponse;
import com.jisr.service.MeasurementHistoryService;
import com.jisr.service.impl.PatientService;
import com.jisr.validator.PatchValidationGroup;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

	private final PatientService patientService;
	private final MeasurementHistoryService measurementHistoryService;

	@GetMapping("/{id}")
	public ResponseEntity<PatientProfileResponse> getPatientProfile(@PathVariable Long id) {
		PatientProfileResponse patientProfileResponse = patientService.getPatientProfile(id);
		return ResponseEntity.ok(patientProfileResponse);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> updatePatientProfile(@PathVariable Long id,
			@Validated(PatchValidationGroup.class) @RequestBody PatientProfileDTO patientProfileDTO) {
		patientService.updatePatientProfile(id, patientProfileDTO);
		return ResponseEntity.ok("Profile updated successfully.");
	}

	@GetMapping("/{id}/measurements")
	public ResponseEntity<List<MeasurementHistoryDTO>> getMeasurementHistory(@PathVariable Long id) {
		List<MeasurementHistoryDTO> history = measurementHistoryService.getMeasurementHistory(id);
		return ResponseEntity.ok(history);
	}

	// Upload files (medical reports, etc.)
	@PostMapping("/{patientId}/upload")
	public ResponseEntity<?> uploadPatientFile(@PathVariable Long patientId, @RequestParam("file") MultipartFile file) {
		patientService.uploadPatientFile(patientId, file);
		return ResponseEntity.ok("File uploaded successfully.");
	}
}
