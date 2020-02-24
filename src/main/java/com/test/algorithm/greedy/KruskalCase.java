package com.test.algorithm.greedy;

import java.util.Arrays;

public class KruskalCase {
	private int edgeNum;
	private char[] vertexes;
	private int[][] matrix;

	private static final int INF = Integer.MAX_VALUE;//表示不连通

	private int vlen;

	public KruskalCase(char[] vertexs,int[][] matrix) {
		this.vlen = vertexs.length;
		this.vertexes = Arrays.copyOf(vertexs, this.vlen);
		this.matrix = matrix;
		this.matrix = new int[vlen][vlen];
		for(int i = 0; i < vlen; i++){
			for(int j = i + 1; j < vlen; j++){
				this.matrix[i][j] = matrix[i][j];
				if(this.matrix[i][j] != INF){
					edgeNum++;
				}
			}
		}
	}

	public void printMatrix(){
		System.out.println("------");
		for(int i = 0; i < vlen; i++){
			for(int j = 0; j < vlen; j++){
				System.out.print((this.matrix[i][j] == INF? "nil" : this.matrix[i][j]) + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args){
		char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
	    int matrix[][] = {
				{0, 12, INF, INF, INF, 16, 14},
				{12, 0, 10, INF, INF, 7, INF},
				{INF, 10, 0, 3, 5, 6, INF},
				{INF, INF, 3, 0, 4, INF, INF},
				{INF, INF, 5, 4, 0, 2, 8},
				{16, 7, 6, INF, 2, 0, 9},
				{14, INF, INF, INF, 8, 9, 0}
		};
	    KruskalCase kruskalCase = new KruskalCase(vertex, matrix);

	    kruskalCase.printMatrix();
		kruskalCase.kruskal();
	}

	public static class EData{
		char start;//边的一个点
		char end;//边的另外一个点
		int weight;

		public EData(char start,char end,int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "EData{" +
					"start=" + start +
					", end=" + end +
					", weight=" + weight +
					'}';
		}
	}

	private static void sortEdge(EData[] edges){
		int len = edges.length - 1;
		for(int i = 0; i < len; i++){
			for(int j = 0; j < len; j++){
				if(edges[j].weight > edges[j + 1].weight){
					EData tmp = edges[j];
					edges[j] = edges[j + 1];
					edges[j + 1] = tmp;
				}
			}
		}
	}

	//返回两顶点的边值,找不到返回-1
	private int getPosition(char ch){
		for(int i = 0; i < vertexes.length; i++){
			if(vertexes[i] == ch){
				return i;
			}
		}
		return -1;
	}

	/**
	 * 获取图中的边放到EData
	 * @return
	 */
	private EData[] getEdges(){
		int index = 0;
		EData[] edges = new EData[edgeNum];
		for(int i = 0; i < vlen; i++){
			for(int j = i + 1; j < vlen; j++){
				if(matrix[i][j] != INF){
					edges[index++] = new EData(vertexes[i], vertexes[j], matrix[i][j]);
				}
			}
		}
		return edges;
	}

	/**
	 * 获取下标为i的顶点的终点index
	 * @param ends 记录了顶点对应的终点是哪个,是在遍历过程中逐步形成的,初始均为0
	 * @param i 顶点对应的下标
	 * @return 下标为i的顶点对应的终点的下标
	 */
	private int getEnd(int[] ends, int i){
		while(ends[i] != 0){
			i = ends[i];
		}
		return i;
	}

	private void kruskal(){
		int index = 0;//最后结果数组的索引
		int[] ends = new int[edgeNum];//保存已有生成树中的每个顶点的中点

		//创建结果数组
		EData[] rets = new EData[edgeNum];
		//获取图中所有的节点,一共有edgeNum个边
		EData[] edges = getEdges();
		System.out.println("图的边的集合" + Arrays.toString(edges) + "共" + edgeNum + "个边");

		System.out.println(Arrays.toString(edges));
		sortEdge(edges);
		System.out.println(Arrays.toString(edges));

		for(int i = 0; i < edgeNum; i++){//遍历此边
			int p1 = getPosition(edges[i].start);
			int p2 = getPosition(edges[i].end);

			int m = getEnd(ends, p1);
			int n = getEnd(ends, p2);
			if(m != n){//没有构成回路
				ends[m] = n;//下标m的顶点的终点的下标设置为n
				rets[index++] = edges[i];//将下标为i的边加入到rets数组
			}
		}

		System.out.println(Arrays.toString(rets));
	}
}