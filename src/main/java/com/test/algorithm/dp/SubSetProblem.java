package com.test.algorithm.dp;

public class SubSetProblem {
	public static void main(String[] args){
	    int[] arr = new int[]{3, 34, 4, 12, 5, 2};
		System.out.println(hasSubSetKeyByRecursion(arr, arr.length - 1, 9));

		System.out.println(hasSubSetKeyByDPArray(arr, 9));
		System.out.println(hasSubSetKeyByDPArray(arr, 10));
		System.out.println(hasSubSetKeyByDPArray(arr, 11));
		System.out.println(hasSubSetKeyByDPArray(arr, 12));
		System.out.println(hasSubSetKeyByDPArray(arr, 13));
	}

	public static boolean hasSubSetKeyByRecursion(int[] arr, int index, int sum){
		if(sum == 0){
			return true;
		}else if(index == 0){
			return arr[0] == sum;
		}else if(arr[index] > sum){
			return hasSubSetKeyByRecursion(arr, index - 1, sum);
		}else{
			boolean ret1 = hasSubSetKeyByRecursion(arr, index - 1, sum - arr[index]);
			boolean ret2 = hasSubSetKeyByRecursion(arr, index - 1, sum);
			return ret1 || ret2;
		}
	}

	public static boolean hasSubSetKeyByDPArray(int[] arr, int sum){
		int rowCount = arr.length;
		int colCount = sum + 1;
		boolean[][] subset = new boolean[rowCount][colCount];
		for(int i = 0; i < rowCount; i++){
			subset[i][0] = true;
		}
		for(int i = 0; i < colCount; i++){
			subset[0][i] = false;
		}
		subset[0][arr[0]] = true;
		for(int i = 1; i < rowCount; i++){
			for(int s = 1; s < colCount; s++){
				if(arr[i] > s){
					subset[i][s] = subset[i - 1][s];
				}else{
					boolean ret1 = subset[i - 1][s - arr[i]];
					boolean ret2 = subset[i - 1][s];
					subset[i][s] = ret1 || ret2;
				}
			}
		}
		return subset[rowCount - 1][colCount - 1];
	}
}
