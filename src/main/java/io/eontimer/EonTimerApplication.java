package io.eontimer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;

import io.eontimer.config.AppProperties;
import io.eontimer.model.ApplicationModel;
import javafx.application.Application;

@SpringBootApplication
public class EonTimerApplication {

	@Autowired
	private Gson gson;

	@Autowired
	private AppProperties properties;

	@Autowired
	private ApplicationModel settings;

	public static void main(String[] args) {
		Application.launch(JavaFxApplication.class, args);
	}

	@PreDestroy
	private void destroy() throws IOException {
		// persist settings
		final String json = gson.toJson(settings);
		Files.write(Paths.get(properties.getName() + ".json"), json.getBytes());
	}

}
