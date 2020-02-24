package com.test.algorithm.dp;

/**
 * 70. Climbing Stairs (Easy)
 * 爬楼梯
 * 题目描述：有 N 阶楼梯，每次可以上一阶或者两阶，求有多少种上楼梯的方法。
 */
public class ClimbingStairs {
	public static void main(String[] args){
		System.out.println(climbStairs2(9));
	}
	public static int climbStairs(int stairs){
		if(stairs <= 2){
			return stairs;
		}
		int[] pd = new int[stairs];
		pd[0] = 1;
		pd[1] = 2;
		for(int i = 2; i < stairs; i++){
			pd[i] = pd[i - 1] + pd[i - 2];
		}
		return pd[stairs - 1];
	}
	public static int climbStairs2(int n) {
		if (n <= 2) {
			return n;
		}
		int pre2 = 1, pre1 = 2;
		for (int i = 2; i < n; i++) {
			int cur = pre1 + pre2;
			pre2 = pre1;
			pre1 = cur;
		}
		return pre1;
	}
}
