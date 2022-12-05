package io.eontimer.service.factory.timer;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.eontimer.model.timer.TimerConstants;
import io.eontimer.service.CalibrationService;
import io.eontimer.util.TimeUtil;

@Service
public class DelayTimerFactory {

	@Autowired
	private SecondTimerFactory secondTimer;

	@Autowired
	private CalibrationService calibrationService;

	public List<Long> createStages(long targetSecond, long targetDelay, long calibration) {
		return Arrays.asList(stage1(targetSecond, targetDelay, calibration), stage2(targetDelay, calibration));
	}

	public long calibrate(long targetDelay, long delayHit) {
		final long delta = calibrationService.toMillis(delayHit) - calibrationService.toMillis(targetDelay);
		if (Math.abs(delta) <= TimerConstants.CLOSE_THRESHOLD) {
			return (long) (TimerConstants.CLOSE_UPDATE_FACTOR * delta);
		}
		return ((long) TimerConstants.UPDATE_FACTOR) * delta;
	}

	private long stage1(long targetSecond, long targetDelay, long calibration) {
		return TimeUtil.toMinimumLength(secondTimer.createStages(targetSecond, calibration).get(0) - calibrationService.toMillis(targetDelay));
	}

	private long stage2(long targetDelay, long calibration) {
		return calibrationService.toMillis(targetDelay) - calibration;
	}

}
