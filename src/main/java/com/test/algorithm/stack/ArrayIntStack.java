package com.test.algorithm.stack;

import java.util.Scanner;

/**
 * 数组模拟栈
 */
public class ArrayIntStack {


	int maxSize = 0;
	int[] array;
	int top = -1;//指向栈顶,-1为空栈

	public ArrayIntStack(int size){
		this.maxSize = size;
		array = new int[maxSize];
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
	public int pop(){
		if(isEmpty()){
			throw new RuntimeException("无法弹出,栈为空");
		}
		return array[top--];
	}

	/**
	 * @param val
	 * @return 是否成功
	 */
	public boolean push(int val){
		if(isFull()){
			System.out.println("栈已满");
			return false;
		}
		array[++top] = val;
		return true;
	}

	public int getTop(){
		if(isEmpty()){
			throw new RuntimeException("栈为空");
		}
		return array[top];
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

	public static void test(){
		ArrayIntStack arrayStack = new ArrayIntStack(10);
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		while(loop){
			System.out.println("show:显示栈");
			System.out.println("exit:退出");
			System.out.println("pop:弹出栈顶");
			System.out.println("push:入栈");
			switch(scanner.nextLine()){
				case "show":
					arrayStack.showStack();
					break;
				case "exit":
					scanner.close();
					loop = false;
					System.out.println("结束");
					break;
				case "pop":
					try {
						System.out.println(arrayStack.pop());
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
					break;
				case "push":
					System.out.print("输入一个数:");
					int in = scanner.nextInt();
					arrayStack.push(in);
					break;
			}
		}
	}
}
