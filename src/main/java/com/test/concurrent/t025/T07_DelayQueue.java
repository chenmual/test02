package com.test.concurrent.t025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 执行定时任务
 */
public class T07_DelayQueue {
	static BlockingQueue<MyTask> tasks = new DelayQueue<>();

	static class MyTask implements Delayed{
		long runningTime;
		String tname;

		MyTask(long rt, String name){
			this.runningTime = rt;
			this.tname = name;
		}

		@Override
		public long getDelay(TimeUnit unit) {
//			long ret = unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
//			System.out.println(runningTime - System.currentTimeMillis());
//			System.out.println("unit:" + ret);
//			return ret;
			return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}

		@Override
		public int compareTo(Delayed o) {
			if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
				return -1;
			} else if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
				return 1;
			} else {
				return 0;
			}
		}
		@Override
		public String toString(){
			return "" + runningTime;
		}
	}
	public static void main(String[] args) throws InterruptedException {
		long now = System.currentTimeMillis();
		MyTask t1 = new MyTask(now + 1000, "t1");
		MyTask t2 = new MyTask(now + 2000, "t2");
		MyTask t3 = new MyTask(now + 1500, "t3");
		MyTask t4 = new MyTask(now + 2500, "t4");
		MyTask t5 = new MyTask(now + 5500, "t5");

		tasks.put(t1);
		tasks.put(t2);
		tasks.put(t3);
		tasks.put(t4);
		tasks.put(t5);

		System.out.println(tasks);
		for(int i = 0; i < 5; i++){
			Delayed d = tasks.take();
			System.out.println(d);
			System.out.println(((MyTask) d).tname);
		}
	}
}
