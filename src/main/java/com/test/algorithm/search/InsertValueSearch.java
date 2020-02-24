package com.test.algorithm.search;

public class InsertValueSearch {
	public static int search(int[] arr, int want){
		int left = 0;
		int right = arr.length - 1;
		if(want < arr[0] || want > arr[right]){//可导致mid越界
			return -1;
		}
		int mid;
		int ret = -1;
		while(left <= right){
			mid = left + (right - left) * (want - arr[left]) / (arr[right] - arr[left]);
			if(want < arr[mid]){
				right = mid - 1;
			}else if(want > arr[mid]){
				left = mid + 1;
			}else{
				ret = mid;
				break;
			}
		}
		return ret;
	}
}
