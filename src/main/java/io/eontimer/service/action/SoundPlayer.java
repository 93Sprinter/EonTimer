package io.eontimer.service.action;

import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.eontimer.model.resource.SoundResource;
import io.eontimer.model.settings.ActionSettingsModel;
import io.eontimer.util.ReactorFxUtil;
import io.eontimer.util.TaskUtil;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

@Component
public class SoundPlayer {

	@Autowired
	private ActionSettingsModel actionSettings;

	private MediaPlayer mediaPlayer;

	private Runnable playCmd = () -> {
		mediaPlayer.setStartTime(Duration.ZERO);
		mediaPlayer.seek(Duration.ZERO);
		mediaPlayer.play();
	};

	private Runnable stopCmd = () -> {
		mediaPlayer.stop();
	};

	public void play() {
		TaskUtil.run(playCmd);
	}

	public void stop() {
		TaskUtil.run(stopCmd);
	}

	@PostConstruct
	private void initialize() {
		ReactorFxUtil.asFlux(actionSettings.getSoundProperty()).map(SoundResource::get).map(r -> createMediaPlayer(r)).subscribe(m -> {
			mediaPlayer = m;
		});
		TaskUtil.run(() -> {
			createMediaPlayer(SoundResource.SILENT.get()).play();
		});
	}

	private MediaPlayer createMediaPlayer(URL url) {
		return new MediaPlayer(new Media(url.toExternalForm()));
	}

}
