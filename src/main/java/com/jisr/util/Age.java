package com.jisr.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Age {
	private YMD ymd;
	private int months;
	private int daysLeftInMonth;
	private int weeks;
	private int daysLeftInWeek;
	private long days;
	private long hours;
	private long minutes;
	private long seconds;
}
