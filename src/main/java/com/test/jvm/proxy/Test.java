package com.test.jvm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {
	public static void main(String[] args){
	    RealSubject realSubject = new RealSubject();
		InvocationHandler invocationHandler =  new DynamicSubject(realSubject);

		Class<?> cls = realSubject.getClass();
		Subject proxy = (Subject) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), invocationHandler);

		proxy.request();
	}
}
