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
}
