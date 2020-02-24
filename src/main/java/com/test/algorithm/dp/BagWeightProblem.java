package com.test.algorithm.dp;

/**
 * 背包问题
 * 将物品item放入背包
 * 物品具有重量weight
 * 物品具有价值value
 * 背包具有容量C
 * 求解若干物品最轻/重+价值最大/小的搭配
 */
public class BagWeightProblem {

	public static void main(String[] args){
		//物品数组的重量
		int[] weights = new int[]{1, 4, 3};
		//物品数组的价值
		int[] values = new int[]{1500, 3000, 2000};
		int capacity = 4;//背包容量
		getMaxValueByCapacity(weights, values, capacity);
	}
	public static void getMaxValueByCapacity(int[] weights,int[] values,int capacity){
		int n = values.length;
		int[][] v = new int[n + 1][capacity + 1];//(len + 1) x (len + 1)的表格
		int[][] path = new int[n + 1][capacity + 1];//(len + 1) x (len + 1)的表格 记录商品背包
		int rowCount = v.length;
		int colCount = v[0].length;
		for(int i = 0; i < rowCount; i++){
			v[i][0] = 0;//初始化第一列
		}
		for(int i = 0; i < colCount; i++){
			v[0][i] = 0;//初始化第一行
		}

		for(int i = 1; i < rowCount; i++){
			for(int j = 1; j < colCount; j++){
				if(weights[i - 1] > j){//新物品的容量>当前背包容量
					v[i][j] = v[i - 1][j];
				}else{//当前背包容量>=新物品的容量
					//上一个单元格的最大值v[i - 1][j]
					//剩余空间v[i - 1][j - weights[i - 1]]的最大值 + 当前商品价值values[i - 1]
					if((v[i][j] = v[i - 1][j]) < values[i - 1] + v[i - 1][j - weights[i - 1]]){
						v[i][j] = values[i - 1] + v[i - 1][j - weights[i - 1]];
						path[i][j] = 1;
					}
				}
			}
		}
		for(int i = 0; i < rowCount; i++){
			for(int j = 0; j < colCount; j++){
				System.out.print(v[i][j] + " ");
			}
			System.out.println();
		}
		{
			int i = rowCount - 1;
			int j = colCount - 1;
			while(i > 0 && j > 0){
				if(path[i][j] == 1){
					System.out.printf("第%d个商品放入背包\n", i);
					j -= weights[i - 1];
				}
				i--;
			}
		}
	}
}
