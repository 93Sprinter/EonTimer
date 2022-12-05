package io.eontimer.controller.timer;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.eontimer.model.ApplicationModel;
import io.eontimer.model.TimerState;
import io.eontimer.model.timer.TimerType;
import io.eontimer.service.TimerRunnerService;
import io.eontimer.service.factory.TimerFactoryService;
import io.eontimer.util.ReactorFxUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

@Component
public class TimerControlPane implements Initializable {

	@Autowired
	private ApplicationModel model;

	@Autowired
	private TimerState timerState;

	@Autowired
	private TimerRunnerService timerRunner;

	@Autowired
	private TimerFactoryService timerFactory;

	@Autowired
	private Gen3TimerPane gen3TimerPane;

	@Autowired
	private Gen4TimerPane gen4TimerPane;

	@Autowired
	private Gen5TimerPane gen5TimerPane;

	@FXML
	private Tab gen3Tab;

	@FXML
	private Tab gen4Tab;

	@FXML
	private Tab gen5Tab;

	@FXML
	private Tab customTab;

	@FXML
	private TabPane timerTabPane;

	@FXML
	private Button updateBtn;

	@FXML
	private Button timerBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		timerTabPane.getSelectionModel().select(findTab(getTimerType()));
		ReactorFxUtil.asFlux(timerTabPane.getSelectionModel().selectedItemProperty())
				.map(it -> findTimerType(it))
				.subscribe(it -> setTimerType(it));

		gen3Tab.disableProperty().bind(
				timerTabPane.getSelectionModel().selectedItemProperty().isNotEqualTo(gen3Tab)
						.and(timerState.getRunningProperty()));
		gen4Tab.disableProperty().bind(
				timerTabPane.getSelectionModel().selectedItemProperty().isNotEqualTo(gen4Tab)
						.and(timerState.getRunningProperty()));
		gen5Tab.disableProperty().bind(
				timerTabPane.getSelectionModel().selectedItemProperty().isNotEqualTo(gen5Tab)
						.and(timerState.getRunningProperty()));
		customTab.disableProperty().bind(
				timerTabPane.getSelectionModel().selectedItemProperty().isNotEqualTo(customTab)
						.and(timerState.getRunningProperty()));

		ReactorFxUtil.asFlux(timerState.getRunningProperty())
				.map(it -> !it ? "Start" : "Stop")
				.subscribe(it -> timerBtn.setText(it));

		timerBtn.setOnAction(event -> {
			if (!timerState.isRunning()) {
				timerRunner.start(timerFactory.getStages());
			} else {
				timerRunner.stop();
			}
		});

		updateBtn.disableProperty().bind(timerState.getRunningProperty());
		updateBtn.setOnAction(event -> calibrate());
	}

	private TimerType getTimerType() {
		return model.getSelectedTimerType();
	}

	private void setTimerType(TimerType timerType) {
		model.setSelectedTimerType(timerType);
	}

	private TimerType findTimerType(Tab tab) {
		if (customTab.equals(tab)) {
			return TimerType.CUSTOM;
		} else if (gen3Tab.equals(tab)) {
			return TimerType.GEN3;
		} else if (gen4Tab.equals(tab)) {
			return TimerType.GEN4;
		} else if (gen5Tab.equals(tab)) {
			return TimerType.GEN5;
		}
		throw new IllegalStateException("unable to find TimerType for selected tab");
	}

	private void calibrate() {
		switch (getTimerType()) {
		case CUSTOM:
			break;
		case GEN3:
			gen3TimerPane.calibrate();
		case GEN4:
			gen4TimerPane.calibrate();
			break;
		case GEN5:
			gen5TimerPane.calibrate();
			break;
		default:
			throw new RuntimeException("Unsupported value " + getTimerType());
		}
	}

	private Tab findTab(TimerType timerType) {
		switch (timerType) {
		case CUSTOM:
			return customTab;
		case GEN3:
			return gen3Tab;
		case GEN4:
			return gen4Tab;
		case GEN5:
			return gen5Tab;
		default:
			throw new RuntimeException("Unsupported value " + getTimerType());
		}
	}

}
