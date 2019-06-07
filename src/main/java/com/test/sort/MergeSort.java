package com.test.sort;

public class MergeSort {
	static void sort(int[] a, int start, int end, int[] temp){
		if(start >= end) return;
		int mid = start + ((end - start) >> 1);
		sort(a, start, mid, temp);
		sort(a, mid + 1, end, temp);
		merge(a, start, end, mid + 1, temp);
	}

	/**
	 * 归并排序
	 * 平均n * log2 (n)
	 * 最好n * log2 (n)
	 * 最坏n * log2 (n)
	 * 空间(n)
	 * 稳定
	 * 10000次测试长度为4000的数组
	 * 3秒左右
	 */
	public static void sort(int[] a){
		int len = a.length;
		int[] temparr = new int[len];
		sort(a, 0, len - 1, temparr);
	}

	/**
	 * --------------
	 * start | mid end
	 * 1 3 9 | 2 6 8
	 * i     | j
	 * --------------
	 * k
	 * @param a array[]
	 * @param start
	 * @param end
	 * @param mid
	 */
	static void merge(int[] a, int start, int end, int mid, int[] temparr){
		int len = a.length;
//		int mid = (len >> 1) + (len & 1);
//		int[] temparr = new int[len];
		int i = start;// start -> mid
		int j = mid;//mid -> end + 1
		for(int k = start; k < end + 1; k++){
//			System.out.println("i=" + i + " j=" + j + " k=" + k);
			if(j > end){
				temparr[k] = a[i];
				i++;
				continue;
			}else if(i >= mid){
				temparr[k] = a[j];
				j++;
				continue;
			}
			if(a[i] < a[j]){
				temparr[k] = a[i];
				i++;
//				if(i == mid){
//					j++;
//				}
			}else{
				temparr[k] = a[j];
				j++;
//				if(j == len){
//					i++;
//				}
			}
		}
//		print(temparr);
		for(int k = start; k < end + 1; k++){
			a[k] = temparr[k];
		}
	}

	static void mergetest(int[] a){
		int len = a.length;
		int mid = (len >> 1) + (len & 1);
		int[] newarr = new int[len];
		int i = 0;
		int j = i + mid;
		int k = 0;
		for(k = 0; k < len; k++){
//			System.out.println("i=" + i + " j=" + j);
			if(j == len){
				newarr[k] = a[i];
				i++;
				continue;
			}else if(i == mid){
				newarr[k] = a[j];
				j++;
				continue;
			}
			if(a[i] < a[j]){
				newarr[k] = a[i];
				i++;
//				if(i == mid){
//					j++;
//				}
			}else{
				newarr[k] = a[j];
				j++;
//				if(j == len){
//					i++;
//				}
			}
		}
//		print(newarr);
	}

	static void print(int[] arr){
		for(int i = 0; i < arr.length;i++){
			System.out.print(arr[i] + " ");
		}
	}
}
