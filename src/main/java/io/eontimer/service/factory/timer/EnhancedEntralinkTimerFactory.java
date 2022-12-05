package io.eontimer.service.factory.timer;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.eontimer.model.timer.Gen5TimerConstants;

@Service
public class EnhancedEntralinkTimerFactory {

	@Autowired
	private EntralinkTimerFactory entralinkTimer;

	public List<Long> createStages(long targetSecond, long targetDelay, long targetAdvances, long calibration, long entralinkCalibration, long frameCalibration) {
		final List<Long> stages = entralinkTimer.createStages(targetSecond, targetDelay, calibration, entralinkCalibration);
		return Arrays.asList(stages.get(0), stages.get(1), stage3(targetAdvances, frameCalibration));
	}

	public long calibrate(long targetAdvances, long actualAdvances) {
		return Math.round((targetAdvances - actualAdvances) / Gen5TimerConstants.ENTRALINK_FRAME_RATE) * 1000;

		// TODO: 4/1/19 - determine if this is still needed
		// val npcRate = 1.0 / calibrationService.toMillis(32)
		// return Math.round((targetAdvances - actualAdvances) /
		// (Gen5TimerConstants.ENTRALINK_FRAME_RATE + (npcCount * npcRate))) * 1000
	}

	private long stage3(long targetAdvances, long frameCalibration) {
		return Math.round(targetAdvances / Gen5TimerConstants.ENTRALINK_FRAME_RATE) * 1000 + frameCalibration;
	}

}
