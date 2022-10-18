package com.ssafy.reflection.annotation;

import java.lang.reflect.InvocationTargetException;

public class Test {

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		ClassA classA = new ClassA();
		
		Invoker invoker = new Invoker();
		invoker.invoke(classA);
	}
}
