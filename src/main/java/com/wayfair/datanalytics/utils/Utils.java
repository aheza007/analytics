package com.wayfair.datanalytics.utils;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Utils {

	private static DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd HH:mm:ss")
			.appendFraction(ChronoField.MICRO_OF_SECOND, 0, 6, true).toFormatter();

	public static Month getMonth(String data) {
		return LocalDateTime.parse(data, formatter).getMonth();
	}
}
