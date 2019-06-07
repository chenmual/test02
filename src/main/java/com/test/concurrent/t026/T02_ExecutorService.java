package com.test.concurrent.t026;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * 继承自Executor
 * 实现execute
 * ExecutorService执行器服务  往里面仍任务
 * 实现submit方法 调用Callable或者Runnable
 */
public class T02_ExecutorService implements ExecutorService {
	@Override
	public void shutdown() {

	}

	@Override
	public List<Runnable> shutdownNow() {
		return null;
	}

	@Override
	public boolean isShutdown() {
		return false;
	}

	@Override
	public boolean isTerminated() {
		return false;
	}

	@Override
	public boolean awaitTermination(long timeout,TimeUnit unit) throws InterruptedException {
		return false;
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		return null;
	}

	@Override
	public <T> Future<T> submit(Runnable task,T result) {
		return null;
	}

	@Override
	public Future<?> submit(Runnable task) {
		return null;
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
		return null;
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,long timeout,TimeUnit unit) throws InterruptedException {
		return null;
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
		return null;
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks,long timeout,TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return null;
	}

	@Override
	public void execute(Runnable command) {

	}
}
