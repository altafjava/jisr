package com.jisr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.jisr.constant.FileCategory;
import com.jisr.dto.HealthcareProviderDTO;
import com.jisr.model.HealthcareProviderProfileResponse;
import com.jisr.service.HealthcareProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class HealthcareProviderController {

	private final HealthcareProviderService providerService;

	@PreAuthorize("#id == principal.id")
	@GetMapping("/{id}")
	public ResponseEntity<HealthcareProviderProfileResponse> getHealthcareProviderProfile(@PathVariable Long id) {
		HealthcareProviderProfileResponse patientProfileResponse = providerService.getHealthcareProviderProfile(id);
		return ResponseEntity.ok(patientProfileResponse);
	}

	@PreAuthorize("#id == principal.id")
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateHealthcareProvider(@PathVariable Long id, @Valid @RequestBody HealthcareProviderDTO dto) {
		providerService.updateHealthcareProvider(id, dto);
		return ResponseEntity.ok("Profile updated successfully.");
	}

	/**
	 * In Next.js, use an <input type="file"> to select & upload the file via /providers/{id}/upload Frontend will receive
	 * the stored location file URL Frontend will call PATCH API /providers/{id} by attaching the URL.
	 */
	@PreAuthorize("#id == principal.id")
	@PostMapping("/{id}/upload")
	public ResponseEntity<String> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file,
			@RequestParam("type") @Valid FileCategory fileCategory) {
		String fileUrl = providerService.uploadFile(id, file, fileCategory);
		return ResponseEntity.ok(fileUrl);
	}

}
