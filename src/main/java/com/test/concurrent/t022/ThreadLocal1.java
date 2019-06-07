package com.test.concurrent.t022;

import java.util.concurrent.TimeUnit;

/**
 * 线程局部变量
 */
public class ThreadLocal1 {
//	volatile static Person p = new Person();
	static ThreadLocal<Person> tl = new ThreadLocal<>();
	public static void main(String[] args){
	    new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(tl.get());
		}).start();

		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			tl.set(new Person());
		}).start();
	}

	static class Person {
		public String name = "zhangsan";
	}
}
