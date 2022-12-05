package io.common.javafx.util;

import org.springframework.util.Assert;

import io.eontimer.util.ReactorFxUtil;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

public final class Nodes {
	public static void hideAndResizeParentIf(Node node, BooleanBinding condition) {
		Assert.notNull(node, "node");
		Assert.notNull(condition, "condition");
		autoresizeContainerOn(node, (ObservableValue<?>) condition);
		bindContentBiasCalculationTo(node, condition.not());
	}

	public static void hideAndResizeParentIf(Node node, ObservableValue<Boolean> condition) {
		hideAndResizeParentIf(node, (ObservableValue<Boolean>) BooleanBinding.booleanExpression(condition));
	}

	public static void autoresizeContainerOn(Node node, ObservableValue<?> observableValue) {
		Assert.notNull(node, "node");
		Assert.notNull(observableValue, "observableValue");
		ReactorFxUtil.asFlux(observableValue).doOnNext(change -> node.autosize()).subscribe();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void bindContentBiasCalculationTo(Node node, BooleanBinding observableValue) {
		Assert.notNull(node, "node");
		Assert.notNull(observableValue, "observableValue");
		node.visibleProperty().bind((ObservableValue) observableValue);
		node.managedProperty().bind((ObservableValue) node.visibleProperty());
	}

	public static void bindContentBiasCalculationTo(Node node, ObservableValue<Boolean> observableValue) {
		bindContentBiasCalculationTo(node, (ObservableValue<Boolean>) BooleanBinding.booleanExpression(observableValue));
	}
}
