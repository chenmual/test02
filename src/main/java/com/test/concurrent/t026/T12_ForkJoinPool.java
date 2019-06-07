package com.test.concurrent.t026;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * 一项大任务 切分成若干小任务 小任务还可以继续分 然后把结果合并join
 * 切分规则程序指定 由ForkJoinPool自行执行
 * FJP是精灵线程
 */
public class T12_ForkJoinPool {
	static int[] nums = new int[1000000];//1000000个数初始化后求和
	static final int MAX_NUM = 5000;
	static Random r = new Random();
	static {
		for(int i = 0; i < nums.length; i++){
			nums[i] = r.nextInt(100);
		}
		System.out.println(Arrays.stream(nums).sum());//第一种方式:遍历求和

	}

	/**
	 * ForkJoinPool里必须是ForkJoinTask
	 * RecursiveAction没有返回值
	 */
	static class AddTask extends RecursiveAction{
		int start, end;

		AddTask(int s, int e){
			this.start = s;
			this.end = e;
		}
		@Override
		protected void compute() {
			//小于50000直接计算
			if(end - start <= MAX_NUM){
				long sum = 0L;
				for(int i = start; i < end; i++){
					sum += nums[i];
				}
				System.out.println("from: " + start + " to: " + end + " = " + sum);
			}else{
				//大于50000切分任务 交给子任务执行
				int middle = start + (end - start) / 2;
				AddTask subTask1 = new AddTask(start, middle);
				AddTask subTask2 = new AddTask(middle, end);
				subTask1.fork();//fork -> 有新的线程启动
				subTask2.fork();
			}
		}
	}

	/**
	 * ForkJoinPool里必须是ForkJoinTask
	 * RecursiveTask<返回类型>有返回值
	 */
	static class AddTask2 extends RecursiveTask<Long> {

		int start, end;
		AddTask2(int s, int e){
			this.start = s;
			this.end = e;
		}
		@Override
		protected Long compute() {
			if(end - start < MAX_NUM){
				long sum = 0L;
				for(int i = start; i < end; i++){
					sum += nums[i];
				}
				return sum;
			}else{
				int middle = start + (end - start) / 2;
				AddTask2 subTask1 = new AddTask2(start, middle);
				AddTask2 subTask2 = new AddTask2(middle, end);
				subTask1.fork();
				subTask2.fork();

				return subTask1.join() + subTask2.join();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		ForkJoinPool fjp = new ForkJoinPool();
		AddTask task = new AddTask(0, nums.length);
		fjp.execute(task);
		System.out.println("fjp1 end");
		System.in.read();

		AddTask2 task2 = new AddTask2(0, nums.length);
		fjp.execute(task2);
		long result = task2.join();//如果使用join本身是阻塞线程的 所以不需要System.in.read();
		System.out.println(result);
	}
}
