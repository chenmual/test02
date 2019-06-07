package com.test.concurrent.t002;

/**
 * synchronized 关键字
 * 对某个对象加锁
 */
public class T {
	private int count = 10;
	public void m(){
		synchronized (this){
			count--;
			System.out.println(Thread.currentThread().getName() + "count = " + count);
		}
	}
}
