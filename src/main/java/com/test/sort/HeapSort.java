package com.test.sort;

import com.sun.glass.ui.Size;

import java.util.Arrays;

public class HeapSort {
	/**
	 * 堆排序
	 * 平均n * log2 (n)
	 * 最好n * log2 (n)
	 * 最坏n * log2 (n)
	 * 空间(1)
	 * 不稳定
	 * 10000次测试长度为4000的数组
	 * 2.6秒左右
	 */
	public static void sort(int[] arr){
		int len = arr.length;
		//i将数组构成一个大顶堆
		for(int i = (len >> 1) - 1; i >= 0; i--){
			adjustHeap(arr, i, len);
		}

		int temp;
		for(int j = len - 1; j > 0; j--){
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustHeap(arr, 0, j);
		}
	}

	/**
	 *
	 * @param arr
	 * @param i 非叶子节点在数组中的索引 寻找以i为父节点的子树的最大值
	 * @param length 多少个元素进行调整 length在逐渐减少
	 */
	public static void adjustHeap(int[] arr, int i, int length){
		int temp = arr[i];
		for(int k = (i << 1) + 1; k < length; k = (k << 1) + 1){//k为i的左子节点,k=k的左子节点
			if(k + 1 < length && arr[k] < arr[k + 1]){//左子节点小于右子节点
				k++;//k指向右子节点
			}
			if(arr[k] > temp){//子节点大于父节点
				arr[i] = arr[k];//i赋值,此时i最大
				i = k;//i指向k
			}else{
				break;
			}
		}
		arr[i] = temp;
	}


	public static void sort2(int[] arr){
		int len = arr.length;
		buildHeap(arr, len);
		for(int i = len - 1; i >= 0; i--){
			swap(arr, 0, i);
			heapify(arr, i, 0);
		}
	}


	/**
	 * 把一个树  从最后一个非叶子节点 到 根节点 依次堆化
	 * 最终tree就是堆化后的(大顶堆, 根节点最大)
	 * @param tree
	 * @param n
	 */
	public static void buildHeap(int[] tree, int n){
		int thelast = n - 1;//最后一个节点
		int parent = (thelast - 1) >> 1;//找到最后一个非叶子节点 往根节点方向遍历
		for(int i = parent; i >= 0; i--){
			heapify(tree, n, i);
		}
	}

	/**
	 * 对第i个节点进行堆化(转换成以该节点为根的大顶堆)
	 * @param tree
	 * @param n
	 * @param i
	 */
	public static void heapify(int[] tree, int n, int i){
		if(i >= n){//出口
			return;
		}
		int left = (i << 1) + 1;
		int right = (i << 1) + 2;

		int max = i;
		if(left < n && tree[max] < tree[left]){//max跟left比较
			max = left;
		}
		if(right < n && tree[max] < tree[right]){//max跟right比较
			max = right;
		}
		if(max != i){//如果顶部元素不是max则与max对调
			swap(tree, i, max);
			heapify(tree, n, max);//继续向下寻找最大元素,完成一次建堆
		}
	}

	public static void swap(int[] arr, int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
