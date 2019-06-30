package com.test.algorithm.stack;

import com.test.util.NumberUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SuffixExpresionUtil {
	public static void test(){
		String suffixExpression = "1 + ((2 + 3) * 4 / 2) - 5 + 11 - 4 + 6 * (7 - 7) * 0 - 12 + 4 * 5";
		int calc = calc(suffixExpression);
		System.out.println("------result------");
		System.out.println(calc);
	}

	public static List<String> getListString(String suffixExpression){
		String[] split = suffixExpression.split(" ");
		List<String> list = new ArrayList<>();
		for(String ele : split){
			list.add(ele);
		}
		return list;
	}

	public static int calculate(List<String> list){
		Stack<String> stack = new Stack<>();
		for(String item: list){
			if(item.matches("\\d+")){//匹配多位数
				stack.push(item);
			}else{//运算符
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				int res = 0;
				if(item.equals("+")){
					res = num1 + num2;
				}else if(item.equals("-")){
					res = num1 - num2;
				}else if(item.equals("*")){
					res = num1 * num2;
				}else if(item.equals("/")){
					res = num1 / num2;
				}
				stack.push(res + "");
			}
		}
		return Integer.parseInt(stack.pop()) ;
	}

	public static int calc(String input){
		Stack<String> opStack = new Stack<>();
		Stack<String> numStack = new Stack<>();
		int len = input.length();
		char nextChar = 0;
		char lastChar = 0;
		int nextIndex = -1;
		int nextOp = 0;
		while(++nextIndex < len){
			nextChar = input.charAt(nextIndex);
			if(NumberUtil.isNumber(nextChar)){//处理多位数
				String pushStr = String.valueOf(nextChar);
				if(NumberUtil.isNumber(lastChar)){
					pushStr = numStack.pop() + pushStr;
				}
//				System.out.println("pushnum" + pushStr);
				numStack.push(pushStr);
			}else if((nextOp = getOpType(nextChar)) > 0){
				if(nextChar == '('){
					opStack.push(String.valueOf(nextChar));
				}else{
					if(nextChar == ')'){//当是右括号的,找到前面的左括号 将两者之间的运算符依次挪到numstack
						String popStr;
						while(true){
//							System.out.println("popOp=" + opStack);
							popStr = String.valueOf(opStack.pop());
							if(!popStr.equals("(")){
								numStack.push(popStr);
							}else{
								break;
							}
						}
					}else{
						if(opStack.isEmpty()){
//							System.out.println("pushop2:" + String.valueOf(nextChar));
							opStack.push(String.valueOf(nextChar));
						}else{
							int topType = getOpType((opStack.peek()).charAt(0));
							if(nextOp > topType || topType == 5){//优先级大或者top是"("
//								System.out.println("pushop0:" + String.valueOf(nextChar));
								opStack.push(String.valueOf(nextChar));
							}else{
								numStack.push(opStack.pop());
//								System.out.println("pushop1:" + String.valueOf(nextChar));
								opStack.push(String.valueOf(nextChar));
							}
						}
					}
				}
			}
			lastChar = nextChar;
		}
		while(numStack.size() > 0){
			String pushOp = numStack.pop();
//			System.out.println("pushOp:" + pushOp);
			opStack.push(pushOp);
		}
		List<String> list = new ArrayList<>();
		while(opStack.size() > 0){
			list.add(opStack.pop());
		}
//		list.forEach(System.out::println);
		return calculate(list);
//		return -1;
	}

	public static int getOpType(int ch){
		switch(ch){
			case '+':
				return 1;
			case '-':
				return 2;
			case '*':
				return 3;
			case '/':
				return 4;
			case '(':
				return 5;
			case ')':
				return 6;
		}
		return 0;
	}
}
