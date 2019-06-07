package com.test.concurrent.t026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * 放入线程 按规则执行Executor
 */
public class T05_ThreadPool {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(5);
		for(int i = 0; i < 6; i++){
			service.execute(() -> {
				//睡500毫秒 打印当前线程名称
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		service.shutdown();
		System.out.println(service);
		System.out.println(service.isTerminated());//开始关并不表示他关闭成功
		System.out.println(service.isShutdown());
		System.out.println(service);

		TimeUnit.SECONDS.sleep(5);
		System.out.println(service.isTerminated());//5秒够 线程未结束
		System.out.println(service.isShutdown());
		System.out.println(service);

	}
}
