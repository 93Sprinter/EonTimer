package io.common.javafx.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

import javafx.util.StringConverter;

public class ChoiceConverter<T extends Enum<T> & Choice> extends StringConverter<T> {

	private final Class<T> classType;

	private final Map<String, T> textMap;

	@SuppressWarnings("unchecked")
	public ChoiceConverter(Class<T> classType) {
		this.classType = classType;
		this.textMap = new HashMap<>();
		for (Enum<T> enum_ : (Enum<T>[]) classType.getEnumConstants())
			this.textMap.put(((Choice) enum_).getText(), (T) enum_);
	}

	public static <T extends Enum<T> & Choice> ChoiceConverter<T> forChoice(Class<T> classType) {
		return new ChoiceConverter<>(classType);
	}

	@Override
	public String toString(T object) {
		return ((Choice) object).getText();
	}

	@Override
	public T fromString(String string) {
		Assert.isTrue(string != null, "string cannot be null");
		Assert.isTrue(this.textMap.containsKey(string), String.format("no option constant for %s.%s", this.classType.getCanonicalName(), string));
		return this.textMap.get(string);
	}

}
