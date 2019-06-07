package com.test.concurrent.t008;

import java.util.concurrent.TimeUnit;

/**
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 会产生脏读现象
 */
public class Account {
	String name;
	double balance;

	public synchronized void set(String name,double balance) {
		this.name = name;
		/*
		如果在写的过程中 其他线程去读数据 读到未写的数据就产生脏读
		在对应的读取方法中加入synchronized
		*/
		try {
			Thread.sleep(2000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		this.balance = balance;
	}

	public double getBalance(String name) {
		return this.balance;
	}

	public static void main() {
		Account a = new Account();
		new Thread(() -> a.set("zhangsan",100.0)).start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(a.getBalance("zhangsan"));

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(a.getBalance("zhangsan"));
	}
}
