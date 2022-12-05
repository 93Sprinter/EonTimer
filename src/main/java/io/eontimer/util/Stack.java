package io.eontimer.util;

import java.util.List;
import java.util.stream.Collectors;

public class Stack<T> {

	private final List<T> list;

	public Stack(List<T> list) {
		this.list = list.stream().collect(Collectors.toList());
	}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void push(T value) {
		list.add(0, value);
	}

	public T peek() {
		return !isEmpty() ? list.get(0) : null;
	}

	public T pop() {
		return !isEmpty() ? list.remove(0) : null;
	}

	public static <T> Stack<T> asStack(List<T> list) {
		return new Stack<>(list);
	}

}
