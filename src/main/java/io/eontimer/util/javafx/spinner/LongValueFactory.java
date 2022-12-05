package io.eontimer.util.javafx.spinner;

import java.util.Optional;

import javafx.beans.property.SimpleLongProperty;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.converter.LongStringConverter;

public class LongValueFactory extends SpinnerValueFactory<Long> {

	private long min = Long.MIN_VALUE;

	private long max = Long.MAX_VALUE;

	private long initialValue = 0L;

	private long step = 1L;

	private final SimpleLongProperty stepProperty;
	private final MinLongProperty minProperty;
	private final MaxLongProperty maxProperty;

	public LongValueFactory() {
		this.stepProperty = new SimpleLongProperty(this.step);
		this.minProperty = new MinLongProperty(this.min, this);
		this.maxProperty = new MaxLongProperty(this.max, this);
		init();
	}

	public LongValueFactory(long min) {
		this.min = min;
		this.stepProperty = new SimpleLongProperty(this.step);
		this.minProperty = new MinLongProperty(this.min, this);
		this.maxProperty = new MaxLongProperty(this.max, this);
		init();
	}

	public LongValueFactory(long min, long max) {
		this.min = min;
		this.max = max;
		this.stepProperty = new SimpleLongProperty(this.step);
		this.minProperty = new MinLongProperty(this.min, this);
		this.maxProperty = new MaxLongProperty(this.max, this);
		init();
	}

	public LongValueFactory(long min, long max, long initialValue) {
		this.min = min;
		this.max = max;
		this.initialValue = initialValue;
		this.stepProperty = new SimpleLongProperty(this.step);
		this.minProperty = new MinLongProperty(this.min, this);
		this.maxProperty = new MaxLongProperty(this.max, this);
		init();
	}

	public LongValueFactory(long min, long max, long initialValue, long step) {
		this.min = min;
		this.max = max;
		this.initialValue = initialValue;
		this.step = step;
		this.stepProperty = new SimpleLongProperty(this.step);
		this.minProperty = new MinLongProperty(this.min, this);
		this.maxProperty = new MaxLongProperty(this.max, this);
		init();
	}

	public long getStep() {
		return stepProperty.get();
	}

	public void setStep(long step) {
		this.step = step;
		stepProperty.set(this.step);
	}

	public long getMin() {
		return minProperty.get();
	}

	public void setMin(long min) {
		this.min = min;
		minProperty.set(this.min);
	}

	public long getMax() {
		return maxProperty.get();
	}

	public void setMax(long max) {
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
		setConverter(new LongStringConverter());

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

	private long wrapValue(long value, long min, long max) {
		if (max == 0) {
			throw new RuntimeException();
		}

		long r = value % max;

		if (min >= (max + 1) && min < r) {
			return r + max - min;
		} else if (min >= (r + 1) && min < max) {
			return r + max - min;
		} else {
			return r;
		}
	}

}
