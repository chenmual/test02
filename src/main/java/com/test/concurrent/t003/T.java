package com.test.concurrent.t003;

/**
 * synchronized 关键字
 * 对某个对象加锁
 */
public class T {
	private int count = 10;
	public synchronized void m(){//等同于synchronized(this)
		count--;
		System.out.println(Thread.currentThread().getName() + "count = " + count);
	}
}
