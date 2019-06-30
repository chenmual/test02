package com.test.sort;

public class CountSort {
	/**
	 * 计数排序
	 * 平均n + k
	 * 最好n + k
	 * 最坏n + k
	 * 空间(n + k)
	 * 稳
	 * 非比较排序
	 * 适用于数组中value范围比较小,但是length比较大的排序
	 * 比如年龄(16~110),学分(0~150)
	 * @param arr
	 */
	public static void sort(int[] arr){
		//获取值的范围
		int len = arr.length;
		int min = arr[0];
		int max = arr[0];
		for(int i = 1;i < len; i++){
			if(max < arr[i]){
				max = arr[i];
			}
			if(min > arr[i]){
				min = arr[i];
			}
		}

		int countlen = max - min + 1;
		//{0, 0, 0, 1, 1, 1, 1, 2, 2}
		//countArr[0] = 3; countArr[1] = 4;
		int[] countArr = new int[countlen];
		int gap = min;
		for(int i = 0; i < len; i++){
			countArr[arr[i] - gap]++;
		}

//		int[] result = new int[len];
		int r = 0;
		for(int i = 0;i < countlen; i++){
			for(int j = 0; j < countArr[i]; j++){
				arr[r++] =  i + gap;
			}
		}
	}
}
