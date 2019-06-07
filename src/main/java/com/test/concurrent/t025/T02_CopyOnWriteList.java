package com.test.concurrent.t025;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制容器
 * 写的效率非常低,读的时候效率高
 * 适合写少读多的环境
 *
 * 事件监听器的队列
 */
public class T02_CopyOnWriteList {
	public static void main(String[] args){
		List<String> lists = new ArrayList<String>();//不安全
//		List<String> lists = new Vector<>();//效率低 安全
//		List<String> lists = new CopyOnWriteArrayList<>();
		Random r = new Random();
		Thread[] ths = new Thread[100];
		for(int i = 0; i < ths.length; i++){
			Runnable task = new Runnable() {
				@Override
				public void run() {
					for(int i = 0; i < 1000; i++){
						lists.add("a" + r.nextInt(10000));
					}
				}
			};
			ths[i] = new Thread(task);
		}
		runAndComputTime(ths);
		System.out.println(lists.size());
	}

	private static void runAndComputTime(Thread[] ths) {
		long s1 = System.currentTimeMillis();
		Arrays.asList(ths).forEach((t) -> {
			t.start();
		});

		Arrays.asList(ths).forEach((t) -> {
			try {
				t.join();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		});
		long s2 = System.currentTimeMillis();
		System.out.println(s2 - s1);

	}
}
