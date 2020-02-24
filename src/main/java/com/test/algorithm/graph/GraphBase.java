package com.test.algorithm.graph;

import java.util.*;

public class GraphBase {
	private ArrayList<String> vertexList;//顶点数组
	private int[][] edges;//顶点矩阵
	private int numOfEdges;//边数

	private boolean[] isVisited;

	/**
	 * @param n 顶点个数
	 */
	public GraphBase(int n) {
		edges = new int[n][n];
		vertexList = new ArrayList<>(n);
		numOfEdges = 0;
		isVisited = new boolean[n];
	}

	/**
	 * 添加顶点
	 * @param vertex 顶点名字
	 */
	public void insertVertex(String vertex){
		vertexList.add(vertex);
	}

	/**
	 * 添加边
	 * @param v1 顶点1的下标
	 * @param v2 顶点2的下标
	 * @param weight 权值
	 */
	public void insertEdge(int v1, int v2, int weight){
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		numOfEdges++;
	}

	public int getNumOfVertexes(){
		return vertexList.size();
	}

	public int getNumOfEdges(){
		return numOfEdges;
	}

	//返回某节点的值(根据下标)
	public String getValueByIndex(int index){
		return vertexList.get(index);
	}

	/**
	 * 返回v1, v2的权值
	 */
	public int getWeight(int v1, int v2){
		return edges[v1][v2];
	}

	//显示矩阵
	public void printGraph(){
		for(int[] link : edges){
			System.out.println(Arrays.toString(link));
		}
	}

	/**
	 * 得到第index个节点的邻接节点的下标
	 * 存在返回下标,不存在返回
	 */
	public int getFirstNeighbor(int index){
		for(int j = 0; j < vertexList.size(); j++){
			if(edges[index][j] > 0){//存在
				return j;
			}
		}
		return -1;
	}

	/**
	 * 根据前一个邻接节点的下标来获取下一个邻接节点
	 */
	public int getNextNeighbor(int v1, int v2){
		for(int j = v2 + 1; j < vertexList.size(); j++){
			if(edges[v1][j] > 0){
				return j;
			}
		}
		return -1;
	}

	private void depthFirstSearch(int index){//以index为v进行遍历
		//访问该节点
		System.out.println(getValueByIndex(index));
		isVisited[index] = true;
		int w = getFirstNeighbor(index);
		while(w != -1){
			if(!isVisited[w]){
				depthFirstSearch(w);//isVisited,
			}
			w = getNextNeighbor(index, w);
		}
	}

	public void depthFirstSearch(){
		for(int i = 0; i < isVisited.length; i++){
			isVisited[i] = false;
		}
		for(int i = 0; i < getNumOfVertexes(); i++){
			if(!isVisited[i]){
				depthFirstSearch(i);
			}
		}
	}

	/**
	 * 对某个节点进行广度优先遍历
	 * @param i
	 */
	public void broadFirstSearch(int i){
		int u;//队列头节点对应下标
		int w;//邻接节点w
		//邻接队列 节点访问顺序
		LinkedList<Integer> queue = new LinkedList<>();
		System.out.println(getValueByIndex(i));
		isVisited[i] = true;
		queue.addLast(i);
		while(!queue.isEmpty()){
			u = queue.removeFirst();
			w = getFirstNeighbor(u);
			while(w != -1){
				if(!isVisited[w]){//是否访问过
					System.out.println(getValueByIndex(w));
					isVisited[w] = true;
					queue.addLast(w);
				}
				w = getNextNeighbor(u, w);//以u为行找下一个w
			}
		}
	}

	public void broadFirstSearch(){
		for(int i = 0; i < isVisited.length; i++){
			isVisited[i] = false;
		}
		for(int i = 0; i < getNumOfVertexes(); i++){
			if(!isVisited[i]){
				broadFirstSearch(i);
			}
		}
	}

