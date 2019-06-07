package com.test.concurrent.t026;

import java.util.concurrent.Executor;

/**
 * 执行一个任务
 */
public class T01_MyExecutor implements Executor {

	public static void main(String[] args){
		new T01_MyExecutor().execute(() -> System.out.println("hello world"));
	}
	@Override
	public void execute(Runnable command) {
		command.run();
	}
}
