package io.eontimer.service.factory.timer;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import io.eontimer.util.TimeUtil;

@Service
public class VariableFrameTimerFactory {

	public List<Long> createStages(long preTimer) {
		return Arrays.asList(preTimer, TimeUtil.INDEFINITE);
	}

}
