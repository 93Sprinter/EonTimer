package io.eontimer.util;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import io.eontimer.model.settings.TimerSettingsConstants;

public class TimeUtil {

	public static long INDEFINITE = Long.MAX_VALUE;

	public static boolean isIndefinite(long value) {
		return Long.MAX_VALUE == value;
	}

	public static Long getStage(List<Long> stages, int index) {
		if (index < stages.size()) {
			return stages.get(index);
		}
		return 0L;
	}

	public static long sum(List<Long> values) {
		if (!values.contains(INDEFINITE)) {
			return values.stream().collect(Collectors.summingLong(Long::longValue));
		}
		return INDEFINITE;
	}

	public static long toMinimumLength(long value) {
		while (value < TimerSettingsConstants.MINIMUM_LENGTH) {
			value += Duration.ofMinutes(1L).toMillis();
		}
		return value;
	}

}
