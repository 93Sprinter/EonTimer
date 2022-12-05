package io.eontimer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.eontimer.model.settings.TimerSettingsModel;

@Service
public class CalibrationService {

	@Autowired
	private TimerSettingsModel timerSettingsModel;

	public long toMillis(long delays) {
		return Math.round(delays * timerSettingsModel.getConsole().getFrameRate());
	}

	public long toDelays(long millis) {
		return Math.round(millis / timerSettingsModel.getConsole().getFrameRate());
	}

	public long createCalibration(long delay, long second) {
		return toMillis(delay - toDelays(second * 1000));
	}

	public long calibrateToMillis(long value) {
		return (timerSettingsModel.isPrecisionCalibrationMode()) ? value : toMillis(value);
	}

	public long calibrateToDelays(long value) {
		return (timerSettingsModel.isPrecisionCalibrationMode()) ? value : toDelays(value);

	}

}
