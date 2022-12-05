package io.eontimer.util.gson.timer;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import io.eontimer.model.timer.Gen5TimerMode;
import io.eontimer.model.timer.Gen5TimerModel;

@Component
public class Gen5TimerModelAdapter extends TypeAdapter<Gen5TimerModel> {

	@Override
	public void write(JsonWriter out, Gen5TimerModel value) throws IOException {
		out.beginObject().name("mode").value(value.getMode().name()).name("calibration").value(value.getCalibration()).name("targetDelay").value(value.getTargetDelay()).name("targetSecond")
				.value(value.getTargetSecond()).name("entralinkCalibration").value(value.getEntralinkCalibration()).name("frameCalibration").value(value.getFrameCalibration()).name("targetAdvances")
				.value(value.getTargetAdvances()).endObject();
	}

	@Override
	public Gen5TimerModel read(JsonReader in) throws IOException {
		final Gen5TimerModel model = new Gen5TimerModel();
		in.beginObject();
		while (in.hasNext()) {
			switch (in.nextName()) {
			case "mode":
				model.setMode(Gen5TimerMode.valueOf(in.nextString()));
				break;
			case "calibration":
				model.setCalibration(in.nextLong());
				break;
			case "targetDelay":
				model.setTargetDelay(in.nextLong());
				break;
			case "targetSecond":
				model.setTargetSecond(in.nextLong());
				break;
			case "entralinkCalibration":
				model.setEntralinkCalibration(in.nextLong());
				break;
			case "frameCalibration":
				model.setFrameCalibration(in.nextLong());
				break;
			case "targetAdvances":
				model.setTargetAdvances(in.nextLong());
				break;
			default:
				break;
			}
		}
		in.endObject();
		return model;
	}

}
