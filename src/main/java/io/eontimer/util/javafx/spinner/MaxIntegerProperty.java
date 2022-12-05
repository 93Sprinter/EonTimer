package io.eontimer.util.javafx.spinner;

import javafx.beans.property.SimpleIntegerProperty;

public class MaxIntegerProperty extends SimpleIntegerProperty {

	private final IntValueFactory intValueFactory;

	public MaxIntegerProperty(int initialValue, IntValueFactory intValueFactory) {
		super(initialValue);
		this.intValueFactory = intValueFactory;
	}

	@Override
	protected void invalidated() {
		final int newMax = get();
		if (newMax < intValueFactory.getMin()) {
			intValueFactory.setMax(intValueFactory.getMin());
			return;
		}

		if (intValueFactory.getValue() > newMax) {
			intValueFactory.setValue(newMax);
		}
	}

}
