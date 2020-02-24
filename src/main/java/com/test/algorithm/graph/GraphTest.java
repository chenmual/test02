package com.test.algorithm.graph;

import java.util.Arrays;

public class GraphTest {
	public static void main(String[] args){
//		test01();
//		test02();
		test03();
	}
	public static void test03(){
		String[] vv = {"A", "B", "C", "D", "E", "F"};
		int n = vv.length;
		//创建图对象
		GraphBase graph = new GraphBase(n);
		//循环添加顶点
		for(String v : vv){
			graph.insertVertex(v);
		}
		graph.insertEdge(0, 1, 5);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 2, 2);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(2, 3, 4);
		graph.insertEdge(2, 4, 8);
		graph.insertEdge(3, 4, 3);
		graph.insertEdge(3, 5, 6);

		graph.printGraph();
//		graph.broadFirstSearch();
		graph.bfs("E");
		System.out.println("------");
//		graph.dfs("E");
		GraphBase.Result result = graph.getSortestTreeByBfsWithWeight("A");
//		graph.depthFirstSearch();
		System.out.println(Arrays.toString(result.parent));
		System.out.println(Arrays.toString(result.distance));
	}
	public static void test02(){
		String[] vv = {"A", "B", "C", "D", "E", "F"};
		int n = vv.length;
		//创建图对象
		GraphBase graph = new GraphBase(n);
		//循环添加顶点
		for(String v : vv){
			graph.insertVertex(v);
		}
		graph.insertEdge(0, 1, 1);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 2, 1);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(2, 3, 1);
		graph.insertEdge(2, 4, 1);
		graph.insertEdge(3, 4, 1);
		graph.insertEdge(3, 5, 1);

		graph.printGraph();
//		graph.broadFirstSearch();
		graph.bfs("E");
		System.out.println("------");
//		graph.dfs("E");
		int[] parent = graph.getSortestTreeByBfs("E");
//		graph.depthFirstSearch();
		System.out.println(Arrays.toString(parent));
	}

	public static void test01(){
//		String[] vv = {"A", "B", "C", "D", "E"};
		String[] vv = {"1", "2", "3", "4", "5", "6", "7", "8"};
		int n = vv.length;
		//创建图对象
		GraphBase graph = new GraphBase(n);
		//循环添加顶点
		for(String v : vv){
			graph.insertVertex(v);
		}
		//添加边
//		graph.insertEdge(0, 1, 1);
//		graph.insertEdge(0, 2, 1);
//		graph.insertEdge(1, 2, 1);
//		graph.insertEdge(1, 3, 1);
//		graph.insertEdge(1, 4, 1);

		graph.insertEdge(0, 1, 1);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(1, 4, 1);
		graph.insertEdge(3, 7, 1);
		graph.insertEdge(4, 7, 1);
		graph.insertEdge(2, 5, 1);
		graph.insertEdge(2, 6, 1);
		graph.insertEdge(5, 6, 1);

		graph.printGraph();
//		graph.depthFirstSearch();
//		System.out.println("-----");
		graph.broadFirstSearch();
	}
}
