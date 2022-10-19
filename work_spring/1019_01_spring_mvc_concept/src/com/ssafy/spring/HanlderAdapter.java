package com.ssafy.spring;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.spring.reflection.Invoker;

public class HanlderAdapter {
	
	private Invoker invoker = new Invoker();
	
	public Object process(Object controller, String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return invoker.invoke(controller, pathInfo, req, resp);
	}

}
