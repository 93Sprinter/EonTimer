package io.eontimer.model;

import java.util.List;

import org.springframework.stereotype.Component;

import io.eontimer.util.TimeUtil;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;

@Component
public class TimerState {

	private final LongProperty totalTimeProperty = new SimpleLongProperty(0L);

	private final LongProperty totalElapsedProperty = new SimpleLongProperty(0L);

	private final LongProperty currentStageProperty = new SimpleLongProperty(0L);

	private final LongProperty nextStageProperty = new SimpleLongProperty(0L);

	private final LongProperty currentRemainingProperty = new SimpleLongProperty(0L);

	private final BooleanProperty runningProperty = new SimpleBooleanProperty(false);

	public LongProperty getTotalTimeProperty() {
		return totalTimeProperty;
	}

	public LongProperty getTotalElapsedProperty() {
		return totalElapsedProperty;
	}

	public LongProperty getCurrentStageProperty() {
		return currentStageProperty;
	}

	public LongProperty getNextStageProperty() {
		return nextStageProperty;
	}

	public LongProperty getCurrentRemainingProperty() {
		return currentRemainingProperty;
	}

	public BooleanProperty getRunningProperty() {
		return runningProperty;
	}

	public long getTotalTime() {
		return totalTimeProperty.get();
	}

	public void setTotalTime(long totalTime) {
		totalTimeProperty.set(totalTime);
	}

	public long getTotalElapsed() {
		return totalElapsedProperty.get();
	}

	public void setTotalElapsed(long totalElapsed) {
		totalElapsedProperty.set(totalElapsed);
	}

	public long getCurrentStage() {
		return currentStageProperty.get();
	}

	public void setCurrentStage(long currentStage) {
		currentStageProperty.set(currentStage);
	}

	public long getNextStage() {
		return nextStageProperty.get();
	}

	public void setNextStage(long nextStage) {
		nextStageProperty.set(nextStage);
	}

	public long getCurrentRemaining() {
		return currentRemainingProperty.get();
	}

	public void setCurrentRemaining(long currentRemaining) {
		currentRemainingProperty.set(currentRemaining);
	}

	public boolean isRunning() {
		return runningProperty.get();
	}

	public void setRunning(boolean running) {
		runningProperty.set(running);
	}

	public void update(List<Long> stages) {
		setCurrentStage(TimeUtil.getStage(stages, 0));
		if (TimeUtil.isIndefinite(getCurrentStage())) {
			setCurrentStage(0L);
		} else {
			setCurrentRemaining(getCurrentStage());
		}
		setNextStage(TimeUtil.getStage(stages, 1));
		setTotalTime(TimeUtil.sum(stages));
	}

}
