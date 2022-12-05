package io.eontimer.model.timer;

import io.common.javafx.util.Choice;

public enum Gen5TimerMode implements Choice {

	STANDARD("Standard"), C_GEAR("C-Gear"), ENTRALINK("Entralink"), ENHANCED_ENTRALINK("Entralink+");

	private String text;

	private Gen5TimerMode(String text) {
		this.text = text;
	}

	@Override
	public String getText() {
		return text;
	}

}
