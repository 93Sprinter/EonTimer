package io.eontimer.service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.eontimer.model.TimerState;
import io.eontimer.model.settings.TimerSettingsModel;
import io.eontimer.service.action.TimerActionService;
import io.eontimer.util.ReactorFxUtil;
import io.eontimer.util.Stack;
import io.eontimer.util.TaskUtil;
import io.eontimer.util.TimeUtil;
import javafx.concurrent.Task;

@Service
public class TimerRunnerService {

	@Autowired
	private TimerState timerState;

	@Autowired
	private TimerSettingsModel timerSettings;

	@Autowired
	private TimerActionService timerActionService;

	private TimerRunnerTask timerTask;

	private List<Duration> stages = new ArrayList<>();

	private List<Duration> mStages = new ArrayList<>();

	public void start(List<Duration> stages) {
		if (!timerState.isRunning()) {
			resetState(stages);

			timerTask = new TimerRunnerTask(stages);
			ReactorFxUtil.asFlux(timerTask.valueProperty(), false)
					.subscribe(it -> {
						if ("resetState".equals(it.getCommand())) {
							timerState.setRunning(false);
							resetState(mStages);
						} else if ("updateCurrentStage".equals(it.getCommand())) {
							timerState.setCurrentStage(it.getCurrentStage());
						} else if ("updateState".equals(it.getCommand())) {
							updateState(it.getStageIndex(), it.getDelta(), it.getElapsed());
							if (!TimeUtil.isIndefinite(timerState.getCurrentStage()) && timerState.getCurrentRemaining().compareTo(it.getActionInterval().peek()) <= 0) {
								timerActionService.invokeAction();
								it.getActionInterval().pop();
							}
						}
					});
			TaskUtil.run(timerTask);

			timerState.setRunning(true);
		}
	}

	public void stop() {
		if (timerState.isRunning()) {
			timerTask.cancel(true);
			timerState.setRunning(false);
			resetState(mStages);
		}
	}

	private Stack<Duration> getActionInterval() {
		return Stack.asStack(timerActionService.getActionInterval().stream().filter(it -> it.compareTo(timerState.getCurrentStage()) < 0).collect(Collectors.toList()));
	}

	private void updateState(int stageIndex, Duration delta, Duration elapsed) {
		timerState.setCurrentStage(TimeUtil.getStage(stages, stageIndex));
		timerState.setCurrentRemaining(!TimeUtil.isIndefinite(timerState.getCurrentStage()) ? timerState.getCurrentStage().minus(elapsed) : elapsed);
		timerState.setTotalElapsed(timerState.getTotalElapsed().plus(delta));
	}

	private void resetState(List<Duration> stages) {
		this.mStages = stages;
		this.stages = stages.stream().collect(Collectors.toList());

		timerState.setTotalTime(TimeUtil.sum(this.mStages));
		timerState.setTotalElapsed(Duration.ZERO);
		timerState.setCurrentStage(TimeUtil.getStage(this.mStages, 0));
		timerState.setCurrentRemaining(!TimeUtil.isIndefinite(timerState.getCurrentStage()) ? timerState.getCurrentStage() : Duration.ZERO);
		timerState.setNextStage(TimeUtil.getStage(this.mStages, 1));
	}

	public List<Duration> getStages() {
		return stages;
	}

	public static class TimerRunnerTaskData {

		private String command;

		private Duration currentStage;

		private int stageIndex;

		private Duration delta;

		private Duration elapsed;

		private Stack<Duration> actionInterval;

		public String getCommand() {
			return command;
		}

		public void setCommand(String command) {
			this.command = command;
		}

		public Duration getCurrentStage() {
			return currentStage;
		}

		public void setCurrentStage(Duration currentStage) {
			this.currentStage = currentStage;
		}

		public int getStageIndex() {
			return stageIndex;
		}

		public void setStageIndex(int stageIndex) {
			this.stageIndex = stageIndex;
		}

		public Duration getDelta() {
			return delta;
		}

		public void setDelta(Duration delta) {
			this.delta = delta;
		}

		public Duration getElapsed() {
			return elapsed;
		}

		public void setElapsed(Duration elapsed) {
			this.elapsed = elapsed;
		}

		public Stack<Duration> getActionInterval() {
			return actionInterval;
		}

		public void setActionInterval(Stack<Duration> actionInterval) {
			this.actionInterval = actionInterval;
		}

		public static TimerRunnerTaskData create() {
			return new TimerRunnerTaskData();
		}

		public TimerRunnerTaskData addCommand(String command) {
			setCommand(command);
			return this;
		}

		public TimerRunnerTaskData addCurrentStage(Duration currentStage) {
			setCurrentStage(currentStage);
			return this;
		}

		public TimerRunnerTaskData addStageIndex(int stageIndex) {
			setStageIndex(stageIndex);
			return this;
		}

		public TimerRunnerTaskData addDelta(Duration delta) {
			setDelta(delta);
			return this;
		}

		public TimerRunnerTaskData addElapsed(Duration elapsed) {
			setElapsed(elapsed);
			return this;
		}

		public TimerRunnerTaskData addActionInterval(Stack<Duration> actionInterval) {
			setActionInterval(actionInterval);
			return this;
		}

	}

	public class TimerRunnerTask extends Task<TimerRunnerTaskData> {

		private List<Duration> stages;

		public TimerRunnerTask(List<Duration> stages) {
			this.stages = stages;
		}

		@Override
		protected TimerRunnerTaskData call() throws Exception {
			Duration preElapsed = Duration.ZERO;
			for (int i = 0; i < stages.size(); i++) {
				preElapsed = runStage(i, preElapsed).minus(TimeUtil.getStage(stages, i));
			}
			updateValue(TimerRunnerTaskData.create().addCommand("resetState"));
			return TimerRunnerTaskData.create();
		}

		private Duration runStage(int stageIndex, Duration preElapsed) throws Exception {
			Duration elapsed = Duration.ofMillis(preElapsed.toMillis());
			Duration adjustedDelay = TimeUtil.milliseconds(timerSettings.getRefreshInterval());
			Instant lastTimestamp = Instant.now();

			updateValue(TimerRunnerTaskData.create().addCommand("updateCurrentStage").addCurrentStage(TimeUtil.getStage(stages, stageIndex)));

			Stack<Duration> actionInterval = getActionInterval();

			while (!isCancelled() && elapsed.compareTo(timerState.getCurrentStage()) < 0) {
				if (Duration.ZERO.compareTo(adjustedDelay) < 0) {
					Thread.sleep(adjustedDelay.toMillis());
				}

				Instant now = Instant.now();
				Duration delta = Duration.between(lastTimestamp, now);
				adjustedDelay = adjustedDelay.minus(delta.minus(TimeUtil.milliseconds(timerSettings.getRefreshInterval())));
				lastTimestamp = Instant.now();
				elapsed = elapsed.plus(delta);

				updateValue(TimerRunnerTaskData.create().addCommand("updateState").addStageIndex(stageIndex).addDelta(delta).addElapsed(elapsed).addActionInterval(actionInterval));

//				if (!TimeUtil.isIndefinite(timerState.getCurrentStage()) && timerState.getCurrentRemaining().compareTo(actionInterval.peek()) <= 0) {
//					System.out.println(actionInterval.size());
//					timerActionService.invokeAction();
//					actionInterval.pop();
//				}
			}
			return elapsed;
		}

	}

}
