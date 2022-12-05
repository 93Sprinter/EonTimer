package io.eontimer.model.resource;

public enum ImageResource implements Resource {

	DefaultBackgroundImage("default_background_image.png");

	private String fileName;

	private ImageResource(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String getPath() {
		return BASE_RESOURCE_PATH + "/img/" + fileName;
	}

}
