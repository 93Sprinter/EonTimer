package io.eontimer.controller.settings;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.javafx.binding.BidirectionalBinding;

import io.eontimer.model.resource.SoundResource;
import io.eontimer.model.settings.ActionMode;
import io.eontimer.model.settings.ActionSettingsModel;
import io.eontimer.util.javafx.ChoiceField;
import io.eontimer.util.javafx.spinner.IntValueFactory;
import io.eontimer.util.javafx.spinner.SpinnerUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;

@Component
public class ActionSettingsPane implements Initializable {

	@Autowired
	private ActionSettingsModel model;

	@FXML
	private ChoiceField<ActionMode> modeField;

	@FXML
	private ChoiceField<SoundResource> soundField;

	@FXML
	private ColorPicker colorField;

	@FXML
	private Spinner<Integer> intervalField;

	@FXML
	private Spinner<Integer> countField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		modeField.setItems(ActionMode.class).valueProperty().bindBidirectional(model.getModeProperty());
		soundField.setItems(SoundResource.class).valueProperty().bindBidirectional(model.getSoundProperty());
		colorField.valueProperty().bindBidirectional(model.getColorProperty());
		intervalField.setValueFactory(new IntValueFactory(0, 1000));
		BidirectionalBinding.bindNumber(intervalField.getValueFactory().valueProperty(), model.getIntervalProperty());
		SpinnerUtil.setOnFocusLost(intervalField, SpinnerUtil.commitValue(intervalField));
		countField.setValueFactory(new IntValueFactory(0, 50));
		BidirectionalBinding.bindNumber(countField.getValueFactory().valueProperty(), model.getCountProperty());
		SpinnerUtil.setOnFocusLost(countField, SpinnerUtil.commitValue(countField));
	}

}
