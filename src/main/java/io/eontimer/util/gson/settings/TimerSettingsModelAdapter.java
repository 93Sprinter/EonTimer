package io.eontimer.util.gson.settings;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import io.eontimer.model.settings.Console;
import io.eontimer.model.settings.TimerSettingsModel;

@Component
public class TimerSettingsModelAdapter extends TypeAdapter<TimerSettingsModel> {

	@Override
	public void write(JsonWriter out, TimerSettingsModel value) throws IOException {
		out.beginObject().name("console").value(value.getConsole().name()).name("refreshInterval").value(value.getRefreshInterval()).name("precisionCalibrationMode")
				.value(value.isPrecisionCalibrationMode()).endObject();
	}

	@Override
	public TimerSettingsModel read(JsonReader in) throws IOException {
		final TimerSettingsModel model = new TimerSettingsModel();
		in.beginObject();
		while (in.hasNext()) {
			switch (in.nextName()) {
			case "console":
				model.setConsole(Console.valueOf(in.nextString()));
				break;
			case "refreshInterval":
				model.setRefreshInterval(in.nextLong());
				break;
			case "precisionCalibrationMode":
				model.setPrecisionCalibrationMode(in.nextBoolean());
				break;
			default:
				break;
			}
		}
		in.endObject();
		return model;
	}

}
