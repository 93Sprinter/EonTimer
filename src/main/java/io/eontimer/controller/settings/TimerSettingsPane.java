package io.eontimer.controller.settings;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.javafx.binding.BidirectionalBinding;

import io.eontimer.model.settings.Console;
import io.eontimer.model.settings.TimerSettingsModel;
import io.eontimer.util.javafx.ChoiceField;
import io.eontimer.util.javafx.spinner.LongValueFactory;
import io.eontimer.util.javafx.spinner.SpinnerUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;

@Component
public class TimerSettingsPane implements Initializable {

	@Autowired
	private TimerSettingsModel model;

	@FXML
	private ChoiceField<Console> consoleField;

	@FXML
	private Spinner<Long> refreshIntervalField;

	@FXML
	private CheckBox precisionCalibrationField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		consoleField.setItems(Console.class).valueProperty().bindBidirectional(model.getConsoleProperty());
		refreshIntervalField.setValueFactory(new LongValueFactory(0, 1000L));
		BidirectionalBinding.bindNumber(refreshIntervalField.getValueFactory().valueProperty(), model.getRefreshIntervalProperty());
		SpinnerUtil.setOnFocusLost(refreshIntervalField, SpinnerUtil.commitValue(refreshIntervalField));
		precisionCalibrationField.selectedProperty().bindBidirectional(model.getPrecisionCalibrationModeProperty());
	}

}
