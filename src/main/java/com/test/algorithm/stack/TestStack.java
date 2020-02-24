package com.test.algorithm.stack;

import java.util.Stack;

public class TestStack {
	public static void main(String[] args){
//		ArrayIntStack.test();
//		test1();
//		float ret = MyExpressionCalculator.getMaxValueByCapacity("1 - 1 * 1 - 1");
//		float ret = MyExpressionCalculator.getMaxValueByCapacity("34 - 22 * 60 + 27 * 2 / 9 + 2");
//		float ret = MyExpressionCalculator.getMaxValueByCapacity("394 - 202 * 600 + 27 * 2 / 9 + 2 + 100 * 400 + 2 - 1 - 20 - 60 / 12 - 4 / 2 - 60 + 88 - 20 / 20 / 1 * 3 / 2");
//		double ret = MyExpressionCalculator.getMaxValueByCapacity("1.23 * 2 + 4.5 / 3 + 67.8 + 664.638 * 2 / 4 + 27 * 2 / 9 + 20 - 40 - 12 / 4 + 22.5 / 7.5 - 24444.4 + 2308.3");
		String express = "4 - (1.5 - 2 * 4.667) / ( 1 - 2.1 ) + (6.66 - 12.333 * (4 / 4))";
//		String express = "1 - (10 / (3 + 50 / 5 - 3))";
//		String express = "(10 / 3)";
//		System.out.println(express.charAt(30));
//		System.out.println(express.substring(43 + 1, 50 - 1));
		System.out.println("---------");
//		System.out.println(MyExpressionCalculator.isPair(MyExpressionCalculator.parentheseType('>'), MyExpressionCalculator.parentheseType('<')));
//		double ret = MyExpressionCalculator.getMaxValueByCapacity(express);
//		System.out.println(ret);
		SuffixExpresionUtil.test();

		float a = 123456.78f;
		float b = 0.5f;
		float c = 1234.56f;


		float ret = (a / 12f + b) * c;
		System.out.println(ret);
	}

	public static void test1(){
		Stack<String> stack = new Stack<>();
		stack.push("abc");
		stack.push("bbb");
		stack.push("223");

		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
}
