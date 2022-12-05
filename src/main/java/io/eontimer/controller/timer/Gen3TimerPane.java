package io.eontimer.controller.timer;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.javafx.binding.BidirectionalBinding;

import io.common.javafx.util.Nodes;
import io.eontimer.model.TimerState;
import io.eontimer.model.timer.Gen3TimerMode;
import io.eontimer.model.timer.Gen3TimerModel;
import io.eontimer.service.CalibrationService;
import io.eontimer.service.TimerRunnerService;
import io.eontimer.service.factory.Gen3TimerFactory;
import io.eontimer.util.ReactorFxUtil;
import io.eontimer.util.TimeUtil;
import io.eontimer.util.javafx.ChoiceField;
import io.eontimer.util.javafx.spinner.LongValueFactory;
import io.eontimer.util.javafx.spinner.SpinnerUtil;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;

@Component
public class Gen3TimerPane implements Initializable {

	@Autowired
	private Gen3TimerModel model;

	@Autowired
	private TimerState timerState;

	@Autowired
	private Gen3TimerFactory timerFactory;

	@Autowired
	private TimerRunnerService timerRunnerService;

	@Autowired
	private CalibrationService calibrationService;

	@FXML
	private ChoiceField<Gen3TimerMode> modeField;

	@FXML
	private Spinner<Long> calibrationField;

	@FXML
	private Spinner<Long> preTimerField;

	@FXML
	private Spinner<Long> targetFrameField;

	@FXML
	private Button setTargetFrameBtn;

	@FXML
	private Spinner<Long> frameHitField;

	private BooleanProperty isPrimedProperty = new SimpleBooleanProperty(true);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		modeField.setItems(Gen3TimerMode.class).valueProperty().bindBidirectional(model.getModeProperty());
		modeField.getParent().disableProperty().bind(timerState.getRunningProperty());

		calibrationField.setValueFactory(new LongValueFactory());
		BidirectionalBinding.bindNumber(calibrationField.getValueFactory().valueProperty(), model.getCalibrationProperty());
		calibrationField.getParent().disableProperty().bind(timerState.getRunningProperty());
		SpinnerUtil.setOnFocusLost(calibrationField, SpinnerUtil.commitValue(calibrationField));

		preTimerField.setValueFactory(new LongValueFactory(0));
		BidirectionalBinding.bindNumber(preTimerField.getValueFactory().valueProperty(), model.getPreTimerProperty());
		calibrationField.getParent().disableProperty().bind(timerState.getRunningProperty());
		SpinnerUtil.setOnFocusLost(preTimerField, SpinnerUtil.commitValue(preTimerField));

		targetFrameField.setValueFactory(new LongValueFactory(0));
		BidirectionalBinding.bindNumber(targetFrameField.getValueFactory().valueProperty(), model.getTargetFrameProperty());
		targetFrameField.getParent().disableProperty().bind(
				model.getModeProperty().isEqualTo(Gen3TimerMode.VARIABLE_TARGET).and(timerState.getRunningProperty().not().or(isPrimedProperty.not()))
						.or(model.getModeProperty().isEqualTo(Gen3TimerMode.STANDARD).and(timerState.getRunningProperty())));
		SpinnerUtil.setOnFocusLost(targetFrameField, SpinnerUtil.commitValue(targetFrameField));

		Nodes.hideAndResizeParentIf(setTargetFrameBtn, model.getModeProperty().isEqualTo(Gen3TimerMode.VARIABLE_TARGET).not());
		setTargetFrameBtn.disableProperty().bind(isPrimedProperty.not());
		setTargetFrameBtn.setOnAction((event) -> {
			if (timerState.isRunning()) {
				final long duration = calibrationService.toMillis(model.getTargetFrame());
				timerRunnerService.getStages().set(1, TimeUtil.milliseconds(duration + model.getCalibration()));
				timerState.setTotalTime(TimeUtil.sum(timerRunnerService.getStages()));
				isPrimedProperty.set(false);
			}
		});

		frameHitField.setValueFactory(new LongValueFactory(0));
		BidirectionalBinding.bindNumber(frameHitField.getValueFactory().valueProperty(), model.getFrameHitProperty());
		frameHitField.getParent().disableProperty().bind(timerState.getRunningProperty());
		SpinnerUtil.setOnFocusLost(frameHitField, SpinnerUtil.commitValue(frameHitField));
		frameHitField.getEditor().setText("");

		ReactorFxUtil.asFlux(timerState.getRunningProperty()).subscribe(it -> isPrimedProperty.set(it));
	}

	public void calibrate() {
		timerFactory.calibrate();
		frameHitField.getEditor().setText("");
	}

}
