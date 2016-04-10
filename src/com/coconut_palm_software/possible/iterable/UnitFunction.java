package com.coconut_palm_software.possible.iterable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class UnitFunction<T> {
	private T result;
	private Class<?> clazz;
	private Method addMethod;
	private Constructor<T> ctor;

	public UnitFunction(Class<T> clazz) {
		this.clazz = clazz;
		try {
			ctor = clazz.getConstructor();
		} catch (Exception e) {
			throw new IllegalArgumentException("Class does not support a 0-arg constructor", e);
		}
		try {
			addMethod = clazz.getMethod("add", Object.class);
		} catch (Exception e) {
			throw new IllegalArgumentException("Class does not support #add", e);
		}
		try {
			result = ctor.newInstance();
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected: Could not construct: " + clazz.getName(), e);
		}
	}
	
	public void add(Object o) {
		try {
			addMethod.invoke(result, o);
		} catch (Exception e) {
			throw new IllegalStateException("Couldn't add a " + o.getClass() + " to a " + clazz.getName(), e);
		}
	}
	
	public T result() {
		return result;
	}
}
