package io.eontimer.controller.settings;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import io.eontimer.model.resource.ImageResource;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("SettingsControlPane.fxml")
public class SettingsControlPane implements Initializable {

	private Dialog<?> dialog;

	@FXML
	private TabPane tabPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dialog = new Dialog<>();
		Window window = dialog.getDialogPane().getScene().getWindow();
		if (window instanceof Stage) {
			((Stage) window).getIcons().add(new Image(ImageResource.APP_ICON.getPath()));
		}
		dialog.setTitle("Settings");
		dialog.getDialogPane().getButtonTypes().setAll(ButtonType.OK);
		dialog.getDialogPane().setContent(tabPane);
	}

	public void show() {
		dialog.showAndWait();
	}

}
