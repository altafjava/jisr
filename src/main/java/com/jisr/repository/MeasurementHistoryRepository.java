package com.jisr.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jisr.entity.MeasurementHistory;

public interface MeasurementHistoryRepository extends JpaRepository<MeasurementHistory, Long> {
	List<MeasurementHistory> findByPatientProfileId(Long patientProfileId);

	List<MeasurementHistory> findByPatientProfileIdOrderByCreatedDateDesc(Long patientProfileId);
}
