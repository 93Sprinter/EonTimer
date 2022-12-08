package io.eontimer.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.concurrent.Task;

public class TaskUtil {

	private static ExecutorService exec;

	public static void init() {
		exec = Executors.newFixedThreadPool(3);
	}

	public static void run(Task<?> command) {
		exec.execute(command);
	}

	public static void run(Runnable command) {
		exec.execute(command);
	}

	public static void shutdown() {
		exec.shutdown();
	}

}
