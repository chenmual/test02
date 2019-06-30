package com.test.algorithm.recursion;

/**
 * 生成一个int[][] 代表x × y的地图
 * 计算从某点P 到终点的路线
 */
public class Maze01 {
	public static void main(String[] args){
		int[][] map = makeMap();
		boolean ret = findWay(map, 1, 1);
		if(ret){
			System.out.println("找到");
		}else{
			System.out.println("未找到");
		}
		showMap(map);
	}

	/**
	 * 8行7列的地图
	 * @return
	 */
	public static int[][] makeMap(){
		int[][] map = new int[8][7];
		//生成障碍
		for(int i = 0; i < 7; i++){
			map[0][i] = 1;
			map[7][i] = 1;
		}
		for(int i = 0; i < 8; i++){
			map[i][0] = 1;
			map[i][6] = 1;
		}
		map[3][1] = 1;
		map[3][2] = 1;
		showMap(map);
		return map;
	}

	public static void showMap(int[][] map){
		//遍历地图
		for(int i = 0; i < 8; i++){
			for(int j = 0;j < 7; j++){
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * 策略:先下,右,上,左,走不通 回溯
	 * @param map 地图, int[][] 0为可走, 1为墙, 2为已走, 3已走并且走不通
	 * @param next_x 出发点x
	 * @param next_y 出发点y
	 * @return
	 */
	public static boolean findWay(int[][] map, int next_x, int next_y){
		if(map[6][5] == 2){
			return true;
		}else{
			if(map[next_x][next_y] == 0){
				map[next_x][next_y] = 2;
				if(findWay(map, next_x + 1, next_y)){//向下走
					return true;
				}else if(findWay(map, next_x, next_y + 1)){//向右走
					return true;
				}else if(findWay(map, next_x - 1, next_y)){//向上走
					return true;
				}else if(findWay(map, next_x, next_y - 1)){//向左走
					return true;
				}else{
					//走不通
					map[next_x][next_y] = 3;
					return false;
				}
			}else{//map[next_x][next_y] = 1, 2, 3
				return false;
			}
		}
	}
}
