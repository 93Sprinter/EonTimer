package io.eontimer.model.timer;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Gen3TimerModel {

	private final ObjectProperty<Gen3TimerMode> modeProperty = new SimpleObjectProperty<>(Gen3TimerConstants.DEFAULT_MODE);

	private final LongProperty calibrationProperty = new SimpleLongProperty(Gen3TimerConstants.DEFAULT_CALIBRATION);

	private final LongProperty preTimerProperty = new SimpleLongProperty(Gen3TimerConstants.DEFAULT_PRE_TIMER);

	private final LongProperty targetFrameProperty = new SimpleLongProperty(Gen3TimerConstants.DEFAULT_TARGET_FRAME);

	private final LongProperty frameHitProperty = new SimpleLongProperty();

	public ObjectProperty<Gen3TimerMode> getModeProperty() {
		return modeProperty;
	}

	public LongProperty getCalibrationProperty() {
		return calibrationProperty;
	}

	public LongProperty getPreTimerProperty() {
		return preTimerProperty;
	}

	public LongProperty getTargetFrameProperty() {
		return targetFrameProperty;
	}

	public LongProperty getFrameHitProperty() {
		return frameHitProperty;
	}

	public Gen3TimerMode getMode() {
		return modeProperty.get();
	}

	public void setMode(Gen3TimerMode mode) {
		modeProperty.set(mode);
	}

	public long getCalibration() {
		return calibrationProperty.get();
	}

	public void setCalibration(long calibration) {
		calibrationProperty.set(calibration);
	}

	public long getPreTimer() {
		return preTimerProperty.get();
	}

	public void setPreTimer(long preTimer) {
		preTimerProperty.set(preTimer);
	}

	public long getTargetFrame() {
		return targetFrameProperty.get();
	}

	public void setTargetFrame(long targetFrame) {
		targetFrameProperty.set(targetFrame);
	}

	public long getFrameHit() {
		return frameHitProperty.get();
	}

	public void setFrameHit(long frameHit) {
		frameHitProperty.set(frameHit);
	}
}
