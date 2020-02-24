package com.test.algorithm.greedy;

import java.util.Arrays;

public class Floyd {

	public static final int N = 65535;
	public static void main(String[] args){
		char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		int length = data.length;
		int[][] matrix = new int[][]{
				{0, 5, 7, N, N, N, 2},
				{5, 0, N, 9, N, N, 3},
				{7, N, 0, N, 8, N, N},
				{N, 9, N, 0, N, 4, N},
				{N, N, 8, N, 0, 5, 4},
				{N, N, N, 4, 5, 0, 6},
				{2, 3, N, N, 4, 6, 0},
		};

		FloydGraph graph = new FloydGraph(length, data, matrix);
		graph.show();
		graph.floyd();
		graph.show();

	}

	public static class FloydGraph{
		private char[] vertex;
		private int[][] dis;// 保存从个个顶点出发到其他(每个)顶点的距离
		private int[][] pre;// 到达目标顶点的前驱,初始为自身

		private int arrLen = 0;

		public FloydGraph(int length, char[] vertex,int[][] matrix) {
			this.arrLen = length;
			this.vertex = vertex;
			this.dis = matrix;
			this.pre = new int[length][length];

			for(int i = 0; i < length; i++){
				Arrays.fill(pre[i], i);
			}
		}
		public void show(){
			char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
			System.out.print("  ");
			for(int i = 0; i < dis.length; i++){
				System.out.print(vertex[i] + " ");
			}
			System.out.println();
			for(int k = 0; k < dis.length; k++){
				System.out.print(vertex[k]);
				for(int i = 0; i < dis.length; i++){
					System.out.print(" " + pre[k][i]);
				}
				System.out.println();
			}
			System.out.println();
			System.out.print("   ");
			for(int i = 0; i < dis.length; i++){
				System.out.print(vertex[i] + "  ");
			}
			System.out.println();
			for(int k = 0; k < dis.length; k++){
				System.out.print(vertex[k]);
				for(int i = 0; i < dis.length; i++){
					if(dis[k][i] == 65535){
						System.out.print(" N");
					}else{
						System.out.printf(" %2d", dis[k][i]);
					}
				}
				System.out.println();
			}
		}

		public void floyd(){
			int len = 0;
			for(int k = 0; k < dis.length; k++){
				for(int i = 0; i < dis.length; i++){
					for(int j = 0; j < dis.length; j++){
						//从i出发,经过k,到达j
						len = dis[i][k] + dis[k][j];
						if(len < dis[i][j]){
							dis[i][j] = len;
							pre[i][j] = pre[k][j];
						}
					}
				}
			}
		}
	}
}
