package io.eontimer.model.timer;

import io.common.javafx.util.Choice;

public enum Gen3TimerMode implements Choice {

	STANDARD("Standard"), VARIABLE_TARGET("Variable Target");

	private String text;

	private Gen3TimerMode(String text) {
		this.text = text;
	}

	@Override
	public String getText() {
		return text;
	}

}
