package io.eontimer.model;

import java.time.Duration;
import java.util.List;

import org.springframework.stereotype.Component;

import io.eontimer.util.TimeUtil;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

@Component
public class TimerState {

	private final ObjectProperty<Duration> totalTimeProperty = new SimpleObjectProperty<>(Duration.ZERO);

	private final ObjectProperty<Duration> totalElapsedProperty = new SimpleObjectProperty<>(Duration.ZERO);

	private final ObjectProperty<Duration> currentStageProperty = new SimpleObjectProperty<>(Duration.ZERO);

	private final ObjectProperty<Duration> nextStageProperty = new SimpleObjectProperty<>(Duration.ZERO);

	private final ObjectProperty<Duration> currentRemainingProperty = new SimpleObjectProperty<>(Duration.ZERO);

	private final BooleanProperty runningProperty = new SimpleBooleanProperty(false);

	public ObjectProperty<Duration> getTotalTimeProperty() {
		return totalTimeProperty;
	}

	public ObjectProperty<Duration> getTotalElapsedProperty() {
		return totalElapsedProperty;
	}

	public ObjectProperty<Duration> getCurrentStageProperty() {
		return currentStageProperty;
	}

	public ObjectProperty<Duration> getNextStageProperty() {
		return nextStageProperty;
	}

	public ObjectProperty<Duration> getCurrentRemainingProperty() {
		return currentRemainingProperty;
	}

	public BooleanProperty getRunningProperty() {
		return runningProperty;
	}

	public Duration getTotalTime() {
		return totalTimeProperty.get();
	}

	public void setTotalTime(Duration totalTime) {
		totalTimeProperty.set(totalTime);
	}

	public Duration getTotalElapsed() {
		return totalElapsedProperty.get();
	}

	public void setTotalElapsed(Duration totalElapsed) {
		totalElapsedProperty.set(totalElapsed);
	}

	public Duration getCurrentStage() {
		return currentStageProperty.get();
	}

	public void setCurrentStage(Duration currentStage) {
		currentStageProperty.set(currentStage);
	}

	public Duration getNextStage() {
		return nextStageProperty.get();
	}

	public void setNextStage(Duration nextStage) {
		nextStageProperty.set(nextStage);
	}

	public Duration getCurrentRemaining() {
		return currentRemainingProperty.get();
	}

	public void setCurrentRemaining(Duration currentRemaining) {
		currentRemainingProperty.set(currentRemaining);
	}

	public boolean isRunning() {
		return runningProperty.get();
	}

	public void setRunning(boolean running) {
		runningProperty.set(running);
	}

	public void update(List<Duration> stages) {
		setCurrentStage(TimeUtil.getStage(stages, 0));
		if (TimeUtil.isIndefinite(getCurrentStage())) {
			setCurrentStage(Duration.ZERO);
		} else {
			setCurrentRemaining(getCurrentStage());
		}
		setNextStage(TimeUtil.getStage(stages, 1));
		setTotalTime(TimeUtil.sum(stages));
	}

}
