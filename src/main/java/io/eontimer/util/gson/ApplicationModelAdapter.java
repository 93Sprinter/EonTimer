package io.eontimer.util.gson;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import io.eontimer.model.ApplicationModel;
import io.eontimer.model.timer.TimerType;
import io.eontimer.util.gson.settings.ActionSettingsModelAdapter;
import io.eontimer.util.gson.settings.TimerSettingsModelAdapter;
import io.eontimer.util.gson.timer.CustomTimerModelAdapter;
import io.eontimer.util.gson.timer.Gen3TimerModelAdapter;
import io.eontimer.util.gson.timer.Gen4TimerModelAdapter;
import io.eontimer.util.gson.timer.Gen5TimerModelAdapter;

@Component
public class ApplicationModelAdapter extends TypeAdapter<ApplicationModel> {

	@Autowired
	private Gen3TimerModelAdapter gen3TimerModelAdapter;

	@Autowired
	private Gen4TimerModelAdapter gen4TimerModelAdapter;

	@Autowired
	private Gen5TimerModelAdapter gen5TimerModelAdapter;

	@Autowired
	private CustomTimerModelAdapter customTimerModelAdapter;

	@Autowired
	private ActionSettingsModelAdapter actionSettingsModelAdapter;

	@Autowired
	private TimerSettingsModelAdapter timerSettingsModelAdapter;

	@Override
	public void write(JsonWriter out, ApplicationModel value) throws IOException {
		out.beginObject();
		gen3TimerModelAdapter.write(out.name("gen3"), value.getGen3());
		gen4TimerModelAdapter.write(out.name("gen4"), value.getGen4());
		gen5TimerModelAdapter.write(out.name("gen5"), value.getGen5());
		customTimerModelAdapter.write(out.name("custom"), value.getCustom());
		actionSettingsModelAdapter.write(out.name("actionSettings"), value.getActionSettings());
		timerSettingsModelAdapter.write(out.name("timerSettings"), value.getTimerSettings());
		out.name("selectedTimer").value(value.getSelectedTimerType().name());
		out.endObject();
	}

	@Override
	public ApplicationModel read(JsonReader in) throws IOException {
		final ApplicationModel model = new ApplicationModel();
		in.beginObject();
		while (in.hasNext()) {
			switch (in.nextName()) {
			case "gen3":
				model.setGen3(gen3TimerModelAdapter.read(in));
				break;
			case "gen4":
				model.setGen4(gen4TimerModelAdapter.read(in));
				break;
			case "gen5":
				model.setGen5(gen5TimerModelAdapter.read(in));
				break;
			case "custom":
				model.setCustom(customTimerModelAdapter.read(in));
				break;
			case "actionSettings":
				model.setActionSettings(actionSettingsModelAdapter.read(in));
				break;
			case "timerSettings":
				model.setTimerSettings(timerSettingsModelAdapter.read(in));
				break;
			case "selectedTimer":
				model.setSelectedTimerType(TimerType.valueOf(in.nextString()));
				break;
			default:
				break;
			}
		}
		in.endObject();
		return model;
	}

}
