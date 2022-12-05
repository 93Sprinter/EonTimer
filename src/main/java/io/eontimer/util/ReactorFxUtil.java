package io.eontimer.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuple4;
import reactor.util.function.Tuples;

public class ReactorFxUtil {

	public static <T> Flux<T> asFlux(ObservableValue<T> observableValue) {
		return asFlux(observableValue, true);
	}

	public static <T> Flux<T> asFlux(ObservableValue<T> observableValue, boolean emitCurrentValue) {
		return Flux.create((emitter) -> {
			if (emitCurrentValue) {
				emitter.next(observableValue.getValue());
			}
			final ChangeListener<T> listener = new ChangeListener<T>() {

				@Override
				public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
					emitter.next(newValue);
				}
			};
			observableValue.addListener(listener);
			emitter.onDispose(() -> {
				Platform.runLater(() -> {
					observableValue.removeListener(listener);
				});
			});
		});
	}

	public static <T> Flux<List<T>> asFlux(ObservableList<T> observableList) {
		return Flux.create((emitter) -> {
			emitter.next(observableList);
			final ListChangeListener<T> listener = new ListChangeListener<T>() {

				@SuppressWarnings("unchecked")
				@Override
				public void onChanged(Change<? extends T> c) {
					emitter.next((List<T>) c.getList());
				}
			};
			observableList.addListener(listener);
			emitter.onDispose(() -> {
				Platform.runLater(() -> {
					observableList.removeListener(listener);
				});
			});
		});
	}

	public static <T1, T2> Flux<Tuple2<T1, T2>> anyChangesOf(ObservableValue<T1> property1, ObservableValue<T2> property2) {
		return anyChangesOf(property1, property2, Tuples::of);
	}

	public static <T1, T2, R> Flux<R> anyChangesOf(ObservableValue<T1> property1, ObservableValue<T2> property2, Mapper2<T1, T2, R> mapper) {
		return Flux.create(emitter -> {
			final List<Disposable> disposables = Stream.of(property1, property2).map(it -> asFlux(it)).map(it -> it.map(v -> mapper.map(property1.getValue(), property2.getValue())))
					.map(it -> it.subscribe(v -> emitter.next(v))).collect(Collectors.toList());
			emitter.onDispose(() -> {
				disposables.forEach(it -> it.dispose());
			});
		});
	}

	public static <T1, T2, T3> Flux<Tuple3<T1, T2, T3>> anyChangesOf(ObservableValue<T1> property1, ObservableValue<T2> property2, ObservableValue<T3> property3) {
		return anyChangesOf(property1, property2, property3, Tuples::of);
	}

	public static <T1, T2, T3, R> Flux<R> anyChangesOf(ObservableValue<T1> property1, ObservableValue<T2> property2, ObservableValue<T3> property3, Mapper3<T1, T2, T3, R> mapper) {
		return Flux.create(emitter -> {
			final List<Disposable> disposables = Stream.of(property1, property2, property3).map(it -> asFlux(it))
					.map(it -> it.map(v -> mapper.map(property1.getValue(), property2.getValue(), property3.getValue()))).map(it -> it.subscribe(v -> emitter.next(v))).collect(Collectors.toList());
			emitter.onDispose(() -> {
				disposables.forEach(it -> it.dispose());
			});
		});
	}

	public static <T1, T2, T3, T4> Flux<Tuple4<T1, T2, T3, T4>> anyChangesOf(ObservableValue<T1> property1, ObservableValue<T2> property2, ObservableValue<T3> property3,
			ObservableValue<T4> property4) {
		return anyChangesOf(property1, property2, property3, property4, Tuples::of);
	}

	public static <T1, T2, T3, T4, R> Flux<R> anyChangesOf(ObservableValue<T1> property1, ObservableValue<T2> property2, ObservableValue<T3> property3, ObservableValue<T4> property4,
			Mapper4<T1, T2, T3, T4, R> mapper) {
		return Flux.create(emitter -> {
			final List<Disposable> disposables = Stream.of(property1, property2, property3, property4).map(it -> asFlux(it))
					.map(it -> it.map(v -> mapper.map(property1.getValue(), property2.getValue(), property3.getValue(), property4.getValue()))).map(it -> it.subscribe(v -> emitter.next(v)))
					.collect(Collectors.toList());
			emitter.onDispose(() -> {
				disposables.forEach(it -> it.dispose());
			});
		});
	}

}
