package com.test.algorithm.search;

import com.test.sort.InsertSort;
import com.test.sort.RadixSort;
import com.test.util.RandomUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestSearchMain {

	public static void main(String[] args){
		for(int i = 0; i < 1; i++){
			testBi02();
//			testBi03();
		}
//		testSeq02();
	}

	public static void testBi03(){
		int len = 20;
		int[] arr = new int[len];//长度为20的数组
		RandomUtil.putRandomNumberIntoArray(arr, 3, 13);//将3~12放入数组
		RadixSort.sort(arr);//排序
		for(int i = 0; i < len; i++){
			System.out.println("[" + i + "]" + arr[i]);
		}

		int find = arr[RandomUtil.s_rnd.nextInt(len)];
		System.out.println("要找到find" + find);
		List<Integer> result = new ArrayList<>();
//		BinarySearch.searchMultiByRecursion(arr, 0, len, find, result);
		BinarySearch.searchMulti(arr, find, result);
		System.out.println(result);
	}

	public static void testBi02(){
		//生成一串有序数组
		int[] arr = new int[100];
		int len = arr.length;
		for(int i = 0 ; i < len; i++){
			arr[i] = i;
		}
		//从中随机挑选20个数作为一个新数组
		int[] testArray = RandomUtil.getRandomArrayFromSourceArray(arr, 20, false);
		//将新数组排序
		RadixSort.sort(testArray);
		Arrays.stream(testArray).forEach(System.out::println);

		int find = testArray[RandomUtil.s_rnd.nextInt(testArray.length)];
		System.out.println("需要查找:" + find);
//		int ret = BinarySearch.searchSingleByRecursion(testArray, 0, testArray.length - 1, find);
		int ret = BinarySearch.search(testArray, find);
		if(ret == -1){
			System.out.println("没有找到");
		}else{
			System.out.println("找到了" + testArray[ret] + " 索引:" + ret);
		}
		int ret1 = InsertValueSearch.search(testArray, find);
		if(ret1 == -1){
			System.out.println("没有找到");
		}else{
			System.out.println("找到了A" + testArray[ret1] + " 索引:" + ret1);
		}
		int ret2 = FibonacciSearch.search(testArray, find);
		if(ret2 == -1){
			System.out.println("没有找到");
		}else{
			System.out.println("找到了B" + testArray[ret2] + " 索引:" + ret2);
		}
	}

	public static void testSeq02(){
		int[] arr = new int[]{0, 0, 2, 2, 3, 4, 5, 6, 6, 7};
		int[] ret = SeqSearch.searchMulti(arr, 6);
		Arrays.stream(ret).forEach(System.out::println);
	}
	public static void testBi01(){
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
