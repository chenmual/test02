package com.test.algorithm.array;

/**
 * 稀疏数组
 */
public class SparseArray {
	public static final int ROW_INDEX = 0;
	public static final int COL_INDEX = 1;
	public static final int VAL_INDEX = 2;

	public static final int FIRST_INDEX = 0;
	private int[][] array = null;
	private int defaultVal = 0;

	/**
	 * 将一个arr[][]转换成稀疏数组SparseArray
	 * @param arr
	 * @param defaultVal
	 * @return
	 */
	public static SparseArray makeSparseArray(int[][] arr, int defaultVal){
		SparseArray ret = new SparseArray();
		ret.defaultVal = defaultVal;
		int row = arr.length;
		int valCount = 0;
		int col = 0;
		//遍历有效个数
		for(int i = 0; i < row; i++){
			col = arr[i].length;
			for(int j = 0; j < col; j++){
				if(arr[i][j] != defaultVal){
					valCount++;
				}
			}
		}
		ret.array = new int[valCount + 1][3];
		//第一行为表格默认数据
		ret.array[FIRST_INDEX][ROW_INDEX] = row;
		ret.array[FIRST_INDEX][COL_INDEX] = col;
		ret.array[FIRST_INDEX][VAL_INDEX] = defaultVal;
		int count = 1;
		for(int i = 0; i < row; i++){
			col = arr[i].length;
			for(int j = 0; j < col; j++){
				if(arr[i][j] != defaultVal){
					ret.array[count][ROW_INDEX] = i;
					ret.array[count][COL_INDEX] = j;
					ret.array[count][VAL_INDEX] = arr[i][j];
					count++;
				}
			}
		}
		return ret;
	}

	/**
	 * 把一个sparseArray转换成arr[][]
	 * @return
	 */
	public int[][] transformToArray(){
		int max = this.array[FIRST_INDEX][ROW_INDEX] * this.array[FIRST_INDEX][COL_INDEX];
		int[][] ret = new int[this.array[FIRST_INDEX][ROW_INDEX]][this.array[FIRST_INDEX][COL_INDEX]];
//		if((max >> 4) > this.array[FIRST_INDEX][VAL_INDEX]){
//		}
		for(int i = 0; i < this.array[FIRST_INDEX][ROW_INDEX]; i++){
			for(int j = 0; j < this.array[FIRST_INDEX][COL_INDEX]; j++){
				ret[i][j] = this.defaultVal;
			}
		}
		int len = array.length;
		for(int i = 1; i < len; i++){
			ret[array[i][ROW_INDEX]][array[i][COL_INDEX]] = array[i][VAL_INDEX];
		}
		return ret;
	}
}
