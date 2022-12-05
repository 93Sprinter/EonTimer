package io.eontimer.util.javafx.spinner;

import javafx.beans.property.SimpleLongProperty;

public class MinLongProperty extends SimpleLongProperty {

	private final LongValueFactory longValueFactory;

	public MinLongProperty(long initialValue, LongValueFactory longValueFactory) {
		super(initialValue);
		this.longValueFactory = longValueFactory;
	}

	@Override
	protected void invalidated() {
		final long newMin = get();
		if (newMin > longValueFactory.getMax()) {
			longValueFactory.setMin(longValueFactory.getMax());
			return;
		}
		if (longValueFactory.getValue() < newMin) {
			longValueFactory.setValue(newMin);
		}
	}

}
