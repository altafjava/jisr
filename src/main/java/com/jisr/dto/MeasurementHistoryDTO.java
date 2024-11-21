package com.jisr.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MeasurementHistoryDTO {
	private BigDecimal height;
	private BigDecimal weight;
	private BigDecimal bmi;
	private LocalDateTime recordedDate;
}
