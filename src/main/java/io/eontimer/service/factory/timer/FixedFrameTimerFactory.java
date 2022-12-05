package io.eontimer.service.factory.timer;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.eontimer.service.CalibrationService;
import io.eontimer.util.TimeUtil;

@Service
public class FixedFrameTimerFactory {

	@Autowired
	private CalibrationService calibrationService;

	public List<Duration> createStages(long preTimer, long targetFrame, long calibration) {
		return Arrays.asList(stage1(preTimer), stage2(targetFrame, calibration));
	}

	public long calibrate(long targetFrame, long frameHit) {
		return calibrationService.toMillis(targetFrame - frameHit);
	}

	private Duration stage1(Long preTimer) {
		return TimeUtil.milliseconds(preTimer);
	}

	private Duration stage2(long targetFrame, long calibration) {
		return TimeUtil.milliseconds(calibrationService.toMillis(targetFrame) + calibration);
	}

}
