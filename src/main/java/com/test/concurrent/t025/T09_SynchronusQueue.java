package com.test.concurrent.t025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class T09_SynchronusQueue {
	public static void main(String[] args) throws InterruptedException {
		//特殊的TransferQueue容量为0
		BlockingQueue<String> strs = new SynchronousQueue<>();
		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		strs.put("aaa");//阻塞等待消费者消费
//		strs.add("aaa");//抛异常
		System.out.println(strs.size());


		BlockingQueue<String> strs2 = new SynchronousQueue<>();
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
				strs2.put("2");
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		System.out.println(strs2.take());
	}
}
