package com.test.concurrent.t021;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个固定容量的容器,拥有put和get方法,以及getCount方法
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * <p>
 * 使用wait和notify/notifyAll来实现
 * <p>
 * 使用Lock和Condition来实现
 * 对比两种方式,Condition的方式可以更加精确的指定哪些线程被唤醒
 */
public class MyContainer2<T> {
	final private LinkedList<T> lists = new LinkedList<>();
	final private int MAX = 10;
	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition producer = lock.newCondition();
	private Condition customer = lock.newCondition();


	public void put(T t) {
		try {
			lock.lock();
			while(lists.size() == MAX) {
				producer.await();
			}
			lists.add(t);
			++count;
			customer.signalAll();//通知生产者生产
		} catch(InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public T get() {
		T t = null;
		try {
			lock.lock();
			while(lists.size() == 0) {
				customer.await();
			}
			t = lists.removeFirst();
			count--;
			producer.signalAll();
		} catch(InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return t;
	}

	public static void main(String[] args){
		MyContainer2<String> c = new MyContainer2<String>();

		//启用消费者线程
		for(int i = 0; i < 10; i++){
			new Thread(() -> {
				for(int j = 0; j < 5; j++){
					System.out.println(c.get());
				}
			}, "c" + i).start();
		}

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		//启用生产者线程
		for(int i = 0; i < 2; i++){
			new Thread(() -> {
				for(int j = 0; j < 25; j++){
					c.put(Thread.currentThread().getName() + " " + j);
				}
			}, "p" + i).start();
		}
	}
}
