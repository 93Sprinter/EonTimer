package io.eontimer.controller.timer;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.javafx.binding.BidirectionalBinding;

import io.eontimer.model.TimerState;
import io.eontimer.model.timer.Gen4TimerMode;
import io.eontimer.model.timer.Gen4TimerModel;
import io.eontimer.service.factory.Gen4TimerFactory;
import io.eontimer.util.javafx.ChoiceField;
import io.eontimer.util.javafx.spinner.LongValueFactory;
import io.eontimer.util.javafx.spinner.SpinnerUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;

@Component
public class Gen4TimerPane implements Initializable {

	@Autowired
	private Gen4TimerModel model;

	@Autowired
	private TimerState timerState;

	@Autowired
	private Gen4TimerFactory timerFactory;

	@FXML
	private ChoiceField<Gen4TimerMode> modeField;

	@FXML
	private Spinner<Long> calibratedDelayField;

	@FXML
	private Spinner<Long> calibratedSecondField;

	@FXML
	private Spinner<Long> targetDelayField;

	@FXML
	private Spinner<Long> targetSecondField;

	@FXML
	private Spinner<Long> delayHitField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		modeField.setItems(Gen4TimerMode.class).valueProperty().bindBidirectional(model.getModeProperty());
		modeField.getParent().disableProperty().bind(timerState.getRunningProperty());

		calibratedDelayField.setValueFactory(new LongValueFactory());
		BidirectionalBinding.bindNumber(calibratedDelayField.getValueFactory().valueProperty(), model.getCalibratedDelayProperty());
		calibratedDelayField.getParent().disableProperty().bind(timerState.getRunningProperty());
		SpinnerUtil.setOnFocusLost(calibratedDelayField, SpinnerUtil.commitValue(calibratedDelayField));

		calibratedSecondField.setValueFactory(new LongValueFactory());
		BidirectionalBinding.bindNumber(calibratedSecondField.getValueFactory().valueProperty(), model.getCalibratedSecondProperty());
		calibratedSecondField.getParent().disableProperty().bind(timerState.getRunningProperty());
		SpinnerUtil.setOnFocusLost(calibratedSecondField, SpinnerUtil.commitValue(calibratedSecondField));

		targetDelayField.setValueFactory(new LongValueFactory(0));
		BidirectionalBinding.bindNumber(targetDelayField.getValueFactory().valueProperty(), model.getTargetDelayProperty());
		targetDelayField.getParent().disableProperty().bind(timerState.getRunningProperty());
		SpinnerUtil.setOnFocusLost(targetDelayField, SpinnerUtil.commitValue(targetDelayField));

		targetSecondField.setValueFactory(new LongValueFactory(0));
		BidirectionalBinding.bindNumber(targetSecondField.getValueFactory().valueProperty(), model.getTargetSecondProperty());
		targetSecondField.getParent().disableProperty().bind(timerState.getRunningProperty());
		SpinnerUtil.setOnFocusLost(targetSecondField, SpinnerUtil.commitValue(targetSecondField));

		delayHitField.setValueFactory(new LongValueFactory(0));
		BidirectionalBinding.bindNumber(delayHitField.getValueFactory().valueProperty(), model.getDelayHitProperty());
		delayHitField.getParent().disableProperty().bind(timerState.getRunningProperty());
		SpinnerUtil.setOnFocusLost(delayHitField, SpinnerUtil.commitValue(delayHitField));
		delayHitField.getEditor().setText("");
	}

	public void calibrate() {
		timerFactory.calibrate();
		delayHitField.getEditor().setText("");
	}

}
