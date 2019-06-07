package com.test.concurrent.t006;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 对比上一个程序 分析这个程序的输出
 */
public class T implements Runnable {
	private static int count = 10;
//	private Object lock = new Object();
	public synchronized void run() {
		count--;
//		try {
//			TimeUnit.SECONDS.sleep(1);
//		} catch(InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}

	public static void main(String[] args) {
		T t = new T();
		for (int i = 0; i < 5; i++) {
			new Thread(t, "THREAD" + i).start();
		}
	}
}

