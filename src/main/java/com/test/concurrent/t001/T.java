package com.test.concurrent.t001;

/**
 * synchronized 关键字
 * 对某个对象加锁
 */
public class T {
	private int count = 10;
	private Object o = new Object();
	public void m(){
		synchronized (o){//锁的是对象不是引用
			//任何线程要执行下面代码 必须先拿到o的锁
			count--;
			System.out.println(Thread.currentThread().getName() + "count = " + count);
		}
	}
}
