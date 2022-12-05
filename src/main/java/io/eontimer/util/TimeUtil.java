package io.eontimer.util;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import io.eontimer.model.settings.TimerSettingsConstants;

public class TimeUtil {

	public static Duration INDEFINITE = Duration.between(Instant.MIN, Instant.MAX);

	public static Duration milliseconds(long value) {
		return Duration.ofMillis(value);
	}

	public static boolean isIndefinite(Duration value) {
		return INDEFINITE.equals(value);
	}

	public static Duration getStage(List<Duration> stages, int index) {
		if (index < stages.size()) {
			return stages.get(index);
		}
		return Duration.ZERO;
	}

	public static Duration sum(List<Duration> values) {
		if (values.contains(INDEFINITE)) {
			return Duration.ofMillis(values.stream().collect(Collectors.summingLong(Duration::toMillis)));
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
