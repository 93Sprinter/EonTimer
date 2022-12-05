package io.eontimer.util.gson.timer;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import io.eontimer.model.timer.CustomTimerModel;

@Component
public class CustomTimerModelAdapter extends TypeAdapter<CustomTimerModel> {

	@Override
	public void write(JsonWriter out, CustomTimerModel value) throws IOException {
		out.beginObject();
		out.name("stages");
		out.beginArray();
		for (long it : value.getStages()) {
			out.value(it);
		}
		out.endArray();
		out.endObject();
	}

	@Override
	public CustomTimerModel read(JsonReader in) throws IOException {
		final CustomTimerModel model = new CustomTimerModel();
		in.beginObject();
		while (in.hasNext()) {
			switch (in.nextName()) {
			case "stages":
				in.beginArray();
				while (in.hasNext()) {
					model.getStages().add(in.nextLong());
				}
				in.endArray();
				break;
			default:
				break;
			}
		}
		in.endObject();
		return model;
	}

}
