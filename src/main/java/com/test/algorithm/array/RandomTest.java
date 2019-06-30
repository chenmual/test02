package com.test.algorithm.array;

import com.test.util.RandomUtil;

public class RandomTest {
	public static void main(String[] args){
//		sparseArrayTest();
//		QueueWithArray1.testDemo();
		QueueWithArray2.testDemo();
	}

	public static void sparseArrayTest(){
		/**
		 * 随机生成一个19行x19列的棋盘,并随机放入黑白棋子(0:空, 1:黑, 2:白)
		 */
		int[][] ret = makeRandom(19, 19, 3);
		printDoubleArr(ret);

		SparseArray sparseArray = SparseArray.makeSparseArray(ret, 0);
		int[][] ret2 = sparseArray.transformToArray();
		System.out.println("------");
		printDoubleArr(ret2);
		System.out.println(varifyDoubleArray(ret, ret2));
	}

	/**
	 * 验证两个arr[][]是否一样
	 */
	public static boolean varifyDoubleArray(int[][] ret1, int[][]ret2){
		boolean retBoolean = true;
		try {
			int len = ret1.length;
			for(int i = 0; i < len && retBoolean; i++){
				int len2 = ret1[i].length;
				for(int j = 0; j < len2; j++){
					if(ret1[i][j] != ret2[i][j]){
						retBoolean = false;
						break;
					}
				}
			}
		}catch(Exception e){
			retBoolean = false;
		}
		return retBoolean;
	}

	/**
	 * 生成一个随机0~len - 1填充的row行col列的数组
	 */
	public static int[][] makeRandom(int row, int col, int len){
		int[][] ret = new int[row][col];
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				ret[i][j] = RandomUtil.s_rnd.nextInt(len);
			}
		}
		return ret;
	}

	/**
	 * 把一个arr[][]输出
	 */
	public static void printDoubleArr(int[][] arr){
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[i].length; j++){
				System.out.print(String.format("%3s", arr[i][j]) + " ");
			}
			System.out.println();
		}
	}
}
