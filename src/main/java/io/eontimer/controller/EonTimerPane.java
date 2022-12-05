package io.eontimer.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import io.eontimer.model.TimerState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("EonTimerPane.fxml")
public class EonTimerPane implements Initializable {

	@Autowired
	private TimerState timerState;

	@FXML
	private Button settingsBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		settingsBtn.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.GEAR));
		settingsBtn.disableProperty().bind(timerState.getRunningProperty());
		settingsBtn.setOnAction(event -> {
			// show settings dialog
		});

	}

}
