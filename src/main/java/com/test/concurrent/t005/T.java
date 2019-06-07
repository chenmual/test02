package com.test.concurrent.t005;

/**
 * 分析一下这个程序的输出
 */
public class T implements Runnable{
	private int count = 10;//5个线程共同访问一个对象的成员
//	@Override
	public /*synchronized */ void run(){
//		try {
//			TimeUnit.SECONDS.sleep(1);
//		} catch(InterruptedException e) {
//			e.printStackTrace();
//		}
		count--;
//		try {
//			TimeUnit.SECONDS.sleep(1);
//		} catch(InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println(Thread.currentThread().getName() + "count = " + count);
	}
	public static void main(String[] args){
		T t = new T();
		for (int i = 0;i < 5; i++){
			new Thread(t, "THREAD" + i).start();
//			try {
//				TimeUnit.MILLISECONDS.sleep(30);
//			} catch(InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}
}
