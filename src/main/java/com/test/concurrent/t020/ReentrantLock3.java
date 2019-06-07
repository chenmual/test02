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
 */
public class ReentrantLock3 {
	Lock lock = new ReentrantLock();

	void m1(){
		lock.lock();
		try {
			for(int i = 0; i < 10; i++){
				TimeUnit.SECONDS.sleep(1);
				System.out.println(i);
			}
		} catch(InterruptedException e){
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 使用trylock进行尝试锁定,不管锁定与否,方法都将继续进行
	 * 可以根据trylock的返回值来确定是否锁定
	 */
	void m2(){
//		boolean locked = lock.tryLock();
//		System.out.println("m2 ..." + locked);
//		if(locked){
//			lock.unlock();
//		}

		boolean locked = false;
		try {
			locked = lock.tryLock(5, TimeUnit.SECONDS);
			System.out.println("m2 .." + locked);
		} catch(InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(locked){
				lock.unlock();
			}
		}
	}
	public static void main(String[] args){
	    ReentrantLock3 rl = new ReentrantLock3();
	    new Thread(rl::m1).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(rl::m2).start();
	}
}
