package io.eontimer.util.gson;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import io.eontimer.model.Stage;

@Component
public class TimerStageModelAdapter extends TypeAdapter<Stage> {

	@Override
	public void write(JsonWriter out, Stage value) throws IOException {
		out.value(value.getLength());
	}

	@Override
	public Stage read(JsonReader in) throws IOException {
		return new Stage(in.nextLong());
	}

}
