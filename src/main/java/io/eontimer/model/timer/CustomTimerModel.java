package io.eontimer.model.timer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomTimerModel {

	private ObservableList<Long> stages = FXCollections.observableArrayList();

	public ObservableList<Long> getStages() {
		return stages;
	}

}
