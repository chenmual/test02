package com.test.concurrent.t015;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决同样的问题的更高效的方法,使用atomXX类
 * AtomXXX类本身的方法都是原子性的,但不能保证多个方法连续调用是原子性的
 */
public class T {
	AtomicInteger count = new AtomicInteger(0);
	void m(){
		for(int i = 0; i < 10000; i++){
			//if count.get() < 1000 //这里如果加上if 就不是原子性的 就会出现不一致的问题
			count.incrementAndGet();
		}
	}
	public static void main(String[] args){
		T t = new T();
		List<Thread> threads = new ArrayList<>();
		for(int i = 0; i < 10; i++){
			threads.add(new Thread(t::m, "thread" + i));
		}
		threads.forEach((o)->o.start());
		threads.forEach((o)->{
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
}
