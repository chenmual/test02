package com.test.sort;

public class BubbleSort {
	/**
	 * 冒泡排序
	 * n^2
	 * 最好n
	 * 最坏n^2
	 * 空间1
	 * 稳
	 * 10000次测试长度为4000的数组
	 * 152秒
	 * sort2为142秒左右
	 * @param arr
	 */
	public static void sort(int[] arr){
		int len = arr.length;
		for(int i = 0; i < len - 1; i++){
			for(int j = 0; j < len - i - 1; j++){
				if(arr[j] > arr[j + 1]){
					//升序 如果j位置的大于j + 1位置的 则交换
					int temp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

	public static void sort2(int[] arr){
		int len = arr.length - 1;
		int len2 = len - 1;
		boolean needBreack = true;
		for(int i = 0; i < len; i++){
			for(int j = 0; j < len - i; j++){
				if(arr[j] > arr[j + 1]){
					int temp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = temp;
					needBreack = false;
				}
			}
			if(needBreack){
				break;
			}
		}
	}
}
