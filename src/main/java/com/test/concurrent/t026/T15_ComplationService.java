package com.test.concurrent.t026;

import java.util.Arrays;
import java.util.concurrent.*;

public class T15_ComplationService {
	public static void main(String[] args){
		ExecutorService exe = null;
		CompletionService service = new ExecutorCompletionService(exe);
		String[] strs = new String[10];
		for(final String str : strs){
			service.submit(new Callable<String>() {
				@Override
				public String call() {
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
					return str;
				}
			});
		}
		Arrays.stream(strs).forEach(s -> System.out.println(s));
	}
}
