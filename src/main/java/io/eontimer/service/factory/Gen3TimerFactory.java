package io.eontimer.service.factory;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.eontimer.model.TimerState;
import io.eontimer.model.timer.Gen3TimerMode;
import io.eontimer.model.timer.Gen3TimerModel;
import io.eontimer.service.factory.timer.FixedFrameTimerFactory;
import io.eontimer.service.factory.timer.VariableFrameTimerFactory;
import io.eontimer.util.ReactorFxUtil;

@Component
public class Gen3TimerFactory implements TimerFactory {

	@Autowired
	private TimerState timerState;

	@Autowired
	private Gen3TimerModel gen3TimerModel;

	@Autowired
	private FixedFrameTimerFactory fixedFrameTimerFactory;

	@Autowired
	private VariableFrameTimerFactory variableFrameTimerFactory;

	@PostConstruct
	private void initialize() {
		Arrays.asList(gen3TimerModel.getModeProperty(), gen3TimerModel.getPreTimerProperty(), gen3TimerModel.getCalibrationProperty()).stream().map(it -> ReactorFxUtil.asFlux(it)).forEach(it -> {
			it.subscribe(o -> {
				timerState.update(getStages());
			});
		});
		ReactorFxUtil.asFlux(gen3TimerModel.getTargetFrameProperty()).filter(it -> Gen3TimerMode.STANDARD.equals(gen3TimerModel.getMode())).subscribe(o -> timerState.update(getStages()));
	}

	@Override
	public List<Duration> getStages() {
		switch (gen3TimerModel.getMode()) {
		case STANDARD:
			return fixedFrameTimerFactory.createStages(gen3TimerModel.getPreTimer(), gen3TimerModel.getTargetFrame(), gen3TimerModel.getCalibration());
		case VARIABLE_TARGET:
			return variableFrameTimerFactory.createStages(gen3TimerModel.getPreTimer());
		default:
			throw new RuntimeException("Unsupported value " + gen3TimerModel.getMode());
		}
	}

	@Override
	public void calibrate() {
		// NOTE: VariableFrameTimer is essentially a FixedFrameTimer
		// just with a floating target frame value. Therefore, the
		// calibration process is the same for both.
		gen3TimerModel.setCalibration(gen3TimerModel.getCalibration() + fixedFrameTimerFactory.calibrate(gen3TimerModel.getTargetFrame(), gen3TimerModel.getFrameHit()));
	}

}
