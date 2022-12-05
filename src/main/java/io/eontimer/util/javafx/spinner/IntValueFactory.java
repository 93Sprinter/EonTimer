package io.eontimer.util.javafx.spinner;

import java.util.Optional;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.converter.IntegerStringConverter;

public class IntValueFactory extends SpinnerValueFactory<Integer> {

	private int min = Integer.MIN_VALUE;

	private int max = Integer.MAX_VALUE;

	private int initialValue = 0;

	private int step = 1;

	private final SimpleIntegerProperty stepProperty;
	private final MinIntegerProperty minProperty;
	private final MaxIntegerProperty maxProperty;

	public IntValueFactory(int min, int max) {
		this.min = min;
		this.max = max;
		this.stepProperty = new SimpleIntegerProperty(this.step);
		this.minProperty = new MinIntegerProperty(this.min, this);
		this.maxProperty = new MaxIntegerProperty(this.max, this);
		init();
	}

	public IntValueFactory(int min, int max, int initialValue) {
		this.min = min;
		this.max = max;
		this.initialValue = initialValue;
		this.stepProperty = new SimpleIntegerProperty(this.step);
		this.minProperty = new MinIntegerProperty(this.min, this);
		this.maxProperty = new MaxIntegerProperty(this.max, this);
		init();
	}

	public IntValueFactory(int min, int max, int initialValue, int step) {
		this.min = min;
		this.max = max;
		this.initialValue = initialValue;
		this.step = step;
		this.stepProperty = new SimpleIntegerProperty(this.step);
		this.minProperty = new MinIntegerProperty(this.min, this);
		this.maxProperty = new MaxIntegerProperty(this.max, this);
		init();
	}

	public int getStep() {
		return stepProperty.get();
	}

	public void setStep(int step) {
		this.step = step;
		stepProperty.set(this.step);
	}

	public int getMin() {
		return minProperty.get();
	}

	public void setMin(int min) {
		this.min = min;
		minProperty.set(this.min);
	}

	public int getMax() {
		return maxProperty.get();
	}

	public void setMax(int max) {
		this.max = max;
		maxProperty.set(this.max);
	}

	@Override
	public void decrement(int steps) {
		Optional.ofNullable(getValue()).map(v -> v - (steps * this.getStep())).ifPresent(newValue -> {
			if (newValue >= this.getMin()) {
				this.setValue(newValue);
			} else if (isWrapAround()) {
				this.setValue(this.wrapValue(newValue, min, max) + 1);
			} else {
				setValue(this.getMin());
			}
		});
	}

	@Override
	public void increment(int steps) {
		Optional.ofNullable(getValue()).map(v -> v + (steps * this.getStep())).ifPresent(newValue -> {
			if (newValue <= this.getMax()) {
				this.setValue(newValue);
			} else if (isWrapAround()) {
				this.setValue(this.wrapValue(newValue, min, max) - 1);
			} else {
				setValue(this.getMax());
			}
		});

	}

	private void init() {
		setConverter(new IntegerStringConverter());

		valueProperty().addListener((observable, oldValue, newValue) -> {
			Optional.ofNullable(newValue).ifPresent((o) -> {
				if (newValue < this.getMin()) {
					this.setValue(this.getMin());
				} else if (newValue > this.getMax()) {
					this.setValue(this.getMax());
				} else {
					this.setValue(newValue);
				}
			});
		});
		setValue(initialValue >= min && initialValue <= max ? initialValue : min);
	}

	private int wrapValue(int value, int min, int max) {
		if (max == 0) {
			throw new RuntimeException();
		}

		int r = value % max;

		if (min >= (max + 1) && min < r) {
			return r + max - min;
		} else if (min >= (r + 1) && min < max) {
			return r + max - min;
		} else {
			return r;
		}
	}

}
