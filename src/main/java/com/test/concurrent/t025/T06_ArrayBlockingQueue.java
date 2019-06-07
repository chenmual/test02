package com.test.concurrent.t025;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T06_ArrayBlockingQueue{
	//有界队列
	static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);
	static Random r = new Random();
	public static void main(String[] args) throws InterruptedException{
	    for(int i = 0; i < 10; i++){
			strs.put("a" + i);
		}

		strs.put("aaaa");//阻塞式 满了会阻塞
//		strs.add("aaaa");加失败之后会抛异常
		boolean ret = strs.offer("aaaa");
	    ret = strs.offer("aaa", 1,TimeUnit.SECONDS);//按时间段阻塞 1秒后返回结果
		System.out.println(strs);
	}
}
