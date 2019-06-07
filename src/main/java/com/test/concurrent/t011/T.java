package com.test.concurrent.t011;

import java.util.concurrent.TimeUnit;

/**
 * 程序执行过程中,如果出现异常,默认情况锁会被释放
 * 所以在并发处理过程中,有异常要多加小心,不然可能会发生不一致的情况
 * 比如 在一个web app处理过程中,多个servlet线程共同访问同一个资源,这时如果异常处理不合适
 * 在第一个线程中抛出异常,其他线程就会进入同步代码区,有可能访问到异常产生时的数据
 * 因此要非常小心的处理同步业务逻辑中的异常(必要时要做事务回滚)
 */
public class T {
	int count = 0;
	synchronized void m(){
		System.out.println(Thread.currentThread().getName() + "start");
		while (true){
			count++;
			System.out.println(Thread.currentThread().getName() + "count = " + count);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(count == 5){
				int i = 1/0;//此处抛出异常,锁将被释放,想要不被释放,可以在这里进行catch,再让循环继续
			}
		}
	}
	public static void main(String[] args){
	    T t = new T();
	    Runnable r = new Runnable() {
			@Override
			public void run() {
				t.m();
			}
		};
	    new Thread(r, "r1").start();

	    try{
	    	TimeUnit.SECONDS.sleep(3);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		new Thread(r, "t2").start();
	}
}