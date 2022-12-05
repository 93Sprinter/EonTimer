package io.eontimer.util.javafx;

import io.common.javafx.util.Choice;
import io.common.javafx.util.ChoiceConverter;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

public class ChoiceField<T extends Enum<T> & Choice> extends ChoiceBox<T> {

	public ChoiceField<T> setItems(Class<T> clazz) {
		final T[] choices = clazz.getEnumConstants();
		setItems(FXCollections.observableArrayList(choices));
		setConverter(ChoiceConverter.forChoice(clazz));
		return this;
	}

}
