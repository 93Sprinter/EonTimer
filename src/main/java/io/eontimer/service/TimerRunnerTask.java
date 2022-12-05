package io.eontimer.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import io.eontimer.service.action.TimerActionService;
import io.eontimer.util.TimeUtil;
import javafx.application.Platform;
import javafx.concurrent.Task;

public class TimerRunnerTask extends Task<String> {

	private List<Long> stages;

	private long refreshInterval;

	private TimerActionService timerActionService;

	private TimerRunnerService timerRunnerService;

	private long currentStage;

	public TimerRunnerTask(TimerRunnerService timerRunnerService, TimerActionService timerActionService, long refreshInterval) {
		this.timerRunnerService = timerRunnerService;
		this.timerActionService = timerActionService;
		this.refreshInterval = refreshInterval;
		this.stages = timerRunnerService.getStages();
	}

	@Override
	protected String call() throws Exception {
		Long preElapsed = 0L;
		for (int i = 0; i < stages.size(); i++) {
			preElapsed = runStage(i, preElapsed) - (TimeUtil.getStage(stages, i));
		}
		Platform.runLater(() -> {
			timerRunnerService.resetState(timerRunnerService.getMStages());
		});
		return "";
	}

	private long runStage(int stageIndex, long preElapsed) throws Exception {
		long elapsed = preElapsed;
		long adjustedDelay = refreshInterval;
		long lastTimestamp = Instant.now().toEpochMilli();
		currentStage = TimeUtil.getStage(stages, stageIndex);

		Platform.runLater(() -> {
			timerRunnerService.updateCurrentState(currentStage);
		});

		List<Long> actionInterval = getActionInterval(currentStage);

		while (!isCancelled() && elapsed < currentStage) {
			if (adjustedDelay > 0L) {
				Thread.sleep(adjustedDelay);
			}

			long now = Instant.now().toEpochMilli();
			long delta = now - lastTimestamp;
			adjustedDelay -= (delta - refreshInterval);
			lastTimestamp = Instant.now().toEpochMilli();
			elapsed += delta;

			long currentRemaining = !TimeUtil.isIndefinite(currentStage) ? currentStage - elapsed : elapsed;

			if (!TimeUtil.isIndefinite(currentStage) && currentRemaining <= actionInterval.get(0)) {
				timerActionService.invokeAction();
				actionInterval.remove(0);
			}

			Platform.runLater(() -> {
				timerRunnerService.updateState(stageIndex, currentRemaining, delta);
			});
		}
		return elapsed;
	}

	private List<Long> getActionInterval(long currentStage) {
		return timerActionService.getActionInterval().stream().filter(it -> it < currentStage).collect(Collectors.toList());
	}

}
