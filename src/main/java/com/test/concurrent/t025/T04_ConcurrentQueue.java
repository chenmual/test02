package com.test.concurrent.t025;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T04_ConcurrentQueue {
	public static void main(String[] args){
		//无界队列
		Queue<String> strs = new ConcurrentLinkedQueue<>();
		for(int i = 0; i < 10; i++){
			strs.offer("a" + i);//类似于add
		}
		System.out.println(strs);
		System.out.println(strs.size());
		System.out.println(strs.poll());//取队列头
		System.out.println(strs.size());
		System.out.println(strs.peek());//取队列头不删
		System.out.println(strs.size());

		//双端队列Deque
	}
}
