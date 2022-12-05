package io.eontimer.service.factory;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.eontimer.model.TimerState;
import io.eontimer.model.timer.CustomTimerModel;
import io.eontimer.util.ReactorFxUtil;

@Component
public class CustomTimerFactory implements TimerFactory {

	@Autowired
	private TimerState timerState;

	@Autowired
	private CustomTimerModel customTimerModel;

	@Override
	public List<Duration> getStages() {
		return customTimerModel.getStages()
				.stream()
				.map(it -> Duration.ofMillis(it.getLength()))
				.collect(Collectors.toList());
	}

	@Override
	public void calibrate() {
		// do nothing
	}

	@PostConstruct
	private void initialize() {
		ReactorFxUtil.asFlux(customTimerModel.getStages())
				.subscribe(it -> timerState.update(getStages()));
	}

}
