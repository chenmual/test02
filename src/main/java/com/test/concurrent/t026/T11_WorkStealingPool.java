package com.test.concurrent.t026;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 任务窃取线程
 * 根据CPU n核起 n个默认线程
 * 精灵线程:DeamonThread 只要虚拟机不退出 他不会退出
 * 普通线程在Debug时显示Thread
 */
public class T11_WorkStealingPool {
	public static void main(String[] args) throws IOException {
		ExecutorService service = Executors.newWorkStealingPool();
		System.out.println(Runtime.getRuntime().availableProcessors());

		service.execute(new R(1000));//第一个任务 给第一个线程
		service.execute(new R(2000));//2->2
		service.execute(new R(2000));//3->3
		service.execute(new R(2000));//4->4
		service.execute(new R(2000));//等待先执行结束的线程

//		try {
//			TimeUnit.SECONDS.sleep(5);
//		} catch(InterruptedException e) {
//			e.printStackTrace();
//		}
		//由于产生的是精灵线程(守护线程/后台线程),主线程不阻塞的话,VM直接结束了 看不到输出了
		System.in.read();
	}

	static class R implements Runnable{
		int time;
		public R(int t){
			this.time = t;
		}
		@Override
		public void run() {
			try {
				TimeUnit.MILLISECONDS.sleep(time);
				System.out.println(time + " " + Thread.currentThread().getName());
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
