package com.test.algorithm.stack;

@Deprecated
public class ExpressionCalculatorWrong {

	public final static int OPT_OFFSET = (1 << 4) - 1;

	public final static int OPT_MUL = 0;
	public final static int OPT_DIV = 1;
	public final static int OPT_ADD = 2;
	public final static int OPT_DEC = 3;

	/**
	 * @param inputStr 输入一个表达式
	 * @return 返回表达式的计算结果
	 */
	public static double calc(String inputStr) throws CalcException{
		int len  = inputStr.length();
		ArrayDoubleStack numStack = new ArrayDoubleStack(len);
		ArrayIntStack optStack = new ArrayIntStack(len);//记录了 (操作优先级 << 4) | 操作符
		int lastChar = -1;
		for(int i = 0;i < len; i++){
			int nextChar = inputStr.charAt(i);
			if(isNumber(nextChar)){//当是数字时入栈
				if(isNumber(lastChar)){
					double last = numStack.pop();
					numStack.push((nextChar - 48f) + last * 10f);
					System.out.println("pushnum2=" + (nextChar - 48 + last * 10f));
				}else{
					numStack.push(nextChar - 48);
					System.out.println("pushnum1=" + (char)nextChar);
				}
			}else if(isOperation(nextChar)){//当是符号时,考虑是否栈空
				int operation = charToOperation(nextChar);
				if(optStack.isEmpty()){//符号栈为空 直接入符号栈
					optStack.push(operation);
					System.out.println("pushopt" + (char)nextChar);
				}else{
					int topOperation = optStack.getTop();
					System.out.println("compare" + operationToPrority(operation) + " and" + operationToPrority(topOperation));
					if(operationToPrority(operation) < operationToPrority(topOperation)){
						//当入栈的操作优先级 小于符号栈顶的优先级时
						//先运算(数字栈顶 - 1, 数字栈顶, 符号栈顶)
						System.out.println("calcopt" + (topOperation & OPT_OFFSET));
						optStack.pop();
						double up = numStack.pop();
						double down = numStack.pop();
						numStack.push(calcByNum(down, up, topOperation & OPT_OFFSET));//把numStack两个值按运算符做计算并将结果入数字栈
						optStack.push(operation);
					}else{
						optStack.push(operation);
						System.out.println("pushopt" + (char)nextChar);
					}
				}
			}
			lastChar = nextChar;
		}
		while(numStack.size() > 1){
			int topOperation = optStack.pop();
			double up = numStack.pop();
			double down = numStack.pop();
			numStack.push(calcByNum(down, up, topOperation & OPT_OFFSET));//把numStack两个值按运算符做计算并将结果入数字栈
		}
		return numStack.getTop();
	}

	public static double calcByNum(double numDown, double numUp, int optIndex){
		System.out.println("计算" + numDown + " 与" + numUp + "的" + optIndex);
		switch(optIndex){
			case OPT_MUL:
				return numDown * numUp;
			case OPT_DIV:
				return numDown / numUp;
			case OPT_ADD:
				return numDown + numUp;
			case OPT_DEC:
				return numDown - numUp;
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

	public static boolean isNumber(int operationChar){
		if(operationChar <= '9' && operationChar >= '0'){
			return true;
		}
		return false;
	}

	static class CalcException extends RuntimeException{
		public CalcException(){}
		public CalcException(String msg){
			super(msg);
		}
	}
}