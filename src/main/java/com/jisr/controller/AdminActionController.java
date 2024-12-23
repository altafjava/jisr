package com.jisr.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jisr.constant.ProviderStatus;
import com.jisr.dto.HealthProviderActionDTO;
import com.jisr.service.HealthcareProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admins/actions")
public class AdminActionController {

	private final HealthcareProviderService providerService;

	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<String>> getAllProviderActions() {
		List<String> statuses = Arrays.stream(ProviderStatus.values())
				.map(ProviderStatus::asString)
				.collect(Collectors.toList());
		return ResponseEntity.ok(statuses);
	}

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> doAction(@RequestBody @Valid HealthProviderActionDTO healthProviderActionDTO) {
		providerService.handleProviderAction(healthProviderActionDTO);
		log.info("Successfully performed action {} on provider with userId: {}", healthProviderActionDTO.getAction(), healthProviderActionDTO.getUserId());
		return ResponseEntity.ok("Provider action completed successfully.");
	}

}
