package com.test.jvm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicSubject implements InvocationHandler {

	private Object sub;

	public DynamicSubject(Object sub) {
		this.sub = sub;
	}

	@Override
	public Object invoke(Object proxy,Method method,Object[] args) throws Throwable {

		System.out.println("before");
		method.invoke(this.sub, args);
		System.out.println("after");
		return null;
	}
}
