package io.eontimer.util.gson.timer;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import io.eontimer.model.timer.Gen4TimerMode;
import io.eontimer.model.timer.Gen4TimerModel;

@Component
public class Gen4TimerModelAdapter extends TypeAdapter<Gen4TimerModel> {

	@Override
	public void write(JsonWriter out, Gen4TimerModel value) throws IOException {
		out.beginObject().name("mode").value(value.getMode().name()).name("calibratedDelay").value(value.getCalibratedDelay()).name("calibratedSecond").value(value.getCalibratedSecond())
				.name("targetDelay").value(value.getTargetDelay()).name("targetSecond").value(value.getTargetSecond()).endObject();
	}

	@Override
	public Gen4TimerModel read(JsonReader in) throws IOException {
		final Gen4TimerModel model = new Gen4TimerModel();
		in.beginObject();
		while (in.hasNext()) {
			switch (in.nextName()) {
			case "mode":
				model.setMode(Gen4TimerMode.valueOf(in.nextString()));
				break;
			case "calibratedDelay":
				model.setCalibratedDelay(in.nextLong());
				break;
			case "calibratedSecond":
				model.setCalibratedSecond(in.nextLong());
				break;
			case "targetDelay":
				model.setTargetDelay(in.nextLong());
				break;
			case "targetSecond":
				model.setTargetSecond(in.nextLong());
				break;
			default:
				break;
			}
		}
		in.endObject();
		return model;
	}

}
