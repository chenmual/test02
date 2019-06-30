package com.test.algorithm.recursion;

public class QueenEight {
	public static void main(String[] args){
		calc();
	}
	public static final int max = 8;
	public static int[] array = new int[max];//只需要1维数组arr[行] = 列 代表一个皇后位置(一共8个)
	public static int count = 0;
	public static void calc(){
		tryPutNextLine(0);
		System.out.println("一共" + count + "次");
	}

	public static boolean checkPos(int n){
		for(int i = 0; i < n; i++){
			//array[i] == array[n]判断列数相同
			//行差 = 列差 -> 在同一斜线
			if(array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
				return false;
			}
		}
		return true;
	}

	/**
	 * @param n 当前行 0~8
	 */
	public static void tryPutNextLine(int n){
		if(n == max){//8个皇后已经放好
			printArr();
			return;
		}
		for(int i = 0; i < max; i++){
			//从1~8列放置皇后
			array[n] = i;
			if(checkPos(n)){//不冲突
				//放置n + 1个皇后
				tryPutNextLine(n + 1);
			}
			//冲突则i++
		}
	}

	public static void printArr(){
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i] + "  ");
		}
		System.out.println();
		count++;
	}
}
