package io.eontimer.model.timer;

import io.common.javafx.util.Choice;

public enum Gen4TimerMode implements Choice {

	STANDARD("Standard");

	private String text;

	private Gen4TimerMode(String text) {
		this.text = text;
	}

	@Override
	public String getText() {
		return text;
	}

}
