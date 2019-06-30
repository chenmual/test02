package com.test.sort;

public class InsertSort {
	/**
	 * 插入排序
	 * 平均n^2
	 * 最好n
	 * 最坏n^2
	 * 空间(1)
	 * 稳
	 * 适合基本有序的排序
	 * 10000次测试长度为4000的数组
	 * 26秒
	 * sort2使用17秒
	 * @param arr
	 */
	public static void sort(int[] arr){
		int len = arr.length;
		for(int i = 1; i < len; i++){
			//i 从1 -> len - 1
			for(int j = i; j > 0; j--){
				//j 从i ->  1
				//如果j比较小 则往前换
				if(arr[j] < arr[j - 1]){
					int temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				}else{
					break;
				}
			}
		}
	}

	public static void sort2(int[] arr){
		int len = arr.length;
		int insertVal = 0;
		int insertIndex = 0;
		for(int i = 1; i < len; i++){
			insertVal = arr[i];
			insertIndex = i - 1;
			while(insertIndex >= 0 && insertVal < arr[insertIndex]){
				arr[insertIndex + 1] = arr[insertIndex];
				insertIndex--;
			}
			if(insertIndex + 1 != i){
				arr[insertIndex + 1] = insertVal;
			}
		}
	}
}
