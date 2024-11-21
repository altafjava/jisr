package com.jisr.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.jisr.dto.MeasurementHistoryDTO;
import com.jisr.entity.MeasurementHistory;
import com.jisr.entity.PatientProfile;
import com.jisr.repository.MeasurementHistoryRepository;
import com.jisr.repository.PatientProfileRepository;
import com.jisr.util.Calculator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MeasurementHistoryService {

	private final PatientProfileRepository patientProfileRepository;
	private final MeasurementHistoryRepository measurementHistoryRepository;

	@Transactional
	public void recordMeasurement(Long patientProfileId, BigDecimal height, BigDecimal weight) {
		PatientProfile patientProfile = patientProfileRepository.findById(patientProfileId)
				.orElseThrow(() -> new IllegalArgumentException("Patient profile not found for patientProfileId: " + patientProfileId));
		MeasurementHistory measurementHistory = new MeasurementHistory();
		measurementHistory.setPatientProfile(patientProfile);
		measurementHistory.setHeight(height);
		measurementHistory.setWeight(weight);
		if (height != null && weight != null) {
			BigDecimal bmi = Calculator.calculateBMI(height, weight);
			measurementHistory.setBmi(bmi);
		}
		measurementHistoryRepository.save(measurementHistory);
	}

	@PreAuthorize("#userId == principal.id")
	public List<MeasurementHistoryDTO> getMeasurementHistory(Long userId) {
		PatientProfile patientProfile = patientProfileRepository.findByUserId(userId)
				.orElseThrow(() -> new IllegalArgumentException("Patient profile not found for UserId: " + userId));
		List<MeasurementHistory> historyList = measurementHistoryRepository.findByPatientProfileIdOrderByCreatedDateDesc(patientProfile.getId());
		return historyList.stream().map(this::toDTO).collect(Collectors.toList());
	}

	private MeasurementHistoryDTO toDTO(MeasurementHistory history) {
		MeasurementHistoryDTO dto = new MeasurementHistoryDTO();
		dto.setHeight(history.getHeight());
		dto.setWeight(history.getWeight());
		dto.setBmi(history.getBmi());
		dto.setRecordedDate(history.getCreatedDate());
		return dto;
	}
}
