package com.test.concurrent.t022;


import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal是使用空间换时间,synchronized是使用时间换空间
 * 比如在hibernate中session就存在于ThreadLocal中,避免synchronized的使用
 *
 * 线程局部变量
 */
public class ThreadLocal2 {
	volatile static Person p = new Person();

	public static void main(String[] args){
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(p.name);
		}).start();

		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			p.name = "lisi";
		}).start();
	}

	static class Person {
		public String name = "zhangsan";
	}
}