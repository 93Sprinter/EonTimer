package io.common.javafx.scene.paint;

import java.util.stream.Stream;

import org.apache.commons.lang3.Range;
import org.springframework.util.Assert;

import javafx.scene.paint.Color;

public class Colors {

	private static final Range<Double> DOUBLE_RANGE = Range.between(Double.valueOf(0.0D), Double.valueOf(1.0D));

	private static final String INVALID_DOUBLE_MSG = "Color's %s value must be between 0.0 and 1.0";

	public Colors() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static String toHex(Color color) {
		return Stream.<Double>of(new Double[] { Double.valueOf(color.getRed()), Double.valueOf(color.getGreen()), Double.valueOf(color.getBlue()) }).map(Colors::toColorInt).map(Colors::toHexString)
				.reduce("#", String::concat);
	}

	public static String toHexAlpha(Color color) {
		return Stream.<Double>of(new Double[] { Double.valueOf(color.getRed()), Double.valueOf(color.getGreen()), Double.valueOf(color.getBlue()), Double.valueOf(color.getOpacity()) })
				.map(Colors::toColorInt).map(Colors::toHexString).reduce("#", String::concat);
	}

	public static String toHexAlpha(Color color, double alpha) {
		Assert.isTrue(DOUBLE_RANGE.contains(Double.valueOf(alpha)), String.format(INVALID_DOUBLE_MSG, new Object[] { "alpha" }));
		return Stream.<Double>of(new Double[] { Double.valueOf(color.getRed()), Double.valueOf(color.getGreen()), Double.valueOf(color.getBlue()), Double.valueOf(alpha) }).map(Colors::toColorInt)
				.map(Colors::toHexString).reduce("#", String::concat);
	}

	private static String toHexString(int value) {
		return String.format("%02x", new Object[] { Integer.valueOf(value) });
	}

	private static int toColorInt(double value) {
		return (int) (value * 255.0D) & 0xFF;
	}

	public static Color deriveHue(Color color, double percent) {
		double hue = derive(color.getHue(), percent);
		return Color.hsb(hue, color.getSaturation(), color.getBrightness());
	}

	public static Color deriveSaturation(Color color, double percent) {
		double saturation = derive(color.getSaturation(), percent);
		return Color.hsb(color.getHue(), saturation, color.getBrightness(), color.getOpacity());
	}

	public static Color deriveBrightness(Color color, double percent) {
		double brightness = derive(color.getBrightness(), percent);
		return Color.hsb(color.getHue(), color.getSaturation(), brightness, color.getOpacity());
	}

	private static double derive(double value, double percent) {
		if (percent < 0.0D) {
			value = Double.max(value + percent, 0.0D);
		} else if (percent > 0.0D) {
			value = Double.min(value + percent, 1.0D);
		}
		return value;
	}
}
