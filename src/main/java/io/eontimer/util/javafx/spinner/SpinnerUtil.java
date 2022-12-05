package io.eontimer.util.javafx.spinner;

import java.util.Optional;
import java.util.function.Consumer;

import io.eontimer.util.ReactorFxUtil;
import javafx.scene.control.Spinner;

public class SpinnerUtil {

	public static <T> void setOnFocusLost(Spinner<T> spinner, Consumer<? super Boolean> func) {
		ReactorFxUtil.asFlux(spinner.focusedProperty()).filter(isFocused -> !isFocused).subscribe(func);
	}

	public static <T> Consumer<? super Boolean> commitValue(Spinner<T> spinner) {
		return new Consumer<Boolean>() {

			@Override
			public void accept(Boolean t) {
				Optional.ofNullable(spinner.getValueFactory()).map(f -> f.getConverter()).ifPresent(converter -> {
					spinner.getValueFactory().setValue(converter.fromString(spinner.getEditor().getText()));
				});
			}
		};
	}

}
