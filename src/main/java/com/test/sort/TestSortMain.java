package com.test.sort;

import java.util.Random;

public class TestSortMain {
//	public static final int TEST_COUNT = 100;//4000;
	public static final int TEST_COUNT = 10000;
//	public static final  int ARR_COUNT = 20;//10000;
	public static final  int ARR_COUNT = 4000;
	public static final  int RANDOM_RANGE = 100000;
	public static void main(String[] args){
		int testCount = TEST_COUNT;
		long totaltime = 0L;
		boolean isAllRight = true;
		for(int t = 0; t < testCount; t++){
			//执行testCount次测试
			//生成4000长度的数组
//			int creCount = new Random().nextInt(ARR_COUNT);
			int creCount = ARR_COUNT;
			int[] arr = new int[creCount];

			for(int i = 0; i < creCount; i++){
				arr[i] = new Random().nextInt(RANDOM_RANGE);//生成0~9999随机数
//				System.out.println(arr[i]);
			}
			long start = System.currentTimeMillis();
//			SelectionSort.sort0(arr);
//			SelectionSort.sort(arr);
//			BubbleSort.sort(arr);
//			InsertSort.sort(arr);
//			ShellSort.sort(arr);
			MergeSort.sort(arr);
			totaltime = totaltime + System.currentTimeMillis() - start;
			//verify 123
			boolean correct = true;
			for(int i = 1; i < creCount; i++){
				if(arr[i] < arr[i - 1]){
					correct = false;
					break;
				}
			}
			if(correct == false){
				isAllRight = false;
				break;
			}
//			for(int i = 0; i < creCount; i++){
//				System.out.println(arr[i]);
//			}
		}
		System.out.println(isAllRight);
		System.out.println(totaltime);
	}
}
