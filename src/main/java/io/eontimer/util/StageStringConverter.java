package io.eontimer.util;

import io.eontimer.model.Stage;
import javafx.util.StringConverter;

public class StageStringConverter extends StringConverter<Stage> {

	@Override
	public String toString(Stage stage) {
		return stage.getLength().toString();
	}

	@Override
	public Stage fromString(String value) {
		return new Stage(Long.valueOf(value));
	}

}
