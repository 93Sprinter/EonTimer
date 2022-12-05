package io.eontimer.service.factory;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.eontimer.model.ApplicationModel;
import io.eontimer.model.TimerState;
import io.eontimer.model.settings.TimerSettingsModel;
import io.eontimer.model.timer.TimerType;
import io.eontimer.util.ReactorFxUtil;

@Component
public class TimerFactoryService {

	@Autowired
	private TimerState timerState;

	@Autowired
	private TimerFactory gen3TimerFactory;

	@Autowired
	private TimerFactory gen4TimerFactory;

	@Autowired
	private TimerFactory gen5TimerFactory;

	@Autowired
	private TimerFactory customTimerFactory;

	@Autowired
	private ApplicationModel applicationModel;

	@Autowired
	private TimerSettingsModel timerSettings;

	public List<Long> getStages() {
		return getTimerFactory().getStages();
	}

	public TimerFactory getTimerFactory() {
		return findTimerFactory(applicationModel.getSelectedTimerType());
	}

	public void calibrate() {
		getTimerFactory().calibrate();
	}

	private TimerFactory findTimerFactory(TimerType type) {
		switch (type) {
		case CUSTOM:
			return customTimerFactory;
		case GEN3:
			return gen3TimerFactory;
		case GEN4:
			return gen4TimerFactory;
		case GEN5:
			return gen5TimerFactory;
		default:
			throw new RuntimeException("Unsupported value " + type);
		}
	}

	@PostConstruct
	private void initialize() {
		ReactorFxUtil.asFlux(applicationModel.getSelectedTimerTypeProperty())
				.map(it -> findTimerFactory(it))
				.map(TimerFactory::getStages)
				.subscribe(it -> timerState.update(it));

		Arrays.asList(
				timerSettings.getConsoleProperty(),
				timerSettings.getPrecisionCalibrationModeProperty())
				.stream()
				.map(it -> ReactorFxUtil.asFlux(it))
				.forEach(it -> {
					it.subscribe(o -> timerState.update(getStages()));
				});
	}

}
