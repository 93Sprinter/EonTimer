package io.eontimer.model.timer;

import io.eontimer.model.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomTimerModel {

	private ObservableList<Stage> stages = FXCollections.observableArrayList();

	public ObservableList<Stage> getStages() {
		return stages;
	}

}
