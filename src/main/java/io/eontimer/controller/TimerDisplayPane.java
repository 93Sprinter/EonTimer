package io.eontimer.controller;

import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.common.javafx.scene.paint.Colors;
import io.eontimer.model.TimerState;
import io.eontimer.model.settings.ActionSettingsModel;
import io.eontimer.service.action.TimerActionService;
import io.eontimer.util.JavaFxScheduler;
import io.eontimer.util.JavaFxUtil;
import io.eontimer.util.ReactorFxUtil;
import io.eontimer.util.TimeUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

@Component
public class TimerDisplayPane implements Initializable {

	@Autowired
	private TimerState timerState;

	@Autowired
	private TimerActionService timerActionService;

	@Autowired
	private ActionSettingsModel actionSettingsModel;

	@FXML
	private Label currentStageLbl;

	@FXML
	private Label minutesBeforeTargetLbl;

	@FXML
	private Label nextStageLbl;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ReactorFxUtil.asFlux(timerState.getCurrentStageProperty())
				.subscribeOn(JavaFxScheduler.platform())
				.map(it -> formatTime(it))
				.subscribe(it -> currentStageLbl.setText(it));
		ReactorFxUtil.asFlux(timerState.getCurrentRemainingProperty())
				.subscribeOn(JavaFxScheduler.platform())
				.map(it -> formatTime(it))
				.subscribe(it -> currentStageLbl.setText(it));
		ReactorFxUtil.anyChangesOf(timerState.getTotalTimeProperty(), timerState.getTotalElapsedProperty())
				.subscribeOn(JavaFxScheduler.platform())
				.subscribe(it -> {
					minutesBeforeTargetLbl.setText(formatMinutesBeforeTarget(it.getT1(), it.getT2()));
				});
		ReactorFxUtil.asFlux(timerState.getNextStageProperty())
				.subscribeOn(JavaFxScheduler.platform())
				.map(it -> formatTime(it))
				.subscribe(it -> nextStageLbl.setText(it));
		ReactorFxUtil.asFlux(timerActionService.getActiveProperty())
				.subscribe(it -> JavaFxUtil.setActive(currentStageLbl, it));

		ReactorFxUtil.asFlux(actionSettingsModel.getColorProperty())
				.map(it -> Colors.toHex(it))
				.map(it -> "-theme-active:" + it)
				.subscribe(it -> currentStageLbl.setStyle(it));
	}

	private String formatMinutesBeforeTarget(Duration totalTime, Duration totalElapsed) {
		if (TimeUtil.isIndefinite(totalTime)) {
			return "?";
		}
		return totalTime.minus(totalElapsed).toString();
	}

	private String formatTime(Duration duration) {
		if (TimeUtil.isIndefinite(duration)) {
			return "?:??";
		}
		return String.format("%d:%02d", duration.toSeconds(), duration.toMillis() / 10 % 100);

	}
}
