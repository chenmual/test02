package com.test.algorithm.dp;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * ---------------------
 * 作者：麦田里的哈士奇
 * 来源：CSDN
 * 原文：https://blog.csdn.net/qq_26410101/article/details/80569811
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 */
public class HouseRobber {
	public static void main(String[] args){
	    int[] arr = new int[]{1, 2, 4, 1, 7, 8, 3};
	    int ret = getMaxMoneyWithHouseRobber(arr, 6);
		System.out.println(ret);
		System.out.println(getMaxMoneyWithHouseRobber(arr));
	}

	/**
	 * 使用递归算法会产生冗余
	 * @param arr
	 * @param index
	 * @return
	 */
	public static int getMaxMoneyWithHouseRobber(int[] arr, int index){
		if(index == 0){
			return arr[0];
		}else if(index == 1){
			return Math.max(arr[0], arr[1]);
		}else{
			int ret1 = getMaxMoneyWithHouseRobber(arr, index - 2) + arr[index];
			int ret2 = getMaxMoneyWithHouseRobber(arr, index - 1);
			return Math.max(ret1, ret2);
		}
	}

	public static int getMaxMoneyWithHouseRobber(int[] arr){
		int len = arr.length;
		int[] opt = new int[len];
		opt[0] = arr[0];
		opt[1] = Math.max(arr[0], arr[1]);
		for(int i = 2; i < len; i++){
			int ret1 = opt[i - 2] + arr[i];
			int ret2 = opt[i - 1];
			opt[i] = Math.max(ret1, ret2);
		}
		return opt[len - 1];
	}
}
