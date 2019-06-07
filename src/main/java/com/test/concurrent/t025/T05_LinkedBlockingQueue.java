package com.test.concurrent.t025;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class T05_LinkedBlockingQueue {
	/**
	 * BlockingQueue:
	 * LinkedBlockingQueue
	 * ArrayBlockingQueue
	 */
	static BlockingQueue<String> strs = new LinkedBlockingQueue<>();

	static Random r = new Random();

	public static void main(String[] args) {
		//启动一个生产者线程
		new Thread(() -> {
			for(int i = 0; i < 100; i++) {
				try {
					strs.put("a" + i);//如果满了就会等待
					TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"p1").start();

		//启动10个消费者线程
		for(int i = 0; i < 10; i++) {
			new Thread(() -> {
				for(; ; ) {
					try {
						//如果空了就会等待
						System.out.println(Thread.currentThread().getName() + "take - " + strs.take());
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			},"c" + i).start();
		}
	}
}
