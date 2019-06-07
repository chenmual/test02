package com.test.search;

import com.test.sort.InsertSort;

import java.util.Random;

public class TestSearchMain {

	public static void main(String[] args){
		long start = System.currentTimeMillis();
		int testCount = 1;
		boolean isAllRight = true;
		for(int t = 0; t < testCount; t++){
			//执行testCount次测试
			//生成10000长度的数组
			int creCount = 1000;
			int[] arr = new int[creCount];

			for(int i = 0; i < creCount; i++){
				arr[i] = new Random().nextInt(1000);//生成0~9999随机数
//				System.out.println(arr[i]);
			}
//			SelectionSort.sort0(arr);
//			BubbleSort.sort(arr);
			InsertSort.sort(arr);
			//verify
			for(int i = 0; i < creCount; i++){
				System.out.println(i + ":" + arr[i]);
			}
			int wantIndex = new Random().nextInt(creCount);
			System.out.println("want:" + arr[wantIndex]);
			System.out.println(BinarySearch.search(arr, arr[wantIndex]));
		}
		System.out.println(isAllRight);
		System.out.println(System.currentTimeMillis() - start);
	}
}
