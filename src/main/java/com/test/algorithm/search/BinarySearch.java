package com.test.algorithm.search;

import java.util.List;

public class BinarySearch {
	public static int search(int[] arr, int want){
		int left = 0;
		int right = arr.length - 1;
		int mid;
		int ret = -1;
		while(left <= right){
			mid = (left + right) >> 1;
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

	/**
	 * 二分查找(需要arr是有序的)
	 * 返回一个结果集合list(如果存在多个符合要查找条件的值的索引)
	 * @param result 结果集合
	 */
	public static void searchMulti(int[] arr, int want, List<Integer> result){
		int left = 0;
		int right = arr.length - 1;
		int mid;
		while(left <= right){
			mid = (left + right) >> 1;
			if(want < arr[mid]){
				right = mid - 1;
			}else if(want > arr[mid]){
				left = mid + 1;
			}else{
				result.add(mid);
				int temp = mid;
				while(--temp > -1 && arr[temp] == want){
					result.add(temp);
				}
				temp = mid;
				int len = arr.length;
				while(++temp < len && arr[temp] == want){
					result.add(temp);
				}
				break;
			}
		}
	}

	/**
	 * 二分查找(需要arr是有序的)
	 * 使用递归实现
	 * @return
	 */
	public static int searchSingleByRecursion(int[] arr, int left, int right, int want){
		if(left > right){
			return -1;//出口
		}
		int mid = (left + right) >> 1;
		if(arr[mid] > want){
			return searchSingleByRecursion(arr, left, --mid, want);
		}else if(arr[mid] < want){
			return searchSingleByRecursion(arr, ++mid, right, want);
		}else{
			return mid;
		}
	}

	/**
	 * 二分查找(需要arr是有序的)
	 * 使用递归 返回一个结果集合list(如果存在多个符合要查找条件的)
	 * result.size()==0为未找到符合value的
	 */
	public static void searchMultiByRecursion(int[] arr,int left,int right,int want, List<Integer> result){
		if(left > right){
			return;
		}
		int mid = (left + right) >> 1;
		if(arr[mid] > want){
			searchMultiByRecursion(arr, left, --mid, want, result);
			return;
		}else if(arr[mid] < want){
			searchMultiByRecursion(arr, ++mid, right, want, result);
			return;
		}else{
			int temp = mid;
			while(temp > -1 && arr[temp] == want){
				result.add(temp--);
			}
			temp = mid;
			int len = arr.length;
			while(len > ++temp && arr[temp] == want){
				result.add(temp);
			}
//			return mid;
		}
	}
}
