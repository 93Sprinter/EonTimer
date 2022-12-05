package io.eontimer.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.concurrent.Task;

public class TaskUtil {

	public static void run(Task<?> command) {
		final ExecutorService exec = Executors.newFixedThreadPool(1);
		exec.execute(command);
		exec.shutdown();
	}

}
