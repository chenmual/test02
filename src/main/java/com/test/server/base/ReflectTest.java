package com.test.server.base;

import java.lang.reflect.InvocationTargetException;

public class ReflectTest {
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		// 获取Class对象
		// 1 对象.getClass()
		Iphone iphone = new Iphone();
		Class clz = iphone.getClass();
		// 2 类.class
		clz = Iphone.class;
		// 3 Class.forName(包名.类名)
		clz = Class.forName("com.test.server.base.Iphone");

		// 创建对象
		// 1 clz.newInstance()jdk9后不推荐
		Iphone iphone2 = (Iphone) clz.newInstance();
		System.out.println(iphone2);
		// 2 clz.getConstructor().newInstance()jdk9后推荐
		Iphone iphone3 = (Iphone) clz.getConstructor().newInstance();
	}

}
class Iphone{
}
