package com.test.concurrent.t026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T09_SinglePool {
	public static void main(String[] args){
		//单线程线程池
		ExecutorService service = Executors.newSingleThreadExecutor();
		for(int i = 0; i < 5; i++){
			final int j = i;
			service.execute(() -> {
				System.out.println(j + " " + Thread.currentThread().getName());
			});
		}
	}
}
