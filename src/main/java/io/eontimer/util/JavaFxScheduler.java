package io.eontimer.util;

import javafx.application.Platform;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class JavaFxScheduler {

	private static Scheduler platform = Schedulers.fromExecutor(Platform::runLater);

	public static Scheduler platform() {
		return platform;
	}

}
