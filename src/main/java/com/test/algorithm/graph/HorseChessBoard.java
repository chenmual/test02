package com.test.algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class HorseChessBoard {
	private static int X;//棋盘列数
	private static int Y;//棋盘行数
	public static boolean visited[];
	public static boolean finished;

	public static void main(String[] args){
		X = 8;
		Y = 8;
		int row = 1;
		int column = 1;
		int[][] chessBoard = new int[X][Y];
		visited = new boolean[X * Y];
//		traversalChessboard(chessBoard, row - 1, column - 1, 1);
		traversalChessboardWithGreedy(chessBoard, row - 1, column - 1, 1);
		System.out.println("------");
		for(int i = 0; i < chessBoard.length; i++){
			System.out.println(Arrays.toString(chessBoard[i]));
		}
	}

	/**
	 * 返回可走的下一步列表
	 * @param curPoint
	 * @return
	 */
	public static ArrayList<Point> next(Point curPoint){
		ArrayList<Point> points = new ArrayList<>(8);
		Point p1 = new Point();
		if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0){//10点位置可走
			points.add(new Point(p1));
		}
		if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0){//11点位置
			points.add(new Point(p1));
		}
		if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0){//1点位置
			points.add(new Point(p1));
		}
		if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0){//2点位置
			points.add(new Point(p1));
		}
		if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y){//4点位置
			points.add(new Point(p1));
		}
		if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y){//5点位置
			points.add(new Point(p1));
		}
		if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y){//7点位置
			points.add(new Point(p1));
		}
		if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y){//8点位置
			points.add(new Point(p1));
		}
		return points;
	}

	/**
	 *
	 * @param chessBoard 棋盘
	 * @param row 行
	 * @param column 列
	 * @param step 步数,从1开始
	 */
	public static void traversalChessboard(int[][] chessBoard, int row, int column, int step){
		chessBoard[row][column] = step;
		visited[row * X + column] = true;//标记该位置已访问
		ArrayList<Point> nextPoints = next(new Point(column, row));
		while(!nextPoints.isEmpty()){
			Point p = nextPoints.remove(0);
			if(!visited[p.y * X + p.x]){
				traversalChessboard(chessBoard, p.y, p.x, step + 1);
			}
		}
		if((step < X * Y) && !finished){//回溯 未完成
			chessBoard[row][column] = 0;
			visited[row * X + column] = false;
		}else{//回溯 完成
			finished = true;
		}
	}

	/**
	 *
	 * @param chessBoard 棋盘
	 * @param row 行
	 * @param column 列
	 * @param step 步数,从1开始
	 */
	public static void traversalChessboardWithGreedy(int[][] chessBoard, int row, int column, int step){
		chessBoard[row][column] = step;
		visited[row * X + column] = true;//标记该位置已访问
		ArrayList<Point> nextPoints = next(new Point(column, row));
		sort(nextPoints);
		while(!nextPoints.isEmpty()){
			Point p = nextPoints.remove(0);
			if(!visited[p.y * X + p.x]){
				traversalChessboardWithGreedy(chessBoard, p.y, p.x, step + 1);
			}
		}
		if((step < X * Y) && !finished){//回溯 未完成
			chessBoard[row][column] = 0;
			visited[row * X + column] = false;
		}else{//回溯 完成
			finished = true;
		}
	}

	public static class Point{
		int x;
		int y;

		public Point() {
		}

		public Point(Point point) {
			this.x = point.x;
			this.y = point.y;
		}

		public Point(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void sort(ArrayList<Point> ps){
		ps.sort(new Comparator<Point>() {
			@Override
			public int compare(Point o1,Point o2) {
				int count1 = next(o1).size();
				int count2 = next(o2).size();
				return count1 - count2;
			}
		});
	}
}
