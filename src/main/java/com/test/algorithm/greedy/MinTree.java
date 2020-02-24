package com.test.algorithm.greedy;

import java.util.Arrays;

/**
 * 最小生成树
 */
public class MinTree {
	/**
	 * 生成图
	 * @param graph 图对象
	 * @param verxs 顶点个数
	 * @param data	顶点的值
	 * @param weight 图的邻接矩阵
	 */
	public void createGraph(PrimGraph graph, int verxs, char[] data, int[][] weight){
		int i, j;
		for(i = 0; i < verxs; i++){
			graph.data[i] = data[i];
			for(j = 0; j < verxs; j++){
				graph.weight[i][j] = weight[i][j];
			}
		}
	}

	public void showGraph(PrimGraph graph){
		for(int[] link : graph.weight){
			System.out.println(Arrays.toString(link));
		}
	}

	/**
	 * 图
	 * @param graph 图
	 * @param v 从图的第几个顶点开始生成
	 */
	public void prim(PrimGraph graph, int v){
		int[] visited = new int[graph.verx];
//		for(int i = 0; i < graph.verx; i++){
//			System.out.println(visited[i]);
//		}
		//两个顶点的下标
		int h1 = -1;//访问过的节点到未访问过的节点最小的距离的边,  的已访问过的节点的index
		int h2 = -1;//访问过的节点到未访问过的节点最小的距离的边,  的未访问过的节点的index
		int minWeight = 10000;
		int verx = graph.verx;
		visited[v] = 1;
		for(int k = 1; k < verx; k++){//算法结束后应该有verx - 1个边
			//确定每次子图和哪个节点距离最近
			for(int i = 0; i < verx; i++){//遍历已访问过的节点
				for(int j = 0; j < verx; j++){//遍历未访问过的节点
					if(visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight){
						minWeight = graph.weight[i][j];
						h1 = i;
						h2 = j;
					}
				}
			}
			System.out.println("边=" + graph.data[h1] + " " + graph.data[h2] + " 权值" + minWeight);
			visited[h2] = 1;
			minWeight = 10000;
		}
	}
}
