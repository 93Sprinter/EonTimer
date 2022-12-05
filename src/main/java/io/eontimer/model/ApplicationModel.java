package io.eontimer.model;

import io.eontimer.model.settings.ActionSettingsModel;
import io.eontimer.model.settings.TimerSettingsModel;
import io.eontimer.model.timer.CustomTimerModel;
import io.eontimer.model.timer.Gen3TimerModel;
import io.eontimer.model.timer.Gen4TimerModel;
import io.eontimer.model.timer.Gen5TimerModel;
import io.eontimer.model.timer.TimerConstants;
import io.eontimer.model.timer.TimerType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ApplicationModel {

	private Gen3TimerModel gen3 = new Gen3TimerModel();

	private Gen4TimerModel gen4 = new Gen4TimerModel();

	private Gen5TimerModel gen5 = new Gen5TimerModel();

	private CustomTimerModel custom = new CustomTimerModel();

	private ActionSettingsModel actionSettings = new ActionSettingsModel();

	private TimerSettingsModel timerSettings = new TimerSettingsModel();

	private final ObjectProperty<TimerType> selectedTimerTypeProperty = new SimpleObjectProperty<>(TimerConstants.DEFAULT_TIMER_TYPE);

	public Gen3TimerModel getGen3() {
		return gen3;
	}

	public void setGen3(Gen3TimerModel gen3) {
		this.gen3 = gen3;
	}

	public Gen4TimerModel getGen4() {
		return gen4;
	}

	public void setGen4(Gen4TimerModel gen4) {
		this.gen4 = gen4;
	}

	public Gen5TimerModel getGen5() {
		return gen5;
	}

	public void setGen5(Gen5TimerModel gen5) {
		this.gen5 = gen5;
	}

	public CustomTimerModel getCustom() {
		return custom;
	}

	public void setCustom(CustomTimerModel custom) {
		this.custom = custom;
	}

	public ActionSettingsModel getActionSettings() {
		return actionSettings;
	}

	public void setActionSettings(ActionSettingsModel actionSettings) {
		this.actionSettings = actionSettings;
	}

	public TimerSettingsModel getTimerSettings() {
		return timerSettings;
	}

	public void setTimerSettings(TimerSettingsModel timerSettings) {
		this.timerSettings = timerSettings;
	}

	public ObjectProperty<TimerType> getSelectedTimerTypeProperty() {
		return selectedTimerTypeProperty;
	}

	public TimerType getSelectedTimerType() {
		return selectedTimerTypeProperty.get();
	}

	public void setSelectedTimerType(TimerType selectedTimerType) {
		selectedTimerTypeProperty.set(selectedTimerType);
	}

}
