package com.test.algorithm.search;

import com.test.util.NumberUtil;

import java.util.Arrays;

public class FibonacciSearch {

	/**
	 * 斐波那契查找
	 * mid = low + F(k - 1) - 1
	 * F(k) = F(k - 1) + F(k - 2)
	 * @param arr
	 * @param want
	 * @return
	 */
	public static int search(int[] arr, int want){
		int left = 0;
		int right = arr.length - 1;
		if(want < arr[0] || want > arr[right]){//可导致mid越界
			return -1;
		}
		int k = NumberUtil.getFibonacciMinIndexELKey(arr[right]);//获取大于或等于arr[right]的斐波那契分割数列的元素的下标
		int[] f = NumberUtil.getFibonacciArray(k + 1);//k > right? k + 1 : right + 1
		int[] temp = Arrays.copyOf(arr, f[k]);//需要多少个数组才能使用斐波那契
		for(int i = right + 1; i < temp.length; i++){
			temp[i] = arr[right];
		}
		int mid;
		int ret = -1;
		while(left <= right){
			mid = left + f[k - 1] - 1;
			if(want < temp[mid]){
				right = mid - 1;
				k--;//实际是找k的前半段
			}else if(want > temp[mid]){
				left = mid + 1;
				k -= 2;//实际是找k的后半段
			}else{
				if(mid <= right){//返回小一点的
					ret = mid;
				}else{
					ret = right;
				}
				break;
			}
		}
		return ret;
	}
}
