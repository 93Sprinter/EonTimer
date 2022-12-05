package io.eontimer.util.gson.timer;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import io.eontimer.model.timer.Gen3TimerMode;
import io.eontimer.model.timer.Gen3TimerModel;

@Component
public class Gen3TimerModelAdapter extends TypeAdapter<Gen3TimerModel> {

	@Override
	public void write(JsonWriter out, Gen3TimerModel value) throws IOException {
		out.beginObject().name("mode").value(value.getMode().name()).name("calibration").value(value.getCalibration()).name("preTimer").value(value.getPreTimer()).name("targetFrame")
				.value(value.getTargetFrame()).endObject();
	}

	@Override
	public Gen3TimerModel read(JsonReader in) throws IOException {
		final Gen3TimerModel model = new Gen3TimerModel();
		in.beginObject();
		while (in.hasNext()) {
			switch (in.nextName()) {
			case "mode":
				model.setMode(Gen3TimerMode.valueOf(in.nextString()));
				break;
			case "calibration":
				model.setCalibration(in.nextLong());
				break;
			case "preTimer":
				model.setPreTimer(in.nextLong());
				break;
			case "targetFrame":
				model.setTargetFrame(in.nextLong());
				break;
			default:
				break;
			}
		}
		in.endObject();
		return model;
	}

}
