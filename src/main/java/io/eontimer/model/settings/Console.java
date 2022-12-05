package io.eontimer.model.settings;

import io.common.javafx.util.Choice;

public enum Console implements Choice {

	GBA("GBA", 59.7271), NDS("NDS", 59.8261), DSI("DSI", 59.8261), _3DS("3DS", 59.8261);

	private String text;

	private Double fps;

	private Console(String text, Double fps) {
		this.text = text;
		this.fps = fps;
	}

	public Double getFrameRate() {
		return 1000 / fps;
	}

	@Override
	public String getText() {
		return text;
	}

}
