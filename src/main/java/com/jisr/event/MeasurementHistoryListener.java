package com.jisr.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.jisr.service.MeasurementHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MeasurementHistoryListener {

	private final MeasurementHistoryService measurementHistoryService;

	@Async
	@EventListener
	public void handleMeasurementEvent(MeasurementHistoryEvent event) {
		measurementHistoryService.recordMeasurement(event.getPatientProfileId(), event.getHeight(), event.getWeight());
	}
}
