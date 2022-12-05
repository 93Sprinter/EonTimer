package io.eontimer.model.settings;

import io.common.javafx.util.Choice;

public enum ActionMode implements Choice {

	AUDIO("Audio"), VISUAL("Visual"), AV("A/V"), NONE("None");

	private String text;

	private ActionMode(String text) {
		this.text = text;
	}

	@Override
	public String getText() {
		return text;
	}

}
