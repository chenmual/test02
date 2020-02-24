package com.test.algorithm.tree;


public class HFMNode<T> implements Comparable<HFMNode>{
	int value;
	T data;
	HFMNode left;
	HFMNode right;

	public HFMNode(int value) {
		this.value = value;
		this.data = null;
	}
	public HFMNode(int value, T data){
		this.data = data;
		this.value = value;
	}

	@Override
	public String toString() {
		return "HFMNode{" +
				"value=" + value +
				", data=" + data +
				'}';
	}

	@Override
	public int compareTo(HFMNode o) {
		return this.value - o.value;
	}

	public void preOrder(){
		System.out.println(this);
		if(this.left != null){
			this.left.preOrder();
		}
		if(this.right != null){
			this.right.preOrder();
		}
	}
}
