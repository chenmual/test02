package com.test.concurrent.t029;

import java.util.concurrent.*;

public class T029 {
	public static void main(String[] args){
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future future = executorService.submit(() -> {
			action(5);
		});
		try {
			future.get(3,TimeUnit.SECONDS);
		} catch(InterruptedException e){
			Thread.currentThread().interrupt();
		} catch(ExecutionException e){
			throw new RuntimeException(e);
		} catch(TimeoutException e){
			Thread.currentThread().interrupt();//设置中断状态
			future.cancel(true);//尝试取消
		}
		executorService.shutdown();
	}

	public static void action(int second){
		try {
			Thread.sleep(TimeUnit.SECONDS.toMillis(second));//业务操作,耗时second
			if(Thread.interrupted()){
				return;
			}
			action();
		} catch(InterruptedException e) {
		}
	}

	public static void action(){
		System.out.printf("[%s]正在执行:", Thread.currentThread().getName());
	}
}
