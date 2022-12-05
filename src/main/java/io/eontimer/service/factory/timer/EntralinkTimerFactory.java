package io.eontimer.service.factory.timer;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntralinkTimerFactory {

	@Autowired
	private DelayTimerFactory delayTimer;

	public List<Long> createStages(long targetSecond, long targetDelay, long calibration, long entralinkCalibration) {
		final List<Long> stages = delayTimer.createStages(targetSecond, targetDelay, calibration);
		return Arrays.asList(stage1(stages), stage2(stages, entralinkCalibration));
	}

	public long calibrate(long targetDelay, long delayHit) {
		return delayTimer.calibrate(targetDelay, delayHit);
	}

	private long stage1(List<Long> stages) {
		return stages.get(0) + 250L;
	}

	private long stage2(List<Long> stages, long entralinkCalibration) {
		return stages.get(1) - entralinkCalibration;
	}

}
