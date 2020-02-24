package com.test.algorithm.greedy;

public class PrimGraph {
	/**
	 * 创建图
	 */
	int verx;//节点个数
	char[] data;//节点数据
	int[][] weight;//邻接矩阵
	public PrimGraph(int verxs){
		this.verx = verxs;
		data = new char[verxs];
		weight = new int[verxs][verxs];
	}
}

