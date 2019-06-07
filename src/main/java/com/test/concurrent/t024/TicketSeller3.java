package com.test.concurrent.t024;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TicketSeller3 {

	static List<String> tickets = new ArrayList<>();
	static {
		for(int i = 0; i < 1000; i++) {
			tickets.add("票编号:" + i);
		}
	}
	public static void main(String[] args){
	    for(int i = 0; i < 10; i++){
	    	new Thread(() -> {
	    		while(true){
	    			//加锁可以正确执行 但是效率不高
	    			synchronized(tickets){
	    				if(tickets.size() <= 0){
	    					break;
						}
						try {
							TimeUnit.SECONDS.sleep(10);
						} catch(InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("销售了:" + tickets.remove(0));
					}
				}
			}).start();
		}
	}
}
