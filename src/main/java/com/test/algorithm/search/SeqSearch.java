package com.test.algorithm.search;

public class SeqSearch {
	/**
	 * 从数组中查找一个值
	 * @return 该索引或者-1(未找到)
	 */
	public static int searchSingle(int[] arr, int value){
		int retIndex = -1;
		for(int i = arr.length - 1; i > -1; i--){
			if(arr[i] == value){
				return i;
			}
		}
		return retIndex;
	}


	/**
	 * 查找多个值
	 * 遍历两边,1统计查找结果数量
	 * 2将就结果存入结果数组
	 * @return 返回结果索引数组或者null(未查找到)
	 */
	public static int[] searchMulti(int[] arr, int value){
		int count = 0;
		for(int i = arr.length - 1; i > -1; i--){
			if(arr[i] == value){
				count++;
			}
		}
		if(count == 0){
			return null;
		}
		int[] ret = new int[count];
		count = 0;
		for(int i = arr.length - 1; i > -1; i--){
			if(arr[i] == value){
				ret[count++] = i;
			}
		}
		return ret;
	}
}
