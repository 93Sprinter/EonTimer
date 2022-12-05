package io.eontimer.controller.settings;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@Component
public class SettingsControlPane implements Initializable {

	private Stage stage;

	@FXML
	private VBox dialog;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stage = new Stage();
		stage.setScene(new Scene(dialog));
	}

	public void show() {
		stage.show();
	}

}
