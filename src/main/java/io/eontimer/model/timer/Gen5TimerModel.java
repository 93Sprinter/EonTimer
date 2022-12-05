package io.eontimer.model.timer;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Gen5TimerModel {

	private final ObjectProperty<Gen5TimerMode> modeProperty = new SimpleObjectProperty<>(Gen5TimerConstants.DEFAULT_MODE);

	private final LongProperty calibrationProperty = new SimpleLongProperty(Gen5TimerConstants.DEFAULT_CALIBRATION);

	private final LongProperty targetDelayProperty = new SimpleLongProperty(Gen5TimerConstants.DEFAULT_TARGET_DELAY);

	private final LongProperty targetSecondProperty = new SimpleLongProperty(Gen5TimerConstants.DEFAULT_TARGET_SECOND);

	private final LongProperty entralinkCalibrationProperty = new SimpleLongProperty(Gen5TimerConstants.DEFAULT_ENTRALINK_CALIBRATION);

	private final LongProperty frameCalibrationProperty = new SimpleLongProperty(Gen5TimerConstants.DEFAULT_FRAME_CALIBRATION);

	private final LongProperty targetAdvancesProperty = new SimpleLongProperty(Gen5TimerConstants.DEFAULT_TARGET_ADVANCES);

	private final LongProperty secondHitProperty = new SimpleLongProperty();

	private final LongProperty delayHitProperty = new SimpleLongProperty();

	private final LongProperty actualAdvancesProperty = new SimpleLongProperty();

	public ObjectProperty<Gen5TimerMode> getModeProperty() {
		return modeProperty;
	}

	public LongProperty getCalibrationProperty() {
		return calibrationProperty;
	}

	public LongProperty getTargetDelayProperty() {
		return targetDelayProperty;
	}

	public LongProperty getTargetSecondProperty() {
		return targetSecondProperty;
	}

	public LongProperty getEntralinkCalibrationProperty() {
		return entralinkCalibrationProperty;
	}

	public LongProperty getFrameCalibrationProperty() {
		return frameCalibrationProperty;
	}

	public LongProperty getTargetAdvancesProperty() {
		return targetAdvancesProperty;
	}

	public LongProperty getSecondHitProperty() {
		return secondHitProperty;
	}

	public LongProperty getDelayHitProperty() {
		return delayHitProperty;
	}

	public LongProperty getActualAdvancesProperty() {
		return actualAdvancesProperty;
	}

	public Gen5TimerMode getMode() {
		return modeProperty.get();
	}

	public void setMode(Gen5TimerMode mode) {
		modeProperty.set(mode);
	}

	public long getCalibration() {
		return calibrationProperty.get();
	}

	public void setCalibration(long calibration) {
		calibrationProperty.set(calibration);
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

	public long getEntralinkCalibration() {
		return entralinkCalibrationProperty.get();
	}

	public void setEntralinkCalibration(long entralinkCalibration) {
		entralinkCalibrationProperty.set(entralinkCalibration);
	}

	public long getFrameCalibration() {
		return frameCalibrationProperty.get();
	}

	public void setFrameCalibration(long frameCalibration) {
		frameCalibrationProperty.set(frameCalibration);
	}

	public long getTargetAdvances() {
		return targetAdvancesProperty.get();
	}

	public void setTargetAdvances(long targetAdvances) {
		targetAdvancesProperty.set(targetAdvances);
	}

	public long getSecondHit() {
		return secondHitProperty.get();
	}

	public void setSecondHit(long secondHit) {
		secondHitProperty.set(secondHit);
	}

	public long getDelayHit() {
		return delayHitProperty.get();
	}

	public void setDelayHit(long delayHit) {
		delayHitProperty.set(delayHit);
	}

	public long getActualAdvances() {
		return actualAdvancesProperty.get();
	}

	public void setActualAdvances(long actualAdvances) {
		actualAdvancesProperty.set(actualAdvances);
	}

}
