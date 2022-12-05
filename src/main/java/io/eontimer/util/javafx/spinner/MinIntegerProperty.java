package io.eontimer.util.javafx.spinner;

import javafx.beans.property.SimpleIntegerProperty;

public class MinIntegerProperty extends SimpleIntegerProperty {

	private final IntValueFactory intValueFactory;

	public MinIntegerProperty(int initialValue, IntValueFactory intValueFactory) {
		super(initialValue);
		this.intValueFactory = intValueFactory;
	}

	@Override
	protected void invalidated() {
		final int newMin = get();
		if (newMin > intValueFactory.getMax()) {
			intValueFactory.setMin(intValueFactory.getMax());
			return;
		}
		if (intValueFactory.getValue() < newMin) {
			intValueFactory.setValue(newMin);
		}
	}

}
