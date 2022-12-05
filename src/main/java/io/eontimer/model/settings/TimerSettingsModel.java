package io.eontimer.model.settings;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

public class TimerSettingsModel {

	private final ObjectProperty<Console> consoleProperty = new SimpleObjectProperty<Console>(TimerSettingsConstants.DEFAULT_CONSOLE);

	private final LongProperty refreshIntervalProperty = new SimpleLongProperty(TimerSettingsConstants.DEFAULT_REFRESH_INTERVAL);

	private final BooleanProperty precisionCalibrationModeProperty = new SimpleBooleanProperty(TimerSettingsConstants.DEFAULT_PRECISION_CALIBRATION_MODE);

	public ObjectProperty<Console> getConsoleProperty() {
		return consoleProperty;
	}

	public LongProperty getRefreshIntervalProperty() {
		return refreshIntervalProperty;
	}

	public BooleanProperty getPrecisionCalibrationModeProperty() {
		return precisionCalibrationModeProperty;
	}

	public Console getConsole() {
		return consoleProperty.get();
	}

	public void setConsole(Console console) {
		consoleProperty.set(console);
	}

	public long getRefreshInterval() {
		return refreshIntervalProperty.get();
	}

	public void setRefreshInterval(long refreshInterval) {
		refreshIntervalProperty.set(refreshInterval);
	}

	public boolean isPrecisionCalibrationMode() {
		return precisionCalibrationModeProperty.get();
	}

	public void setPrecisionCalibrationMode(boolean precisionCalibrationMode) {
		precisionCalibrationModeProperty.set(precisionCalibrationMode);
	}

}
