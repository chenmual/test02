package com.test.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtil {
	/**
	 * 判断一个正数的位数
	 * @param positive
	 * @return
	 */
	public static int getBit(int positive){
		int ret = -1;
		if(positive > 0){
			ret = 1;
		}
		while((positive = positive / 10) > 0){
			ret++;
		}
		return ret;
	}

	public static double bigMul(double a, double b){
		BigDecimal b1 = new BigDecimal(String.valueOf(a));
		BigDecimal b2 = new BigDecimal(String.valueOf(b));
		return b1.multiply(b2).doubleValue();
	}


	public static double bigDiv(double a, double b){
		BigDecimal b1 = new BigDecimal(String.valueOf(a));
		BigDecimal b2 = new BigDecimal(String.valueOf(b));
		return b1.divide(b2, 4, RoundingMode.HALF_UP).doubleValue();
	}
	public static double bigAdd(double a, double b){
		BigDecimal b1 = new BigDecimal(String.valueOf(a));
		BigDecimal b2 = new BigDecimal(String.valueOf(b));
		return b1.add(b2).doubleValue();
	}
	public static double bigDec(double a, double b){
		BigDecimal b1 = new BigDecimal(String.valueOf(a));
		BigDecimal b2 = new BigDecimal(String.valueOf(-b));
		return b1.add(b2).doubleValue();
	}

	public static boolean isNumber(int ch){
		if(ch <= '9' && ch >= '0'){
			return true;
		}
		return false;
	}

	/**
	 * 最大公约数
	 * @param a
	 * @param b
	 * @return
	 */
	public static int getMaxCommonDivisor(int a, int b) {
		int ret = 1;
		int len = a > b ? b : a;
		for (int i = 2; i <= len; i++) {
			if ((a % i == 0) && (b % i == 0)) {
					ret = i;
				}
			}
			return ret;
		}

	/**
	 * 最小公倍数
	 * @param a
	 * @param b
	 * @return
	 */
	public static int getMinCommonMultiple(int a, int b) {
		int ret = a * b;
		int max = ret;
		int start = a < b ? b : a;
		for (int i = start; i < max; i += start) {
			if ((i % a == 0) && (i % b == 0)) {
				ret = i;
				break;
			}
		}
		return ret;
	}

	/**
	 * 返回一个斐波那契数列
	 */
	public static int[] getFibonacciArray(int size){
		int[] fib = new int[size];
		fib[0] = 1;
		fib[1] = 1;
		for(int i = 2; i < size; i++){
			fib[i] = fib[i - 1] + fib[i - 2];
		}
		return fib;
	}

	public static int getFibonacciValue(int n){
		if(n == 0 || n  == 1){
			return 1;
		}
		return getFibonacciValue(n - 1) + getFibonacciValue(n - 2);
	}


	public static int getFibonacciMinIndexELKey(int key){
		return getFibonacciMinIndexELKey(key, null);
	}

	/**
	 * 找到第一个大于等于key的斐波那契数列的元素的下标 (从0开始)
	 * @param key
	 * @param lastNode
	 * @return
	 */
	public static int getFibonacciMinIndexELKey(int key,DNode lastNode){
		if(null == lastNode){
			if(key <= 1){
				return 1;
			}
			lastNode = new DNode();
			lastNode.value = 1;
			lastNode.key = 0;
			lastNode.pre = null;

			lastNode.next = new DNode();
			lastNode.next.value = 1;
			lastNode.next.key = lastNode.key + 1;
			lastNode.next.pre = lastNode;
			lastNode = lastNode.next;

		}
		lastNode.next = new DNode();
		lastNode.next.value = lastNode.value + lastNode.pre.value;
		lastNode.next.key = lastNode.key + 1;
		lastNode.next.pre = lastNode;
		lastNode = lastNode.next;
		if(lastNode.value >= key){//找到了
			return lastNode.key;
		}else{
			return getFibonacciMinIndexELKey(key, lastNode);
		}
	}

	public static class DNode{
		int key = 0;
		int value = 0;
		DNode next = null;
		DNode pre = null;
	}

	public static long floatPointMoveRight(double a) {
		double temp = a;
		long tempInt = Math.round(temp);
		long max = 21l;
		while (temp - (double)tempInt != 0f) {
			temp = temp * 10l;
			tempInt = Math.round(temp);
			max--;
			if(max < 0){
				System.out.println("error");
				break;
			}
		}
		return tempInt;
	}

	public static void main(String[] args){
//		System.out.println(getFibonacciMinIndexELKey(20, null));
		System.out.println(floatPointMoveRight(0.523412d));
	}


}
