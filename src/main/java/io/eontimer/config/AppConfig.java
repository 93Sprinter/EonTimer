package io.eontimer.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import io.eontimer.model.ApplicationModel;
import io.eontimer.model.settings.ActionSettingsModel;
import io.eontimer.model.settings.TimerSettingsModel;
import io.eontimer.model.timer.CustomTimerModel;
import io.eontimer.model.timer.Gen3TimerModel;
import io.eontimer.model.timer.Gen4TimerModel;
import io.eontimer.model.timer.Gen5TimerModel;
import io.eontimer.util.gson.ApplicationModelAdapter;

@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class AppConfig {

	@Autowired
	private AppProperties properties;

	@Bean()
	public Gson gson(GsonBuilder builder, ApplicationModelAdapter applicationModelAdapter) {
		return builder.setPrettyPrinting().registerTypeAdapter(ApplicationModel.class, applicationModelAdapter).create();
	}

	@Bean
	public ApplicationModel settings(Gson gson) throws JsonSyntaxException, IOException {
		final File file = new File(properties.getName() + ".json");
		if (file.exists()) {
			return gson.fromJson(Files.readString(Paths.get(properties.getName() + ".json")), ApplicationModel.class);
		} else {
			return new ApplicationModel();
		}
	}

	@Bean
	public Gen3TimerModel gen3TimerModel(ApplicationModel settings) {
		return settings.getGen3();
	}

	@Bean
	public Gen4TimerModel gen4TimerModel(ApplicationModel settings) {
		return settings.getGen4();
	}

	@Bean
	public Gen5TimerModel gen5TimerModel(ApplicationModel settings) {
		return settings.getGen5();
	}

	@Bean
	public CustomTimerModel customTimerModel(ApplicationModel settings) {
		return settings.getCustom();
	}

	@Bean
	public ActionSettingsModel actionSettingsModel(ApplicationModel settings) {
		return settings.getActionSettings();
	}

	@Bean
	public TimerSettingsModel timerSettingsModel(ApplicationModel settings) {
		return settings.getTimerSettings();
	}
}
