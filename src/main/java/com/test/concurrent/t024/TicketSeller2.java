package com.test.concurrent.t024;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TicketSeller2 {
	//Vector是同步容器  是线程安全的
	static Vector<String> tickets = new Vector<>();
	static {
		for(int i = 0; i < 1000; i++) {
			tickets.add("票编号:" + i);
		}
	}
	public static void main(String[] args){
	    for(int i = 0; i < 10; i++){
	    	new Thread(() -> {
	    		//但是判断和remove操作分离了(不是原子的 所以会出问题)
	    		while(tickets.size() > 0){
//					try {
//						TimeUnit.SECONDS.sleep(10);
//					} catch(InterruptedException e) {
//						e.printStackTrace();
//					}
					System.out.println("销售了--" + tickets.remove(0));
				}
			}).start();
		}
	}
}
