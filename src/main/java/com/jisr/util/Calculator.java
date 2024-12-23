package com.jisr.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Calculator {

	public static Age calculateAge(LocalDate start, LocalDate end) {
		if (start == null || end == null) {
			throw new IllegalArgumentException("Start and end dates cannot be null");
		}
		if (end.isBefore(start)) {
			throw new IllegalArgumentException("End date cannot be before start date");
		}
		// Calculate years, months, and days
		Period period = Period.between(start, end);
		YMD ymd = new YMD(period.getYears(), period.getMonths(), period.getDays());
		// Calculate total days, weeks, and other units
		long days = ChronoUnit.DAYS.between(start, end);
		int weeks = (int) days / 7;
		int daysLeftInWeek = (int) days % 7;
		int months = period.getYears() * 12 + period.getMonths();
		int daysLeftInMonth = period.getDays();
		long hours = days * 24;
		long minutes = hours * 60;
		long seconds = minutes * 60;
		// Prepare the result
		Age result = new Age();
		result.setYmd(ymd);
		result.setMonths(months);
		result.setDaysLeftInMonth(daysLeftInMonth);
		result.setWeeks(weeks);
		result.setDaysLeftInWeek(daysLeftInWeek);
		result.setDays(days);
		result.setHours(hours);
		result.setMinutes(minutes);
		result.setSeconds(seconds);
		return result;
	}

	public static BigDecimal calculateBMI(BigDecimal height, BigDecimal weight) {
		if (height == null || weight == null || height.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Height and weight must be non-zero positive values.");
		}
		return weight.divide(height.pow(2), MathContext.DECIMAL32).multiply(BigDecimal.valueOf(10000));
	}

}
