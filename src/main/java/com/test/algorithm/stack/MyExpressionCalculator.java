package com.test.algorithm.stack;

import com.test.util.NumberUtil;

public class MyExpressionCalculator {
	public final static int OFFSET_3 = (1 << 3) - 1;
	public final static int OFFSET_4 = (1 << 4) - 1;

	public final static int OPT_MUL = 0;
	public final static int OPT_DIV = 1;
	public final static int OPT_ADD = 2;
	public final static int OPT_DEC = 3;

	public final static int PAR_LEFT = 1;
	public final static int PAR_RIGHT = 2;

	public final static int PAR_LEFT_OFFSET = (2 - PAR_LEFT) << 3;//1<<3
	public final static int PAR_RIGHT_OFFSET = (2 - PAR_RIGHT) << 3;//0<<3

	/**
	 * @param inputStr 输入一个表达式
	 * @return 返回表达式的计算结果 目前只支持. + - * / () [] {} <> 操作,其中/操作会产生精度问题
	 */
	public static double calc(String inputStr) throws CalcException{
		int len  = inputStr.length();
		if(len >= 100){
			throw new CalcException("表达式太长,无法计算");
		}
		ArrayDoubleStack numStack = new ArrayDoubleStack(len);
		ArrayIntStack optStack = new ArrayIntStack(len);//记录了 (操作优先级 << 4) | 操作符
		ArrayDoubleStack tempNumStack = new ArrayDoubleStack(11);//有多位数时先按顺序记录
//		ArrayIntStack parinfoStack = new ArrayIntStack(inputStr.length());//记录括号信息index<< 4 | type
		ArrayIntStack parenthereTypeStack = new ArrayIntStack(len);
		ArrayIntStack parenthereNumTopStack = new ArrayIntStack(len);//当括号栈有括号时,当前(数字栈.size+1)入此栈,用于计算左括号的右边表达式时计算循环出口
		ArrayIntStack parenthereOptTopStack = new ArrayIntStack(len);//当括号栈有括号时,当前(符号栈.size)入此栈,用于在比较符号优先级时判断循环出口
		int parentheseType = 0;
		for(int i = len - 1;i > -1; i--){
			int nextChar = inputStr.charAt(i);
			if(NumberUtil.isNumber(nextChar)){//当是数字时入栈
				int push = nextChar - 48;
				tempNumStack.push(push);
				int add = 0;
				int nextIndex;
				int point = -1;
				while(((nextIndex = i + --add) > -1) && (NumberUtil.isNumber(nextChar = inputStr.charAt(nextIndex)) || nextChar == '.')){
//					System.out.println("readchar:" + (char)nextChar);
					if(nextChar == '.'){
						point = 1;
					}else{
						if(tempNumStack.push(nextChar - 48)){
							if(point > 0){
								point++;
							}
						}else{
							throw new CalcException("输入数据过大");
						}
					}
				}
//				System.out.println("befor point=" + point);
				double ret = 0;
				int offset = point;
				while(!tempNumStack.isEmpty()){
					if(offset > 0){//有小数点
						point--;
						if(point > 0){
							ret = ret * 10d + tempNumStack.pop();
						}else{
//							ret = ret + tempNumStack.pop() * (Math.pow(10, point - 1));
							ret = NumberUtil.bigAdd(ret, tempNumStack.pop() * (Math.pow(10, point - 1)));
//							ret = NumberUtil.bigAdd(ret, NumberUtil.bigMul(tempNumStack.pop(), (double) (Math.pow(10, point - 1))));
						}
					}else{
						ret = ret * 10d + tempNumStack.pop();
					}
				}
				i = i + add + 1;
				numStack.push(ret);
//				System.out.println("pushnum0=" + ret + " add=" + -(add + 1) + " point=" + point);
			}else if(isOperation(nextChar)){//当是符号时,考虑是否栈空
				int operation = charToOperation(nextChar);
				if(optStack.isEmpty()){//符号栈为空 直接入符号栈
					optStack.push(operation);
//					System.out.println("pushopt" + (char)nextChar);
				}else{
					int topOperation = optStack.getTop();
//					System.out.println("compare" + operationToPrority(operation) + " and" + operationToPrority(topOperation));
					int parenthereOptTop = parenthereOptTopStack.size() > 0?parenthereOptTopStack.getTop() : 0;
					if(operationToPrority(operation) < operationToPrority(topOperation) && optStack.size() > parenthereOptTop){
						//当入栈的操作优先级 小于符号栈顶的优先级时
						//先运算(数字栈顶 - 1, 数字栈顶, 符号栈顶)
						do {
//							System.out.println("calcopt" + (topOperation & OFFSET_4));
							optStack.pop();
							double up = numStack.pop();
							double down = numStack.pop();
							numStack.push(calcByNum(down, up, topOperation & OFFSET_4, 0));//把numStack两个值按运算符做计算并将结果入数字栈
							topOperation = optStack.isEmpty()? 0 : optStack.getTop();
							parenthereOptTop = parenthereOptTopStack.size() > 0?parenthereOptTopStack.getTop() : 0;
						}while(operationToPrority(operation) < operationToPrority(topOperation) && optStack.size() > parenthereOptTop);
						optStack.push(operation);
//						optStack.showStack();
					}else{
						optStack.push(operation);
//						System.out.println("pushopt" + (char)nextChar);
					}
				}
			}else if(nextChar == '.'){
				throw new CalcException("暂时无法计算小数");
			}else if((parentheseType = parentheseType(nextChar)) > 0){
				if(isLeft(parentheseType)){
					int topParenthere, topSize;
					if(parenthereTypeStack.size() > 0){
						topParenthere = parenthereTypeStack.pop();
						topSize = parenthereNumTopStack.pop();
						parenthereOptTopStack.pop();
						if(!isPair(parentheseType, topParenthere)){
							throw new CalcException("非法表达式,只有" + (char) nextChar + "的左半部分,在" + (i));
						}
						while(numStack.size() > topSize){
							int topOperation = optStack.pop();
							double up = numStack.pop();
							double down = numStack.pop();
							numStack.push(calcByNum(down, up, topOperation & OFFSET_4, 1));//把numStack两个值按运算符做计算并将结果入数字栈
						}
					}else{
						throw new CalcException("非法表达式,只有" + (char) nextChar + "的左半部分,在" + (i));
					}

				}else{
					parenthereTypeStack.push(parentheseType);
					parenthereNumTopStack.push(numStack.size() + 1);
					parenthereOptTopStack.push(optStack.size());
//					System.out.println("pushNumTop:" + (numStack.size() + 1));
//					System.out.println("pushOptTop:" + (optStack.size()));
//					numStack.showStack();
				}
			}
		}
		while(numStack.size() > 1){
			int topOperation = optStack.pop();
			double up = numStack.pop();
			double down = numStack.pop();
			numStack.push(calcByNum(down, up, topOperation & OFFSET_4, 2));//把numStack两个值按运算符做计算并将结果入数字栈
		}
		return numStack.getTop();
	}

