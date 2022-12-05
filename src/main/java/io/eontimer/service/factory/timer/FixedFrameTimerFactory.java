package io.eontimer.service.factory.timer;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.eontimer.service.CalibrationService;

@Service
public class FixedFrameTimerFactory {

	@Autowired
	private CalibrationService calibrationService;

	public List<Long> createStages(long preTimer, long targetFrame, long calibration) {
		return Arrays.asList(stage1(preTimer), stage2(targetFrame, calibration));
	}

	public long calibrate(long targetFrame, long frameHit) {
		return calibrationService.toMillis(targetFrame - frameHit);
	}

	private long stage1(long preTimer) {
		return preTimer;
	}

	private long stage2(long targetFrame, long calibration) {
		return calibrationService.toMillis(targetFrame) + calibration;
	}

}
