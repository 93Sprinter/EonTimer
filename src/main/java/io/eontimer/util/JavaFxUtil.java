package io.eontimer.util;

import io.common.javafx.util.Nodes;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class JavaFxUtil {

	public static void showWhen(Node node, BooleanBinding condition) {
		Nodes.hideAndResizeParentIf(node, condition.not());
	}

	public static boolean isActive(Label label) {
		return label.getStyleClass().contains("active");
	}

	public static void setActive(Label label, boolean isActive) {
		if (isActive) {
			label.getStyleClass().add("active");
		} else {
			label.getStyleClass().remove("active");
		}
	}

}
