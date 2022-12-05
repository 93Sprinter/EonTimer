package io.eontimer.controller.timer;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.javafx.binding.BidirectionalBinding;

import io.eontimer.model.TimerState;
import io.eontimer.model.timer.Gen5TimerMode;
import io.eontimer.model.timer.Gen5TimerModel;
import io.eontimer.service.factory.Gen5TimerFactory;
import io.eontimer.util.JavaFxUtil;
import io.eontimer.util.javafx.ChoiceField;
import io.eontimer.util.javafx.spinner.LongValueFactory;
import io.eontimer.util.javafx.spinner.SpinnerUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;

@Component
public class Gen5TimerPane implements Initializable {

	@Autowired
	private Gen5TimerModel model;

	@Autowired
	private TimerState timerState;

	@Autowired
	private Gen5TimerFactory timerFactory;

	@FXML
	private ChoiceField<Gen5TimerMode> modeField;

	@FXML
	private Spinner<Long> calibrationField;

	@FXML
	private Spinner<Long> targetDelayField;

	@FXML
	private Spinner<Long> targetSecondField;

	@FXML
	private Spinner<Long> entralinkCalibrationField;

	@FXML
	private Spinner<Long> frameCalibrationField;

	@FXML
	private Spinner<Long> targetAdvancesField;

	@FXML
	private Spinner<Long> secondHitField;

	@FXML
	private Spinner<Long> delayHitField;

	@FXML
	private Spinner<Long> actualAdvancesField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		modeField.setItems(Gen5TimerMode.class).valueProperty().bindBidirectional(model.getModeProperty());
		modeField.getParent().disableProperty().bind(timerState.getRunningProperty());

		calibrationField.setValueFactory(new LongValueFactory());
		BidirectionalBinding.bindNumber(calibrationField.getValueFactory().valueProperty(), model.getCalibrationProperty());
		calibrationField.getParent().disableProperty().bind(timerState.getRunningProperty());
		SpinnerUtil.setOnFocusLost(calibrationField, SpinnerUtil.commitValue(calibrationField));

		targetDelayField.setValueFactory(new LongValueFactory(0));
		BidirectionalBinding.bindNumber(targetDelayField.getValueFactory().valueProperty(), model.getTargetDelayProperty());
		targetDelayField.getParent().disableProperty().bind(timerState.getRunningProperty());
		JavaFxUtil.showWhen(targetDelayField.getParent(),
				model.getModeProperty().isEqualTo(Gen5TimerMode.C_GEAR)
						.or(model.getModeProperty().isEqualTo(Gen5TimerMode.ENTRALINK))
						.or(model.getModeProperty().isEqualTo(Gen5TimerMode.ENHANCED_ENTRALINK)));
		SpinnerUtil.setOnFocusLost(targetDelayField, SpinnerUtil.commitValue(targetDelayField));

		targetSecondField.setValueFactory(new LongValueFactory(0));
		BidirectionalBinding.bindNumber(targetSecondField.getValueFactory().valueProperty(), model.getTargetSecondProperty());
		targetSecondField.getParent().disableProperty().bind(timerState.getRunningProperty());
		SpinnerUtil.setOnFocusLost(targetSecondField, SpinnerUtil.commitValue(targetSecondField));

		entralinkCalibrationField.setValueFactory(new LongValueFactory());
		BidirectionalBinding.bindNumber(entralinkCalibrationField.getValueFactory().valueProperty(), model.getEntralinkCalibrationProperty());
		entralinkCalibrationField.getParent().disableProperty().bind(timerState.getRunningProperty());
		JavaFxUtil.showWhen(entralinkCalibrationField.getParent(),
				model.getModeProperty().isEqualTo(Gen5TimerMode.ENTRALINK)
						.or(model.getModeProperty().isEqualTo(Gen5TimerMode.ENHANCED_ENTRALINK)));
		SpinnerUtil.setOnFocusLost(entralinkCalibrationField, SpinnerUtil.commitValue(entralinkCalibrationField));

		frameCalibrationField.setValueFactory(new LongValueFactory());
		BidirectionalBinding.bindNumber(frameCalibrationField.getValueFactory().valueProperty(), model.getFrameCalibrationProperty());
		frameCalibrationField.getParent().disableProperty().bind(timerState.getRunningProperty());
		JavaFxUtil.showWhen(frameCalibrationField.getParent(), model.getModeProperty().isEqualTo(Gen5TimerMode.ENHANCED_ENTRALINK));
		SpinnerUtil.setOnFocusLost(frameCalibrationField, SpinnerUtil.commitValue(frameCalibrationField));

		targetAdvancesField.setValueFactory(new LongValueFactory(0));
		BidirectionalBinding.bindNumber(targetAdvancesField.getValueFactory().valueProperty(), model.getTargetAdvancesProperty());
		targetAdvancesField.getParent().disableProperty().bind(timerState.getRunningProperty());
		JavaFxUtil.showWhen(targetAdvancesField.getParent(), model.getModeProperty().isEqualTo(Gen5TimerMode.ENHANCED_ENTRALINK));
		SpinnerUtil.setOnFocusLost(targetAdvancesField, SpinnerUtil.commitValue(targetAdvancesField));

		secondHitField.setValueFactory(new LongValueFactory(0));
		BidirectionalBinding.bindNumber(secondHitField.getValueFactory().valueProperty(), model.getSecondHitProperty());
		secondHitField.getParent().disableProperty().bind(timerState.getRunningProperty());
		JavaFxUtil.showWhen(secondHitField.getParent(),
				model.getModeProperty().isEqualTo(Gen5TimerMode.STANDARD)
						.or(model.getModeProperty().isEqualTo(Gen5TimerMode.ENTRALINK))
						.or(model.getModeProperty().isEqualTo(Gen5TimerMode.ENHANCED_ENTRALINK)));
		SpinnerUtil.setOnFocusLost(secondHitField, SpinnerUtil.commitValue(secondHitField));
		secondHitField.getEditor().setText("");

		delayHitField.setValueFactory(new LongValueFactory(0));
		BidirectionalBinding.bindNumber(delayHitField.getValueFactory().valueProperty(), model.getDelayHitProperty());
		delayHitField.getParent().disableProperty().bind(timerState.getRunningProperty());
		JavaFxUtil.showWhen(delayHitField.getParent(),
				model.getModeProperty().isEqualTo(Gen5TimerMode.C_GEAR)
						.or(model.getModeProperty().isEqualTo(Gen5TimerMode.ENTRALINK))
						.or(model.getModeProperty().isEqualTo(Gen5TimerMode.ENHANCED_ENTRALINK)));
		SpinnerUtil.setOnFocusLost(delayHitField, SpinnerUtil.commitValue(delayHitField));
		delayHitField.getEditor().setText("");

		actualAdvancesField.setValueFactory(new LongValueFactory(0));
		BidirectionalBinding.bindNumber(actualAdvancesField.getValueFactory().valueProperty(), model.getActualAdvancesProperty());
		actualAdvancesField.getParent().disableProperty().bind(timerState.getRunningProperty());
		JavaFxUtil.showWhen(actualAdvancesField.getParent(), model.getModeProperty().isEqualTo(Gen5TimerMode.ENHANCED_ENTRALINK));
		SpinnerUtil.setOnFocusLost(actualAdvancesField, SpinnerUtil.commitValue(actualAdvancesField));
		actualAdvancesField.getEditor().setText("");
	}

	public void calibrate() {
		timerFactory.calibrate();
		secondHitField.getEditor().setText("");
		delayHitField.getEditor().setText("");
		actualAdvancesField.getEditor().setText("");
	}

}
