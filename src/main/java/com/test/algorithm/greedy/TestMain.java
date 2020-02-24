package com.test.algorithm.greedy;

public class TestMain {
	public static void main(String[] args){
		char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
//		char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};

		int len = data.length;

		int[][] weight = new int[][]{
				{10000, 5, 7, 10000, 10000, 10000, 2},
				{5, 10000, 10000, 9, 10000, 10000, 3},
				{7, 10000, 10000, 10000, 8, 10000, 10000},
				{10000, 9, 10000, 10000, 10000, 4, 10000},
				{10000, 10000, 8, 10000, 10000, 5, 4},
				{10000, 10000, 10000, 4, 5, 10000, 6},
				{2, 3, 10000, 10000, 4, 6, 10000},
		};


		weight = new int[][]{
				//A		B	    C	   D	  E		 F		G
				{10000, 12, 10000, 10000, 10000, 16, 14},
				{12, 10000, 10, 10000, 10000, 7, 10000},
				{10000, 10, 10000, 3, 5, 6, 10000},
				{10000, 10000, 3, 10000, 4, 10000, 10000},
				{10000, 10000, 5, 4, 10000, 2, 8},
				{16, 7, 6, 10000, 2, 10000, 9},
				{14, 10000, 10000, 10000, 8, 9, 10000}
		};

		PrimGraph primGraph = new PrimGraph(len);

		MinTree minTree = new MinTree();
	    minTree.createGraph(primGraph, len, data, weight);
		minTree.showGraph(primGraph);
		minTree.prim(primGraph, 0);
	}
}
