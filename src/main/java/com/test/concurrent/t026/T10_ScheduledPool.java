package com.test.concurrent.t026;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T10_ScheduledPool {
	public static void main(String[] args){
		ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
		/**
		 * 按照固定时间间隔执行
		 * 任务
		 * 开始延迟时间
		 * 每隔多长时间执行一次
		 * 时间单位
		 */
		service.scheduleAtFixedRate(() -> {
//			try {
//				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
//			} catch(InterruptedException e) {
//				e.printStackTrace();
//			}
			System.out.println(Thread.currentThread().getName());
		}, 0, 500,TimeUnit.MILLISECONDS);
	}
}
