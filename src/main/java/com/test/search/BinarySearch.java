package com.test.search;

public class BinarySearch {
	public static int search(int[] arr, int want){
		int left = 0;
		int right = arr.length - 1;

		int mid;
		int ret = -1;
		while(left < right){
			mid = (left + right) >> 1;
			if(want < arr[mid]){
				right = mid - 1;
			}else if(want > arr[mid]){
				left = mid;
			}else{
				ret = mid;
				break;
			}
		}
		return ret;
	}
}
