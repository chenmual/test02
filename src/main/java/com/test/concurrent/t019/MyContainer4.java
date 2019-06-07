package com.test.concurrent.t019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 面试题:
 * 实现一个容器,提供两个方法:add, size
 * 写两个线程.线程1添加10个元素到容器中,线程2实现监控元素的个数,当数到5个时,线程2输出并结束
 *
 * 给list添加volatile之后,t2能够接到通知,但是,t2线程的死循环很浪费cpu,如果不用死循环怎么做呢?
 *
 * 这里使用wait和notify可以做到,wait会释放锁,而notify不会释放锁
 * 需要注意的是,运用这种方法必须要保证t2先执行,也就是首先让t2监听才可以
 *
 * 分析下面程序
 * 可以读到输出结果并不是size=5时退出,而是t1结束时t2才接到通知退出
 *
 * notify之后,t1必须释放锁,t2退出后,也必须使用notify,通知t1继续执行
 * 整个通信过程比较繁琐
 */
public class MyContainer4 {
	//添加volatile使t2得到通知
	volatile List<String> list = new ArrayList<>();

	public void add(String s) {
		list.add(s);
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		MyContainer4 c = new MyContainer4();

		final Object lock = new Object();

		new Thread(() -> {
			synchronized(lock){
				System.out.println("t2启动");
				if(c.size() != 5){
					try {
						lock.wait();
					} catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				System.out.println("t2 结束");
				lock.notify();
			}
		}, "t2").start();

		try {
			TimeUnit.SECONDS.sleep(1);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		new Thread(() -> {
			System.out.println("t1启动");
			synchronized(lock){
				for(int i = 0; i < 10; i++) {
					c.add(String.valueOf(i));
					System.out.println("add " + i);

					if(c.size() == 5){
						lock.notify();
						//释放锁让t2得以执行
						try {
							lock.wait();
						} catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("t1 结束");
			}
		}, "t1").start();
	}

}
