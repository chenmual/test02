package com.test.concurrent.t020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用reentrantlock可以完成同样功能
 * 需要注意的是,必须要必须要必须要手动释放锁
 * 使用synchronized的话如果遇到异常,jvm会释放锁,但是lock必须手动释放锁,因此经常在finally中进行锁的释放
 *
 * 使用reentrantlock可以进行"尝试锁定"trylock,这样无法锁定,或者在指定时间内无法锁定
 *
 * 使用ReentrantLock还可以调用lockInterruptibly方法,可以对线程interrupt方法做出响应
 */
public class ReentrantLock4 {
	public static void main(String[] args){
		Lock lock = new ReentrantLock();
		Thread t1 = new Thread(() -> {
			try {
				lock.lock();
				System.out.println("t1 start");
				TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
			} catch(InterruptedException e) {
				System.out.println("interrupted!");
			} finally {
				lock.unlock();
			}
		});
		t1.start();

		Thread t2 = new Thread(() -> {
			boolean locked = false;
			try {
				//t2永远获取不到lock锁 一直在阻塞(lockInterruptibly可以被其他线程打断)
				lock.lockInterruptibly();
				locked = true;
				System.out.println("t2 start");
				TimeUnit.SECONDS.sleep(5);
				System.out.println("t2 end");
			} catch(InterruptedException e) {
				System.out.println("t2 interrupted!");
//				e.printStackTrace();
			} finally {
				if(locked){
					lock.unlock();
				}
			}
		});
		t2.start();

		//1秒后 主线程可以打断t2的阻塞
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		t2.interrupt();
	}
}
