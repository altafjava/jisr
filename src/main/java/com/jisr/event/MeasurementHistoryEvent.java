package com.jisr.event;

import java.math.BigDecimal;
import org.springframework.context.ApplicationEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasurementHistoryEvent extends ApplicationEvent {

	private static final long serialVersionUID = -7409200633909661096L;

	private Long patientProfileId;
	private BigDecimal height;
	private BigDecimal weight;

	public MeasurementHistoryEvent(Object source, Long patientProfileId, BigDecimal height, BigDecimal weight) {
		super(source);
		this.patientProfileId = patientProfileId;
		this.height = height;
		this.weight = weight;
	}
}
