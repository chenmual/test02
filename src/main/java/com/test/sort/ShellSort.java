package com.test.sort;

public class ShellSort {
	/**
	 * 希尔排序
	 * 平均n^1.3
	 * 最好n
	 * 最坏n^2
	 * 空间(1)
	 * 不稳
	 * 10000次测试长度为4000的数组
	 * 37秒左右
	 * sort2 35秒
	 * sort3 30秒
	 */
	public static void sort(int[] a){
		int max_gap = 4;
		int len = a.length;
		for(int gap = max_gap; gap > 0; gap--){//gap 4~1
			for(int i = gap; i < len; i = i + gap){
				for(int j = i; j > 0; j = j - gap){
					if(a[j] < a[j - gap]){
						//j比j - gap小 交换
						int temp = a[j];
						a[j] = a[j - gap];
						a[j - gap] = temp;
					}else{
						break;
					}
				}
			}
		}
	}

	public static void sort2(int[] arr){
		int len = arr.length;
		int temp = 0;
		for(int gap = len >> 1; gap > 0; gap = gap >> 1){
			for(int i = gap; i < len; i++){//i:gap ~ len - 1 i为j的初值
				for(int j = i - gap; j >= 0; j -= gap){
					if(arr[j] > arr[j + gap]){
						temp = arr[j];
						arr[j] = arr[j + gap];
						arr[j + gap] = temp;
					}else{
						break;
					}
				}
			}
		}
	}

	public static void sort3(int[] arr){
		int len = arr.length;
		int temp = 0;
		for(int gap = len >> 1; gap > 0; gap = gap >> 1){
			for(int i = gap; i < len; i++){//i:gap ~ len - 1 i为j的初值
				int j = i;
				temp = arr[j];
				if(arr[j] < arr[j - gap]){
					while(j - gap >= 0 && temp < arr[j - gap]){//arr[j]往前移j-gap
						arr[j] = arr[j - gap];
						j -= gap;
					}
					arr[j] = temp;
				}
			}
		}
	}
}
