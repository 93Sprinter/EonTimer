package io.eontimer.service.factory.timer;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import io.eontimer.util.TimeUtil;

@Service
public class SecondTimerFactory {

	public List<Long> createStages(long targetSecond, long calibration) {
		return Arrays.asList(stage1(targetSecond, calibration));
	}

	public long calibrate(long targetSecond, long secondHit) {
		if (secondHit < targetSecond) {
			return (targetSecond - secondHit) * 1000 - 500;
		}
		if (secondHit > targetSecond) {
			return (targetSecond - secondHit) * 1000 + 500;
		}
		return 0L;
	}

	private long stage1(long targetSecond, long calibration) {
		return TimeUtil.toMinimumLength(targetSecond * 1000 + calibration + 200);
	}

}
