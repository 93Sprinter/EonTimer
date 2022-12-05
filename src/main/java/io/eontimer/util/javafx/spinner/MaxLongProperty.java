package io.eontimer.util.javafx.spinner;

import javafx.beans.property.SimpleLongProperty;

public class MaxLongProperty extends SimpleLongProperty {

	private final LongValueFactory longValueFactory;

	public MaxLongProperty(long initialValue, LongValueFactory longValueFactory) {
		super(initialValue);
		this.longValueFactory = longValueFactory;
	}

	@Override
	protected void invalidated() {
		final long newMax = get();
		if (newMax < longValueFactory.getMin()) {
			longValueFactory.setMax(longValueFactory.getMin());
			return;
		}

		if (longValueFactory.getValue() > newMax) {
			longValueFactory.setValue(newMax);
		}
	}

}
