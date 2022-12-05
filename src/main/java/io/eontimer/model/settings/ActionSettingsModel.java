package io.eontimer.model.settings;

import io.eontimer.model.resource.SoundResource;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class ActionSettingsModel {

	private final ObjectProperty<ActionMode> modeProperty = new SimpleObjectProperty<>(ActionSettingsConstants.DEFAULT_MODE);

	private final ObjectProperty<Color> colorProperty = new SimpleObjectProperty<>(ActionSettingsConstants.DEFAULT_COLOR);

	private final ObjectProperty<SoundResource> soundProperty = new SimpleObjectProperty<>(ActionSettingsConstants.DEFAULT_SOUND);

	private final IntegerProperty intervalProperty = new SimpleIntegerProperty(ActionSettingsConstants.DEFAULT_INTERVAL);

	private final IntegerProperty countProperty = new SimpleIntegerProperty(ActionSettingsConstants.DEFAULT_COUNT);

	public ObjectProperty<ActionMode> getModeProperty() {
		return modeProperty;
	}

	public ObjectProperty<Color> getColorProperty() {
		return colorProperty;
	}

	public ObjectProperty<SoundResource> getSoundProperty() {
		return soundProperty;
	}

	public IntegerProperty getIntervalProperty() {
		return intervalProperty;
	}

	public IntegerProperty getCountProperty() {
		return countProperty;
	}

	public ActionMode getMode() {
		return modeProperty.get();
	}

	public void setMode(ActionMode mode) {
		modeProperty.set(mode);
	}

	public Color getColor() {
		return colorProperty.get();
	}

	public void setColor(Color color) {
		colorProperty.set(color);
	}

	public SoundResource getSound() {
		return soundProperty.get();
	}

	public void setSound(SoundResource sound) {
		soundProperty.set(sound);
	}

	public int getInterval() {
		return intervalProperty.get();
	}

	public void setInterval(int interval) {
		intervalProperty.set(interval);
	}

	public int getCount() {
		return countProperty.get();
	}

	public void setCount(int count) {
		countProperty.set(count);
	}

}
