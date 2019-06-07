package com.test.concurrent.t020;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock还可以被指定为公平锁
 */
public class ReentrantLock5 extends Thread{
	//ReentrantLock(true = 公平锁)
	private static ReentrantLock lock = new ReentrantLock(true);

	public void run(){
		for(int i = 0; i < 100; i++){
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + "获得锁");
			}finally {
				lock.unlock();
			}
		}
	}
	public static void main(String[] args){
	    ReentrantLock5 rl = new ReentrantLock5();
	    Thread th1 = new Thread(rl);
	    Thread th2 = new Thread(rl);
	    th1.start();
	    th2.start();
	}
}
