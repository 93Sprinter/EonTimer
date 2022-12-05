package io.eontimer.model.resource;

import io.common.javafx.util.Choice;

public enum SoundResource implements Resource, Choice {

	BEEP("beep.wav"), TICK("tick.wav"), DING("ding.wav"), POP("pop.wav"), SILENT("silence.wav");

	private String fileName;

	private SoundResource(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String getText() {
		return this.name();
	}

	@Override
	public String getPath() {
		return BASE_RESOURCE_PATH + "/sounds/" + fileName;
	}

}
