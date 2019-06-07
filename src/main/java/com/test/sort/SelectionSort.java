package com.test.sort;

public class SelectionSort {
	/**
	 * 选择排序
	 * 平均n^2
	 * 最好n^2
	 * 最坏n^2
	 * 空间(1)
	 * 不稳
	 * 10000次测试长度为4000的数组
	 * 此方法为30秒左右
	 * sort0为35秒左右
	 * 太慢了
	 * 每次遍历筛选出最小的和最大的
	 */
	public static void sort(int[] a){
		int len = a.length;
		int halflen = len >> 1;
		for(int i = 0; i < halflen + 1; i++){
			int min = i;//最小index
			int max = len - i - 1;//最大index
			int minValue = a[i];
			int maxValue = a[len - i - 1];
			for(int j = i; j < len - i; j++){//0~len - 1
				if(maxValue < a[j]){
					max = j;
					maxValue = a[j];
				}
				if(minValue > a[j]){
					min = j;
					minValue = a[j];
				}
			}
			//遍历之后交换把最小的放在最左边  把最大的放在最右边
			if(maxValue > a[len - i - 1]){
				//交换
				int temp = a[max];
				a[max] = a[len - i - 1];
				a[len - i - 1] = temp;
//				System.out.println("max=" + maxValue + " i=" + i + " max=" + max);
				if(min == len - i - 1){
					//注意 这里最大的与最右边的交换后可能把最小的放在max索引位置了 所以这里min要==max
					min = max;
				}
			}
			if(minValue < a[i]){
				int temp = a[min];
				a[min] = a[i];
				a[i] = temp;
//				System.out.println("min=" + minValue + " i=" + i + " min=" + min);
			}
//			for(int m = 0; m < len; m++){
//				System.out.print(a[m] + ", ");
//			}
//			System.out.println();
		}
	}

	/**
	 * 单边选择排序
	 * @param a
	 */
	public static void sort0(int[] a){
		int len = a.length;
		for(int i = 0; i < len; i++){
			int min = 0;
			int minValue = a[i];
			for(int j = i; j < len; j++){
				if(minValue > a[j]){
					min = j;
					minValue = a[j];
				}
			}
			if(minValue < a[i]){
				//交换
				int temp = a[min];
				a[min] = a[i];
				a[i] = temp;
			}
		}
	}
}
