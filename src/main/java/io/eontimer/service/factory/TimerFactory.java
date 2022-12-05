package io.eontimer.service.factory;

import java.util.List;

public interface TimerFactory {

	public List<Long> getStages();

	public void calibrate();

}
