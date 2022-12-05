package io.eontimer.service.factory.timer;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.eontimer.util.TimeUtil;

@Service
public class EntralinkTimerFactory {

	@Autowired
	private DelayTimerFactory delayTimer;

	public List<Duration> createStages(long targetSecond, long targetDelay, long calibration, long entralinkCalibration) {
		final List<Duration> stages = delayTimer.createStages(targetSecond, targetDelay, calibration);
		return Arrays.asList(stage1(stages), stage2(stages, entralinkCalibration));
	}

	public long calibrate(long targetDelay, long delayHit) {
		return delayTimer.calibrate(targetDelay, delayHit);
	}

	private Duration stage1(List<Duration> stages) {
		return stages.get(0).plus(TimeUtil.milliseconds(250L));
	}

	private Duration stage2(List<Duration> stages, long entralinkCalibration) {
		return stages.get(1).minus(TimeUtil.milliseconds(entralinkCalibration));
	}

}
