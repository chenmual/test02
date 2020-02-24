package com.test.algorithm.dac;

public class HanoiTower {
	public static void main(String[] args){
		hanoiTower(10, 'A', 'B', 'C');
	}

	public static void hanoiTower(int num, char a, char b, char c){
		//如果只有一个盘子
		if(num == 1){
			System.out.println("第1个盘子 从" + a + " -> " + c);
		}else{//n>=2的情况,看成上面的盘子 x (n - 1) + 最下面的盘子 x 1
			// 1.将最上面的n - 1个盘子 从A移动到b,移动中会使用到c
			hanoiTower(num - 1, a, c, b);
			// 2.把最下面的1个盘子 从a移动到c
			System.out.println("第" + num + "个盘子 从" + a + " -> " + c);
			// 3.把b塔的所有盘 从b->c
			hanoiTower(num - 1, b, a, c);
		}
	}
}
