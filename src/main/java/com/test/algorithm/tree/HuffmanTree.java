package com.test.algorithm.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree<T>{

	HFMNode root = null;

	public void printTreeByPreOrder(){
		if(root != null){
			root.preOrder();
		}else{
			System.out.println("空树");
		}
	}

	public HuffmanTree(List<HFMNode<T>> list){
		while(list.size() > 1){
			Collections.sort(list);
			HFMNode left = list.get(0);
			HFMNode right = list.get(1);
			HFMNode parent = new HFMNode<T>(left.value + right.value);
			parent.left = left;
			parent.right = right;

			list.remove(left);
			list.remove(right);
			list.add(parent);
		}
		this.root = list.get(0);
	}

	public HuffmanTree(int[] arr){
		List<HFMNode> list = new ArrayList<>();
		int len = arr.length;
		for(int i = 0; i < len; i++){
			list.add(new HFMNode(arr[i]));
		}
		while(list.size() > 1){
			Collections.sort(list);
			HFMNode left = list.get(0);
			HFMNode right = list.get(1);
			HFMNode parent = new HFMNode<T>(left.value + right.value);
			parent.left = left;
			parent.right = right;

			list.remove(left);
			list.remove(right);
			list.add(parent);
		}
		this.root = list.get(0);
	}

	public static void test(){
		int[] arr = {13, 7 , 8, 3, 29, 6, 1};

		HuffmanTree tree = new HuffmanTree(arr);
		tree.printTreeByPreOrder();
	}
}

