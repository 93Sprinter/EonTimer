package io.eontimer.controller.timer;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import io.eontimer.model.Stage;
import io.eontimer.model.TimerState;
import io.eontimer.model.timer.CustomTimerModel;
import io.eontimer.util.StageStringConverter;
import io.eontimer.util.javafx.spinner.LongValueFactory;
import io.eontimer.util.javafx.spinner.SpinnerUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.KeyCode;

@Component
public class CustomTimerPane implements Initializable {

	@Autowired
	private CustomTimerModel model;

	@Autowired
	private TimerState timerState;

	@FXML
	private ListView<Stage> list;

	@FXML
	private Spinner<Long> valueField;

	@FXML
	private Button valueAddBtn;

	@FXML
	private Button valueRemoveBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		list.setItems(model.getStages());
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		list.setCellFactory(TextFieldListCell.forListView(new StageStringConverter()));
		list.disableProperty().bind(timerState.getRunningProperty());

		valueField.setValueFactory(new LongValueFactory(0L));
		valueField.disableProperty().bind(timerState.getRunningProperty());
		valueField.setOnKeyPressed((event) -> {
			if (event.getCode() == KeyCode.ENTER) {
				model.getStages().add(new Stage(valueField.getValue()));
				valueField.getEditor().setText("");
			}
		});
		SpinnerUtil.setOnFocusLost(valueField, SpinnerUtil.commitValue(valueField));
		valueField.getEditor().setText("");

		valueAddBtn.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.PLUS));
		valueAddBtn.disableProperty().bind(valueField.getEditor().textProperty().isEmpty().or(timerState.getRunningProperty()));
		valueAddBtn.setOnAction((event) -> {
			model.getStages().add(new Stage(valueField.getValue()));
			valueField.getEditor().setText("");
		});

		valueRemoveBtn.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.MINUS));
		valueRemoveBtn.disableProperty().bind(list.getSelectionModel().selectedItemProperty().isNull().or(timerState.getRunningProperty()));
		valueRemoveBtn.setOnAction((event) -> {
			list.getSelectionModel().getSelectedIndices().stream().map(i -> model.getStages().get(i)).forEach(s -> model.getStages().remove(s));
		});
	}

}
