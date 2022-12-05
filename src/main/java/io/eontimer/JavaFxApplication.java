package io.eontimer;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import io.eontimer.config.AppProperties;
import io.eontimer.controller.EonTimerPane;
import io.eontimer.model.resource.CssResource;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;

public class JavaFxApplication extends Application {

	private ConfigurableApplicationContext applicationContext;

	@Override
	public void init() {
		String[] args = getParameters().getRaw().toArray(new String[0]);
		this.applicationContext = new SpringApplicationBuilder().sources(EonTimerApplication.class).run(args);
	}

	@Override
	public void start(Stage stage) {
		FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
		stage.setTitle(applicationContext.getBean(AppProperties.class).getFullApplicationName());
		stage.setScene(new Scene(fxWeaver.loadView(EonTimerPane.class)));
		stage.getScene().getStylesheets().add(CssResource.MAIN.getPath());
		stage.setWidth(610.0);
		stage.setHeight(470.0);
		stage.setResizable(false);
		stage.show();
	}

	@Override
	public void stop() {
		this.applicationContext.close();
		Platform.exit();
	}

}
