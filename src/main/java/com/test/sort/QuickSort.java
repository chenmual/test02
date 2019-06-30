package com.test.sort;

import com.test.util.RandomUtil;

public class QuickSort {
	/**
	 * 单轴快速排序
	 * 平均n * log2 (n)
	 * 最好n * log2 (n)
	 * 最坏n ^ 2
	 * 空间(log2 (n))
	 * 不稳定
	 * 10000次测试长度为4000的数组
	 * 3秒左右
	 * sort1为3.5秒左右
	 * sort2为3.2秒左右
	 * @param arr
	 */
	public static void sort(int[] arr){
		sortByDoublePivot(arr, 0, arr.length - 1);
	}

	public static void sortByDoublePivot(int[] arr, int start, int end){
		if(start >= end) return;
		int midPivotIndex = end;
		int leftPivotIndex = start;
		int rightPivotIndex = end - 1;
		while(leftPivotIndex <= rightPivotIndex){
			while(leftPivotIndex <= rightPivotIndex && arr[leftPivotIndex] <= arr[midPivotIndex]){
				leftPivotIndex++;
			}
			while(leftPivotIndex <= rightPivotIndex && arr[rightPivotIndex] > arr[midPivotIndex]){
				rightPivotIndex--;
			}
			//此时arr[leftPivotIndex]>=arr[midPivotIndex]>=arr[rightPivotIndex]
			if(leftPivotIndex < rightPivotIndex){
				int temp = arr[leftPivotIndex];
				arr[leftPivotIndex] = arr[rightPivotIndex];
				arr[rightPivotIndex] = temp;
			}
		}

		int temp = arr[leftPivotIndex];
		arr[leftPivotIndex] = arr[midPivotIndex];
		arr[midPivotIndex] = temp;

		sortByDoublePivot(arr, start, leftPivotIndex - 1);
		sortByDoublePivot(arr, leftPivotIndex + 1, end);
	}
	//以随机index为单轴
	public static void sortBySingleRandomPivot1(int[] arr,int start,int end/*, int deep*/){
		if(start >= end) return;
//		System.out.println("---start");
		int midPivotIndex = start + RandomUtil.s_rnd.nextInt((end - start) + 1);
		int leftPivotIndex = start;
		int rightPivotIndex = end;
		while(leftPivotIndex <= rightPivotIndex){
			while(leftPivotIndex <= rightPivotIndex && arr[leftPivotIndex] <= arr[midPivotIndex]){
				leftPivotIndex++;
				//错过随机索引轴
				if(leftPivotIndex == midPivotIndex){
					leftPivotIndex++;
//					overleft = true;
				}
			}
			while(leftPivotIndex <= rightPivotIndex && arr[rightPivotIndex] > arr[midPivotIndex]){
				rightPivotIndex--;
				//错过随机索引轴
				if(rightPivotIndex == midPivotIndex){
					rightPivotIndex--;
				}
			}
//			if(overleft){
//				leftPivotIndex--;
//			}
			if(leftPivotIndex < rightPivotIndex){
				int temp = arr[leftPivotIndex];
				arr[leftPivotIndex] = arr[rightPivotIndex];
				arr[rightPivotIndex] = temp;
			}
//			System.out.print("-l=" + leftPivotIndex + " ");
//			System.out.print("r=" + rightPivotIndex + " ");
//			System.out.print("m=" + midPivotIndex + "|");
//			TestSortMain.printArr(arr);
		}

//		if(leftPivotIndex > end){
//			leftPivotIndex = end;
//		}
		if(leftPivotIndex > end || (arr[leftPivotIndex] > arr[midPivotIndex] && leftPivotIndex > midPivotIndex)){
			//以right为新轴
			leftPivotIndex--;
			int temp = arr[leftPivotIndex];
			arr[leftPivotIndex] = arr[midPivotIndex];
			arr[midPivotIndex] = temp;
//			System.out.println("------right");
//			System.out.print("-l=" + leftPivotIndex + " ");
//			System.out.print("r=" + rightPivotIndex + " ");
//			System.out.print("m=" + midPivotIndex + "|");
//			TestSortMain.printArr(arr);
//			System.out.println("-------next: " + start + "-" + (leftPivotIndex - 1) + " " + (leftPivotIndex + 1) + "-" + end + " deep=" + deep);
			sortBySingleRandomPivot1(arr, start, leftPivotIndex - 1/*, deep + 1*/);
			sortBySingleRandomPivot1(arr, leftPivotIndex + 1, end/*, deep + 1*/);
		}else{

			int temp = arr[leftPivotIndex];
			arr[leftPivotIndex] = arr[midPivotIndex];
			arr[midPivotIndex] = temp;

//			System.out.println("------left");
//			System.out.print("-l=" + leftPivotIndex + " ");
//			System.out.print("r=" + rightPivotIndex + " ");
//			System.out.print("m=" + midPivotIndex + "|");
//			TestSortMain.printArr(arr);
//			System.out.println("-------next: " + start + "-" + (leftPivotIndex - 1) + " " + (leftPivotIndex + 1) + "-" + end + " deep=" + deep);
			sortBySingleRandomPivot1(arr, start, leftPivotIndex - 1/*, deep + 1*/);
			sortBySingleRandomPivot1(arr, leftPivotIndex + 1, end/*, deep + 1*/);
		}
	}

	/**
	 * 以随机index作为单轴(替代lastindex)
	 * 降低遇到最差时间复杂度的可能
	 * @param arr
	 */
	public static void sort1(int[] arr){
		sortBySingleRandomPivot1(arr, 0, arr.length - 1);
	}

	/**
	 * 选择中间位置的值作为中轴线
	 * @param arr
	 */
	public static void sort2(int[] arr){
		sortBySinglePivot(arr, 0, arr.length - 1);
	}

	public static void sortBySinglePivot(int[] arr, int left, int right){
		int l = left;
		int r = right;
		int pivot = arr[(left + right) >> 1];
		while(l < r){
			while(arr[l] < pivot){//左边第一个大于pivot的
				l++;
			}
			while(arr[r] > pivot){//右边第一个大于pivot
				r--;
			}
			if(l >= r){//左<=右
				break;
			}
			int temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;
			if(arr[l] == pivot){
				r--;
			}
			if(arr[r] == pivot){
				l++;
			}
		}
		if(l == r){//让l与r不等退出递归
			l++;
			r--;
		}
		if(left < r){
			sortBySinglePivot(arr, left, r);
		}
		if(right > l){
			sortBySinglePivot(arr, l, right);
		}
	}
}
