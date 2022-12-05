package io.eontimer.util.gson.settings;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import io.common.javafx.scene.paint.Colors;
import io.eontimer.model.resource.SoundResource;
import io.eontimer.model.settings.ActionMode;
import io.eontimer.model.settings.ActionSettingsModel;
import javafx.scene.paint.Color;

@Component
public class ActionSettingsModelAdapter extends TypeAdapter<ActionSettingsModel> {

	@Override
	public void write(JsonWriter out, ActionSettingsModel value) throws IOException {
		out.beginObject().name("mode").value(value.getMode().name()).name("color").value(Colors.toHex(value.getColor())).name("sound").value(value.getSound().name()).name("interval")
				.value(value.getInterval()).name("count").value(value.getCount()).endObject();
	}

	@Override
	public ActionSettingsModel read(JsonReader in) throws IOException {
		final ActionSettingsModel model = new ActionSettingsModel();
		in.beginObject();
		while (in.hasNext()) {
			switch (in.nextName()) {
			case "mode":
				model.setMode(ActionMode.valueOf(in.nextString()));
				break;
			case "color":
				model.setColor(Color.web(in.nextString()));
				break;
			case "sound":
				model.setSound(SoundResource.valueOf(in.nextString()));
				break;
			case "interval":
				model.setInterval(in.nextInt());
				break;
			case "count":
				model.setCount(in.nextInt());
				break;
			default:
				break;
			}
		}
		in.endObject();
		return model;
	}

}
