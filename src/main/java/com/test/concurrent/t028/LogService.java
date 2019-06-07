package com.test.concurrent.t028;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

public class LogService {

	private BlockingQueue<String> queue;
	private LoggerThread loggerThread;
	private PrintWriter writer;
	private boolean isShutDown;
	private int reservations;

	private class LoggerThread extends Thread{
		public void run(){
			try {
				while(true){
					try {
						synchronized(LogService.this){
							if(isShutDown && reservations == 0){
								break;
							}
							String msg = queue.take();
							synchronized(LogService.this){
								--reservations;
							}
							writer.println(msg);
						}
					}catch(InterruptedException e){
						/**
						 * retry
						 */
					}
				}
			} finally {
				writer.close();
			}
		}
	}

	public void start(){
		loggerThread.start();
	}

	public void stop(){
		synchronized(this){
			isShutDown = true;
		}
		loggerThread.interrupt();
	}

	public void log(String msg) throws InterruptedException{
		synchronized(this){
			if(isShutDown){
				throw new IllegalStateException();
			}
			++reservations;
			queue.put(msg);
		}
	}
}
