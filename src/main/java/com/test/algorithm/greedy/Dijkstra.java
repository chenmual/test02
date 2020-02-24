package com.test.algorithm.greedy;


import java.util.Arrays;

public class Dijkstra {
	public static final int N = 65535;
	int[] alreadyArr;//默认0.i循环,使i变为1
	int[] dis;//默认65535.i循环+j循环,判断i与每个点的连通最距离ij小于dis[i]则dis[i]=ij距离
	int[] preVisited;//默认0.i循环+j循环,判断i与每个点的连通最距离ij小于dis[i]则preVisited[j]=i

//	char[] vertex;
//	int[][] matrix;

	/**
	 *
	 * @param len 顶点长度
	 * @param startIndex 出发顶点下标
	 */
	public Dijkstra(int len, int startIndex) {
		alreadyArr = new int[len];
		alreadyArr[startIndex] = 1;
		dis = new int[len];
		Arrays.fill(dis, 65535);
		dis[startIndex] = 0;
		preVisited = new int[len];

	}
	public static class DijkstraGraph{
		private char[] vertex;
		private int[][] matrix;
		private Dijkstra dijkstra;//已经访问的顶点的集合

		public DijkstraGraph(char[] vertex,int[][] matrix) {
			this.vertex = vertex;
			this.matrix = matrix;
		}
		public void printGraph(){
			for(int[] link : matrix){
				System.out.println(Arrays.toString(link));
			}
		}

		/**
		 * @param index 出发顶点的下标
		 */
		public void dsj(int index){
			int disLen = vertex.length;
			this.dijkstra = new Dijkstra(disLen, index);
			update(index);
			dijkstra.showArr();
			System.out.println("-------------出发顶点遍历结束");
			for(int i = 1; i < disLen; i++){
				index = dijkstra.updateAlreadyArr();
				System.out.println("-----------第" + index + "个节点(" + vertex[index] + ")作为update(index)");
				update(index);
				dijkstra.showArr();
			}

//			dijkstra.showArr();
		}

		private void update(int index){
			int len = 0;
			int arrLen = matrix[index].length;//出发顶点到index的距离 + index到j顶点的距离的和
			for(int j = 0; j < arrLen; j++){
				len = dijkstra.getDis(index) + matrix[index][j];
				if(!dijkstra.isVisitedByIndex(j) && len < dijkstra.dis[j]){
					dijkstra.updatePre(j, index);//更新j的前驱节点为index preVisited[j] = index
					dijkstra.updateDis(j, len);//更新出发顶点到j顶点的距离 dis[j] = len
				}
			}
		}

	}
	public static void main(String[] args){
		char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		int[][] matrix = new int[][]{
				{N, 5, 7, N, N, N, 2},
				{5, N, N, 9, N, N, 3},
				{7, N, N, N, 8, N, N},
				{N, 9, N, N, N, 4, N},
				{N, N, 8, N, N, 5, 4},
				{N, N, N, 4, 5, N, 6},
				{2, 3, N, N, 4, 6, N}
		};

		DijkstraGraph graph = new DijkstraGraph(vertex, matrix);

		graph.printGraph();
		graph.dsj(6);
	}

	//判断index是否访问过
	public boolean isVisitedByIndex(int index){
		return alreadyArr[index] == 1;
	}

	//更新从出发点到index顶点的距离
	public void updateDis(int index, int distance){
		dis[index] = distance;
	}

	//下标为pre的前驱节点变为了index
	public void updatePre(int pre, int index){
		preVisited[pre] = index;
	}

	//返回出发顶点到index的距离
	public int getDis(int index){
		return dis[index];
	}

	public int updateAlreadyArr(){
		int min = 65535;
		int index = 0;
		for(int i = 0; i < alreadyArr.length; i++){
			if(alreadyArr[i] == 0 && dis[i] < min){
				// alreadyArr[i]说明未作为访问顶点访问过
				// dis < min说明已是出发顶点的下层
				min = dis[i];
				index = i;
			}
		}
		alreadyArr[index] = 1;
		return index;
	}

	public void showArr(){
		System.out.println("alreadyArr:");
		System.out.println(Arrays.toString(alreadyArr));
		System.out.println("preArr:");
		System.out.println(Arrays.toString(preVisited));
		System.out.println("dis:");
		System.out.println(Arrays.toString(dis));
	}
}
