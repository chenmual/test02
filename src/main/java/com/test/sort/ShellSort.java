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
	 */
	public static void sort(int[] a){
		int max_gap = 4;
		int len = a.length;
//		System.out.println("len:" + a.length);
		for(int gap = max_gap; gap > 0; gap--){//gap 4~1
			for(int i = gap; i < len; i = i + gap){
//				System.out.println("i:" + i);
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
}
