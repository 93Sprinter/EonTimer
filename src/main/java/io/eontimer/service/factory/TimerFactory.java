package io.eontimer.service.factory;

import java.time.Duration;
import java.util.List;

public interface TimerFactory {

	public List<Duration> getStages();

	public void calibrate();

}
