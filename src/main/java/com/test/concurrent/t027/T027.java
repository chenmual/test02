package com.test.concurrent.t027;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 读(共享锁)
 * 写(排他锁)
 */
public class T027 {

	private Map<String, Object> map = new HashMap<>();
	//读写锁
	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	private Lock r = rwl.readLock();
	private Lock w = rwl.writeLock();

	private volatile boolean isUpdate;

	public void readWrite(){
		r.lock();
		if(isUpdate){
			r.unlock();
			w.lock();
			map.put("xxx", "xxx");
			r.lock();//在持有写锁的时候拿到读锁
			w.unlock();
		}
		Object o = map.get("xxx");
		System.out.println(o);
		r.unlock();
	}
}
