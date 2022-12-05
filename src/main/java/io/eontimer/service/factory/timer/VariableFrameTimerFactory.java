package io.eontimer.service.factory.timer;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import io.eontimer.util.TimeUtil;

@Service
public class VariableFrameTimerFactory {

	public List<Duration> createStages(long preTimer) {
		return Arrays.asList(TimeUtil.milliseconds(preTimer), TimeUtil.INDEFINITE);
	}

}
