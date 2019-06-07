package com.test.concurrent.t004;

/**
 * synchronized 关键字
 * 对某个类的.class加锁
 */
public class T {
	private static int count = 10;
	public synchronized static void m1(){
		//这里等同于synchronized(T.class)
		count--;
		System.out.println(Thread.currentThread().getName() + "count = " + count);
	}

	public static void m2(){
		synchronized (T.class){//synchronized(this)是否可以?
			count--;
		}
	}
}
