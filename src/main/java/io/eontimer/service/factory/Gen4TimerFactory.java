package io.eontimer.service.factory;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.eontimer.model.TimerState;
import io.eontimer.model.timer.Gen4TimerModel;
import io.eontimer.service.CalibrationService;
import io.eontimer.service.factory.timer.DelayTimerFactory;
import io.eontimer.util.ReactorFxUtil;

@Component
public class Gen4TimerFactory implements TimerFactory {

	@Autowired
	private TimerState timerState;

	@Autowired
	private Gen4TimerModel gen4TimerModel;

	@Autowired
	private DelayTimerFactory delayTimerFactory;

	@Autowired
	private CalibrationService calibrationService;

	@Override
	public List<Duration> getStages() {
		return delayTimerFactory.createStages(
				gen4TimerModel.getTargetSecond(),
				gen4TimerModel.getTargetDelay(),
				getCalibration());
	}

	@Override
	public void calibrate() {
		gen4TimerModel.setCalibratedDelay(
				gen4TimerModel.getCalibratedDelay() + calibrationService.calibrateToDelays(delayTimerFactory.calibrate(gen4TimerModel.getTargetDelay(), gen4TimerModel.getDelayHit())));
	}

	private long getCalibration() {
		return calibrationService.createCalibration(gen4TimerModel.getCalibratedDelay(), gen4TimerModel.getCalibratedSecond());
	}

	@PostConstruct
	private void initialize() {
		Arrays.asList(
				gen4TimerModel.getCalibratedDelayProperty(),
				gen4TimerModel.getCalibratedSecondProperty(),
				gen4TimerModel.getTargetDelayProperty(),
				gen4TimerModel.getTargetSecondProperty()).stream()
				.map(it -> ReactorFxUtil.asFlux(it))
				.forEach(it -> {
					it.subscribe(o -> timerState.update(getStages()));
				});
	}

}
