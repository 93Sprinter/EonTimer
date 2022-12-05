package io.eontimer.model.resource;

import java.net.URL;

public interface Resource {

	public static final String BASE_RESOURCE_PATH = "/io/eontimer";

	public String getPath();

	public default URL get() {
		return this.getClass().getResource(getPath());
	}

}
