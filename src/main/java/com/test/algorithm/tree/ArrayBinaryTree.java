package com.test.algorithm.tree;

public class ArrayBinaryTree {
	public static void main(String[] args){
//	    int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
		int[] array = new int[]{1, 3, 6, 8, 10, 14};
		ArrayBinaryTree tree = new ArrayBinaryTree(array);
//		tree.preOrder(0);
		tree.infixOrder(0);
	}

	private int[] arr;
	public ArrayBinaryTree(int[] arr){
		this.arr = arr;
	}

	/**
	 * @param index 数组下标
	 */
	public void preOrder(int index){
		int len = arr.length;
		//如果数组为空
		if(len <= 0){
			System.out.println("数组为空,不能按照二叉树前序遍历");
		}
		System.out.println(arr[index]);
		//向左递归遍历
		int next = index * 2 + 1;
		if(next < len){
			preOrder(next);
		}
		next = index * 2 + 2;
		//向右递归遍历
		if(next < len){
			preOrder(next);
		}
	}

	public void infixOrder(int index){
		int len = arr.length;
		//如果数组为空
		if(len <= 0){
			System.out.println("数组为空,不能按照二叉树前序遍历");
		}
		//向左递归遍历
		int next = index * 2 + 1;
		if(next < len){
			infixOrder(next);
		}
		System.out.println(arr[index]);
		next = index * 2 + 2;
		//向右递归遍历
		if(next < len){
			infixOrder(next);
		}
	}
	public void postOrder(int index){
		int len = arr.length;
		//如果数组为空
		if(len <= 0){
			System.out.println("数组为空,不能按照二叉树前序遍历");
		}
		//向左递归遍历
		int next = index * 2 + 1;
		if(next < len){
			postOrder(next);
		}
		next = index * 2 + 2;
		//向右递归遍历
		if(next < len){
			postOrder(next);
		}
		System.out.println(arr[index]);
	}
}
