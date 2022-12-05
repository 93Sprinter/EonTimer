package io.eontimer.model.resource;

public enum CssResource implements Resource {

	MAIN("main.css");

	private String fileName;

	private CssResource(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String getPath() {
		return BASE_RESOURCE_PATH + "/css/" + fileName;
	}

}
