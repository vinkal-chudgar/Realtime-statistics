package io.chudgar.vinkal.common.utils;

import java.time.Instant;

public class DateTimeUtil {

	public static boolean isWithinInterval(long timeStamp) {
		return Instant.now().getEpochSecond() - getSecond(timeStamp) <= 60;
	}

	public static long getSecond(long timeStamp) {
		return Instant.ofEpochMilli(timeStamp).getEpochSecond();
	}

}