	/**
	 * @param numDown 操作数1
	 * @param numUp 操作数2
	 * @param optIndex 操作类型
	 * @param source 调试参数,便于打印哪调用的
	 * @return
	 */
	public static double calcByNum(double numDown, double numUp, int optIndex, int source){
//		System.out.println((source == 2? "" : (source == 1? "---" : "------")) + "计算" + numUp + " " + getOptChar(optIndex) + " " + numDown + "的结果.");
		switch(optIndex){
			case OPT_MUL:
				return NumberUtil.bigMul(numUp, numDown);
//				return numDown * numUp;
			case OPT_DIV:
				return NumberUtil.bigDiv(numUp, numDown);
//				return numDown / numUp;
			case OPT_ADD:
				return NumberUtil.bigAdd(numUp, numDown);
//				return numDown + numUp;
			case OPT_DEC:
				return NumberUtil.bigDec(numUp, numDown);
//				return numDown - numUp;
			default:
				throw new CalcException("计算失败,运算符非法");
		}
	}


	/**
	 * 根据操作符计算出优先级
	 * @param operationChar
	 * @return
	 */
	public static int charToOperation(int operationChar) {
		switch(operationChar) {
			case '*':
				return (4 << 4) | (OPT_MUL);
			case '/':
				return (4 << 4) | (OPT_DIV);
			case '+':
				return (2 << 4) | (OPT_ADD);
			case '-':
				return (2 << 4) | (OPT_DEC);
			default:
				return -1;
		}
	}

	public static int operationToPrority(int operation){
		return operation >> 4;
	}

	/**
	 * 判断是否是运算符
	 * @param operationChar
	 * @return
	 */
	public static boolean isOperation(int operationChar){
		if(operationChar == '*' || operationChar == '/' || operationChar == '-' || operationChar == '+'){
			return true;
		}
		return false;
	}

	static class CalcException extends RuntimeException{
		public CalcException(String msg){
			super(msg);
		}
	}

	public static int parentheseType(int ch){//括号处理
		int ret = 0;
		switch(ch){
			case '(':
				ret++;
			case '{':
				ret++;
			case '[':
				ret++;
			case '<':
				ret++;
				ret = ret | PAR_LEFT_OFFSET;
				break;
			case ')':
				ret++;
			case '}':
				ret++;
			case ']':
				ret++;
			case '>':
				ret++;
				ret = ret | PAR_RIGHT_OFFSET;
				break;
			default:
				break;
		}
		return ret;
	}

	public static boolean isPair(int type1, int type2){
//		System.out.println("type1=" + type1 + " type2=" + type2);
		return ((type1 & OFFSET_3) == (type2 & OFFSET_3)) && (isLeft(type1) != isLeft(type2));
	}

	public static boolean isLeft(int type){
		return 2 - (type >> 3) == PAR_LEFT;
	}

	public static char getOptChar(int index){
		switch(index) {
			case OPT_MUL:
				return '*';
			case OPT_DIV:
				return '/';
			case OPT_ADD:
				return '+';
			case OPT_DEC:
				return '-';
			default:
				return '?';
		}
	}
}