package com.test.algorithm.stack;

import java.util.Scanner;

public class ArrayDoubleStack {


	int maxSize = 0;
	double[] array;
	int top = -1;//指向栈顶,-1为空栈

	public ArrayDoubleStack(int size){
		this.maxSize = size;
		array = new double[maxSize];
	}

	public boolean isFull(){
		return top == maxSize - 1;
	}
	public boolean isEmpty(){
		return top == -1;
	}
	public int size(){
		return top + 1;
	}

	/**
	 * 将栈顶数据返回
	 * @return
	 */
	public double pop(){
		if(isEmpty()){
			throw new RuntimeException("无法弹出,栈为空");
		}
		return array[top--];
	}

	public double getTop(){
		if(isEmpty()){
			throw new RuntimeException("栈为空");
		}
		return array[top];
	}

	/**
	 * @param val
	 * @return 是否成功
	 */
	public boolean push(double val){
		if(isFull()){
			System.out.println("栈已满");
			return false;
		}
		array[++top] = val;
		return true;
	}

	/**
	 * 从栈顶开始显示数据
	 */
	public void showStack(){
		if(isEmpty()){
			System.out.println("栈为空");
			return;
		}
		for(int i = top; i > - 1; i--){
			System.out.println("stack[" + i + "]:" + array[i]);
		}
	}
}
