package io.eontimer.model.timer;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Gen4TimerModel {

	private final ObjectProperty<Gen4TimerMode> modeProperty = new SimpleObjectProperty<>(Gen4TimerConstants.DEFAULT_MODE);

	private final LongProperty calibratedDelayProperty = new SimpleLongProperty(Gen4TimerConstants.DEFAULT_CALIBRATED_DELAY);

	private final LongProperty calibratedSecondProperty = new SimpleLongProperty(Gen4TimerConstants.DEFAULT_CALIBRATED_SECOND);

	private final LongProperty targetDelayProperty = new SimpleLongProperty(Gen4TimerConstants.DEFAULT_TARGET_DELAY);

	private final LongProperty targetSecondProperty = new SimpleLongProperty(Gen4TimerConstants.DEFAULT_TARGET_SECOND);

	private final LongProperty delayHitProperty = new SimpleLongProperty();

	public ObjectProperty<Gen4TimerMode> getModeProperty() {
		return modeProperty;
	}

	public LongProperty getCalibratedDelayProperty() {
		return calibratedDelayProperty;
	}

	public LongProperty getCalibratedSecondProperty() {
		return calibratedSecondProperty;
	}

	public LongProperty getTargetDelayProperty() {
		return targetDelayProperty;
	}

	public LongProperty getTargetSecondProperty() {
		return targetSecondProperty;
	}

	public LongProperty getDelayHitProperty() {
		return delayHitProperty;
	}

	public Gen4TimerMode getMode() {
		return modeProperty.get();
	}

	public void setMode(Gen4TimerMode mode) {
		modeProperty.set(mode);
	}

	public long getCalibratedDelay() {
		return calibratedDelayProperty.get();
	}

	public void setCalibratedDelay(long calibratedDelay) {
		calibratedDelayProperty.set(calibratedDelay);
	}

	public long getCalibratedSecond() {
		return calibratedSecondProperty.get();
	}

	public void setCalibratedSecond(long calibratedSecond) {
		calibratedSecondProperty.set(calibratedSecond);
	}

	public long getTargetDelay() {
		return targetDelayProperty.get();
	}

	public void setTargetDelay(long targetDelay) {
		targetDelayProperty.set(targetDelay);
	}

	public long getTargetSecond() {
		return targetSecondProperty.get();
	}

	public void setTargetSecond(long targetSecond) {
		targetSecondProperty.set(targetSecond);
	}

	public long getDelayHit() {
		return delayHitProperty.get();
	}

	public void setDelayHit(long delayHit) {
		delayHitProperty.set(delayHit);
	}

}
