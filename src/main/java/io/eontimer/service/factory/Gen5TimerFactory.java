package io.eontimer.service.factory;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.eontimer.model.TimerState;
import io.eontimer.model.timer.Gen5TimerModel;
import io.eontimer.service.CalibrationService;
import io.eontimer.service.factory.timer.DelayTimerFactory;
import io.eontimer.service.factory.timer.EnhancedEntralinkTimerFactory;
import io.eontimer.service.factory.timer.EntralinkTimerFactory;
import io.eontimer.service.factory.timer.SecondTimerFactory;
import io.eontimer.util.ReactorFxUtil;

@Component
public class Gen5TimerFactory implements TimerFactory {

	@Autowired
	private TimerState timerState;

	@Autowired
	private Gen5TimerModel gen5TimerModel;

	@Autowired
	private DelayTimerFactory delayTimerFactory;

	@Autowired
	private SecondTimerFactory secondTimerFactory;

	@Autowired
	private EntralinkTimerFactory entralinkTimerFactory;

	@Autowired
	private EnhancedEntralinkTimerFactory enhancedEntralinkTimerFactory;

	@Autowired
	private CalibrationService calibrationService;

	@Override
	public List<Long> getStages() {
		switch (gen5TimerModel.getMode()) {
		case STANDARD:
			return secondTimerFactory.createStages(gen5TimerModel.getTargetSecond(), calibrationService.calibrateToMillis(gen5TimerModel.getCalibration()));
		case C_GEAR:
			return delayTimerFactory.createStages(gen5TimerModel.getTargetSecond(), gen5TimerModel.getTargetDelay(), calibrationService.calibrateToMillis(gen5TimerModel.getCalibration()));
		case ENTRALINK:
			return entralinkTimerFactory.createStages(gen5TimerModel.getTargetSecond(), gen5TimerModel.getTargetDelay(), calibrationService.calibrateToMillis(gen5TimerModel.getCalibration()),
					calibrationService.calibrateToMillis(gen5TimerModel.getCalibration()));
		case ENHANCED_ENTRALINK:
			return enhancedEntralinkTimerFactory.createStages(gen5TimerModel.getTargetSecond(), gen5TimerModel.getTargetDelay(), gen5TimerModel.getTargetAdvances(),
					calibrationService.calibrateToMillis(gen5TimerModel.getCalibration()), calibrationService.calibrateToMillis(gen5TimerModel.getCalibration()), gen5TimerModel.getCalibration());
		default:
			throw new RuntimeException("Unsupported value " + gen5TimerModel.getMode());
		}
	}

	@Override
	public void calibrate() {
		switch (gen5TimerModel.getMode()) {
		case STANDARD:
			gen5TimerModel.setCalibration(gen5TimerModel.getCalibration() + calibrationService.calibrateToDelays(getSecondCalibration()));
			break;
		case C_GEAR:
			gen5TimerModel.setCalibration(gen5TimerModel.getCalibration() + calibrationService.calibrateToDelays(getDelayCalibration()));
			break;
		case ENTRALINK:
			gen5TimerModel.setCalibration(gen5TimerModel.getCalibration() + calibrationService.calibrateToDelays(getSecondCalibration()));
			gen5TimerModel.setEntralinkCalibration(gen5TimerModel.getEntralinkCalibration() + calibrationService.calibrateToDelays(getEntralinkCalibration()));
			break;
		case ENHANCED_ENTRALINK:
			gen5TimerModel.setCalibration(gen5TimerModel.getCalibration() + calibrationService.calibrateToDelays(getSecondCalibration()));
			gen5TimerModel.setEntralinkCalibration(gen5TimerModel.getEntralinkCalibration() + calibrationService.calibrateToDelays(getEntralinkCalibration()));
			gen5TimerModel.setFrameCalibration(gen5TimerModel.getFrameCalibration() + getAdvancesCalibration());
			break;
		default:
			throw new RuntimeException("Unsupported value " + gen5TimerModel.getMode());
		}

	}

	private long getSecondCalibration() {
		return secondTimerFactory.calibrate(gen5TimerModel.getTargetSecond(), gen5TimerModel.getSecondHit());
	}

	private long getDelayCalibration() {
		return delayTimerFactory.calibrate(gen5TimerModel.getTargetDelay(), gen5TimerModel.getDelayHit());
	}

	private long getEntralinkCalibration() {
		return entralinkTimerFactory.calibrate(gen5TimerModel.getTargetDelay(), gen5TimerModel.getDelayHit() - getSecondCalibration());
	}

	private long getAdvancesCalibration() {
		return enhancedEntralinkTimerFactory.calibrate(gen5TimerModel.getTargetAdvances(), gen5TimerModel.getActualAdvances());
	}

	@PostConstruct
	private void initialize() {
		Arrays.asList(
				gen5TimerModel.getModeProperty(),
				gen5TimerModel.getCalibrationProperty(),
				gen5TimerModel.getTargetDelayProperty(),
				gen5TimerModel.getTargetSecondProperty(),
				gen5TimerModel.getEntralinkCalibrationProperty(),
				gen5TimerModel.getFrameCalibrationProperty(),
				gen5TimerModel.getTargetAdvancesProperty())
				.stream()
				.map(it -> ReactorFxUtil.asFlux(it))
				.forEach(it -> {
					it.subscribe(o -> timerState.update(getStages()));
				});
	}

}