	public void bfs(String ch){
		int startIndex = -1;
		for(int i = 0; i < isVisited.length; i++){
			isVisited[i] = false;
			if(vertexList.get(i).equals(ch)){
				startIndex = i;
			}
		}
		if(startIndex == -1){
			System.out.println("错误");
			return;
		}

		LinkedList<Integer> queue = new LinkedList<>();
		queue.addLast(startIndex);
		HashSet<Integer> set = new HashSet<>();
		set.add(startIndex);
		while(queue.size() > 0){
			int popIndex = queue.pop();
			LinkedList<Integer> linkedNodes = new LinkedList<>();
			for(int i = 0; i < edges[popIndex].length; i++){
				if(edges[popIndex][i] == 1){
					linkedNodes.add(i);
				}
			}
			for(int i : linkedNodes){
				if(!set.contains(i)){
					queue.addLast(i);
					set.add(i);
				}
			}
			System.out.println(vertexList.get(popIndex));
		}
	}




	public int[] getSortestTreeByBfs(String ch){
		int startIndex = -1;
		int len = isVisited.length;
		for(int i = 0; i < isVisited.length; i++){
			isVisited[i] = false;
			if(vertexList.get(i).equals(ch)){
				startIndex = i;
			}
		}
		if(startIndex == -1){
			System.out.println("错误");
			return null;
		}

		LinkedList<Integer> queue = new LinkedList<>();
		queue.addLast(startIndex);
		HashSet<Integer> set = new HashSet<>();
		set.add(startIndex);

		int[] parent = new int[len];
		Arrays.fill(parent, -1);
		while(queue.size() > 0){
			int popIndex = queue.pop();
			LinkedList<Integer> linkedNodes = new LinkedList<>();
			for(int i = 0; i < edges[popIndex].length; i++){
				if(edges[popIndex][i] == 1){
					linkedNodes.add(i);
				}
			}
			for(int i : linkedNodes){
				if(!set.contains(i)){
					queue.addLast(i);
					set.add(i);
					parent[i] = popIndex;
				}
			}
			System.out.println(vertexList.get(popIndex));
		}
		return parent;
	}

	public Result getSortestTreeByBfsWithWeight(String ch){
		int startIndex = -1;
		int len = isVisited.length;
		for(int i = 0; i < isVisited.length; i++){
			isVisited[i] = false;
			if(vertexList.get(i).equals(ch)){
				startIndex = i;
			}
		}
		if(startIndex == -1){
			System.out.println("错误");
			return null;
		}

		PriorityQueue<PQNode> queue = new PriorityQueue<>();
		queue.add(new PQNode(0, startIndex));
		HashSet<Integer> set = new HashSet<>();

		int[] parent = new int[len];
		int[] distance = new int[len];
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(parent, -1);
		while(queue.size() > 0){
			PQNode pqNode= queue.poll();
			int dist = pqNode.dis;
			int popIndex = pqNode.index;
			set.add(startIndex);
			LinkedList<Integer> linkedNodes = new LinkedList<>();
			for(int i = 0; i < edges[pqNode.index].length; i++){
				if(edges[pqNode.index][i] > 0){
					linkedNodes.add(i);
				}
			}
			for(int i : linkedNodes){
				if(!set.contains(i)){
					if(dist + edges[pqNode.index][i] < distance[i]){
						distance[i] = dist + edges[pqNode.index][i];
						queue.add(new PQNode(distance[i], i));
						parent[i] = popIndex;
					}
//					set.add(i);
				}
			}
//			System.out.println(vertexList.get(popIndex));
		}

		//输出结果
		for(int i = 0; i < parent.length; i++){
			String str = vertexList.get(i);
			if(str.equals(ch)){
				System.out.printf("'%s':%s\t", str, ch);
			}else{
				System.out.printf("'%s':%s\t", str, vertexList.get(parent[i]));
			}
		}
		System.out.println();
		for(int i = 0; i < distance.length; i++){
			String str = vertexList.get(i);
			if(str.equals(ch)){
				System.out.printf("'%s':%d\t", ch, 0);
			}else{
				System.out.printf("'%s':%d\t", str, distance[i]);
			}
		}
		System.out.println();
		return new Result(parent, distance);
	}

	public static class PQNode implements Comparable<PQNode>{
		int dis;
		int index;
		public PQNode(int dis,int index) {
			this.dis = dis;
			this.index = index;
		}

		@Override
		public int compareTo(PQNode o) {
			return this.index - o.index;
		}
	}
	public static class Result{
		int[] parent;
		int[] distance;

		public Result(int[] parent,int[] distance) {
			this.parent = parent;
			this.distance = distance;
		}
	}
}
