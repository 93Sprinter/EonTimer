package io.eontimer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.eontimer.model.TimerState;
import io.eontimer.model.settings.TimerSettingsModel;
import io.eontimer.service.action.TimerActionService;
import io.eontimer.util.TaskUtil;
import io.eontimer.util.TimeUtil;

@Service
public class TimerRunnerService {

	@Autowired
	private TimerState timerState;

	@Autowired
	private TimerSettingsModel timerSettings;

	@Autowired
	private TimerActionService timerActionService;

	private TimerRunnerTask timerTask;

	private List<Long> stages = new ArrayList<>();

	private List<Long> mStages = new ArrayList<>();

	public void start(List<Long> stages) {
		if (!timerState.isRunning()) {
			resetState(stages);

			timerTask = new TimerRunnerTask(this, timerActionService, timerSettings.getRefreshInterval());
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

	public void updateState(long stageIndex, long currentRemaining, long delta) {
		timerState.setCurrentStage(TimeUtil.getStage(stages, (int) stageIndex));
		timerState.setCurrentRemaining(currentRemaining);
		timerState.setTotalElapsed(timerState.getTotalElapsed() + delta);
	}

	public void updateCurrentState(long currentStage) {
		timerState.setCurrentStage(currentStage);
	}

	public void resetState(List<Long> stages) {
		timerState.setRunning(false);

		this.mStages = stages;
		this.stages = stages.stream().collect(Collectors.toList());

		timerState.setTotalTime(TimeUtil.sum(this.mStages));
		timerState.setTotalElapsed(0L);
		timerState.setCurrentStage(TimeUtil.getStage(this.mStages, 0));
		timerState.setCurrentRemaining(!TimeUtil.isIndefinite(timerState.getCurrentStage()) ? timerState.getCurrentStage() : 0L);
		timerState.setNextStage(TimeUtil.getStage(this.mStages, 1));
	}

	public List<Long> getStages() {
		return stages;
	}

	public List<Long> getMStages() {
		return mStages;
	}

}
