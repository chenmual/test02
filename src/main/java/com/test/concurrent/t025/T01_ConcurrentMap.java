package com.test.concurrent.t025;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * 并发容器
 * http://blog.csdn.net/sunxianghuang/article/details/52221913
 * http://www.educity.cn/java/498061.html
 * 阅读ConcurrentSkipListMap 跳表实现
 */
public class T01_ConcurrentMap {
	public static void main(String[] args){
		//并发MAP
		Map<String, String> map = new Hashtable<>();//效率最低
//		Map<String, String> map = Collections.synchronizedMap(new HashMap<>());//等同于hashtable
//		Map<String, String> map = new ConcurrentHashMap<>();//使用分段锁 1.8之后采用CAS替代分段锁


		//高并发并且排序
////	SortedMap<String, String> map = new TreeMap<>();//不安全
//		Map<String, String> map = new ConcurrentSkipListMap<>();//插入时效率稍微低 但是是排序的

		Random r = new Random();
		Thread[] ths = new Thread[100];
		CountDownLatch latch = new CountDownLatch(ths.length);
		long start = System.currentTimeMillis();
		for(int i = 0; i < ths.length; i++){
			ths[i] = new Thread(() -> {
				for(int j = 0; j < 10000; j++){
					map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
					latch.countDown();
				}
			});
		}

		Arrays.asList(ths).forEach(t -> t.start());
		try {
			latch.await();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
