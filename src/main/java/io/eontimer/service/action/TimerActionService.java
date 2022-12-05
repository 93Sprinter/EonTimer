package io.eontimer.service.action;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.eontimer.model.settings.ActionMode;
import io.eontimer.model.settings.ActionSettingsModel;
import io.eontimer.util.ReactorFxUtil;
import io.eontimer.util.TaskUtil;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;

@Service
public class TimerActionService implements InitializingBean {

	@Autowired
	private ActionSettingsModel timerActionSettingsModel;

	@Autowired
	private SoundPlayer soundPlayer;

	private SimpleBooleanProperty activeProperty = new SimpleBooleanProperty(false);

	private List<Duration> actionInterval = new ArrayList<>();

	public void invokeAction() {
		if (ActionMode.AUDIO.equals(timerActionSettingsModel.getMode()) || ActionMode.AV.equals(timerActionSettingsModel.getMode())) {
			soundPlayer.play();
		}
		if (ActionMode.VISUAL.equals(timerActionSettingsModel.getMode()) || ActionMode.AV.equals(timerActionSettingsModel.getMode())) {
			setActive(true);
			TaskUtil.run(new Task<String>() {

				@Override
				protected String call() throws Exception {
					Thread.sleep(75);
					setActive(false);
					return null;
				}
			});
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ReactorFxUtil.anyChangesOf(
				timerActionSettingsModel.getCountProperty(),
				timerActionSettingsModel.getIntervalProperty())
				.map(it -> it.mapT1(v -> v.intValue()))
				.map(it -> it.mapT2(v -> v.intValue()))
				.map(it -> createActionInterval(it.getT1(), it.getT2()))
				.subscribe(it -> actionInterval = it);

	}

	private List<Duration> createActionInterval(int count, int interval) {
		return IntStream.range(0, count - 1).boxed().map(it -> (count - 1) - it - 1).map(it -> it * interval).map(it -> (long) it).map(Duration::ofMillis).collect(Collectors.toList());
	}

	public SimpleBooleanProperty getActiveProperty() {
		return activeProperty;
	}

	public boolean isActive() {
		return activeProperty.get();
	}

	public void setActive(boolean active) {
		this.activeProperty.set(active);
	}

	public List<Duration> getActionInterval() {
		return actionInterval;
	}

}
