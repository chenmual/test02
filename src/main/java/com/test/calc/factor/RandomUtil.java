package com.test.calc.factor;

import java.util.Random;

public class RandomUtil{
	public static Random s_rnd = null;
	static{
		s_rnd = new Random();
	}

	public static void main(String[] args){
		for(int i = 0; i < 10000; i++){
			int k = getRandom(-3, 10);
			if(k > 9 || k < -2){
				System.out.println(k);
			}
		}
	}

	public static int getRandom(int min, int max){
		return s_rnd.nextInt(max - min + 1) + min;
	}
}
