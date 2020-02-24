package com.test.sort;

import java.util.Random;

public class TestSortMain {
//	public static final int TEST_COUNT = 1;
	public static final int TEST_COUNT = 10000;
//	public static final  int ARR_COUNT = 80000;//10000;
	public static final  int ARR_COUNT = 4000;
//	public static final  int RANDOM_RANGE = 500;
	public static final  int RANDOM_RANGE = 100000;//取值范围0~99999
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
				arr[i] = new Random().nextInt(RANDOM_RANGE) - 0;//生成0~9999随机数
//				System.out.println(arr[i]);
			}
			if(testCount == 1 || false){
				System.out.print("1st:");
				printArr(arr);
			}
			long start = System.currentTimeMillis();

//			SelectionSort.sort0(arr);
//			SelectionSort.sort(arr);
//			BubbleSort.sort(arr);
//			BubbleSort.sort2(arr);
//			InsertSort.sort(arr);
//			InsertSort.sort2(arr);
//			ShellSort.sort(arr);
//			ShellSort.sort2(arr);
//			ShellSort.sort3(arr);
//			MergeSort.sort(arr);
//			QuickSort.sort(arr);
//			QuickSort.sort1(arr);
//			QuickSort.sort2(arr);
//			CountSort.sort(arr);
//			RadixSort.sort(arr);
//			HeapSort.sort(arr);
			HeapSort.sort2(arr);
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

			if(testCount == 1 || false){
				System.out.print("sort:");
				printArr(arr);
			}
//			for(int i = 0; i < creCount; i++){
//				System.out.println(arr[i]);
//			}
		}
		System.out.println(isAllRight);
		System.out.println(totaltime);
	}

	public static void printArr(int[] a){
		for(int i = 0; i < a.length; i++){
//			System.out.print("[" + i + "]" + a[i] + " ");
			System.out.println(a[i] + " ");
		}
		System.out.println();
	}
}
