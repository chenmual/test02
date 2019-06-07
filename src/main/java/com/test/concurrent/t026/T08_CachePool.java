package com.test.concurrent.t026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CachedThreadPool 弹性线程池
 * 如果来一个任务 没有空闲的就新起一个线程
 * 线程空闲超过60秒则自动销毁(可以指定其他时间)
 */
public class T08_CachePool {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println(service);
		for(int i = 0; i < 2; i++){
			service.execute(() -> {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
			System.out.println(service);
			TimeUnit.SECONDS.sleep(80);
			System.out.println(service);
		}
	}
}
