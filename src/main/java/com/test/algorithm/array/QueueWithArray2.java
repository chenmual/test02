package com.test.algorithm.array;

import java.util.Scanner;

public class QueueWithArray2 {
	int[] queue;
	int maxSize;
	//指向数组第一个元素,默认为0.
	int front;
	//指向数组最后一个元素的下一个位置
	//预留出一个位置可以使(rear + 1) % maxSize == front
	//默认为0
	int rear;
	public QueueWithArray2(int len){
		maxSize = len;
		queue = new int[len];
	}

	public boolean isFull(){
		return (rear + 1) % maxSize == front;
	}

	public boolean isEmpty(){
		//没有变化
		return rear == front;
	}

	/**
	 * 往队尾加一个元素
	 * @return 是否成功
	 */
	public boolean offer(int element){
		if(isFull()){
			System.out.println("队列已满");
			return false;
		}
		queue[rear] = element;
		//rear++考虑取模
		rear = (rear + 1) % maxSize;
		return true;
	}

	/**
	 * 从队头取一个元素(移除)
	 * @return 返回元素或抛出异常
	 */
	public int poll(){
		if(isEmpty()){
			throw new RuntimeException("队列为空");
		}
		int temp = queue[front];
		front = (front + 1) % maxSize;
		return temp;
	}

	public void showQueue(){
		if(isEmpty()){
			System.out.println("队列为空");
			return;
		}
		int len = front + size();
		for(int i = front; i < len; i++){
			int idx = i % maxSize;
			System.out.printf("arr[%d]=%d ", idx, queue[idx]);
		}
		System.out.println();
	}

	public int size(){
		return (rear + maxSize - front) % maxSize;
	}

	/**
	 * 从队列头取一个元素(不移除)
	 * 返回队头元素或者exception
	 */
	public int peek(){
		if(isEmpty()){
			throw new RuntimeException("队列为空");
		}
		return queue[front];
	}


	public static void testDemo(){
		QueueWithArray2 queueWithArray = new QueueWithArray2(4);
		Scanner scanner = new Scanner(System.in);
		boolean needBreak = false;
		while(!needBreak){
			System.out.println("s:显示(show)队列");
			System.out.println("e:退出");
			System.out.println("a:添加(add/offer)数据到队尾");
			System.out.println("r:移除(remove/poll)队头");
			System.out.println("p:取(peek/element)队头");
			char key = scanner.next().charAt(0);
			switch(key){
				case 's':
					queueWithArray.showQueue();
					break;
				case 'e':
					needBreak = true;
					break;
				case 'a':
					try {
						System.out.println("输入一个数:");
						int a = scanner.nextInt();
						boolean isOffer = queueWithArray.offer(a);
						if(isOffer){
							System.out.println("添加成功,当前队列为:");
							queueWithArray.showQueue();
						}
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
					break;
				case 'r':
					try {
						int ret = queueWithArray.poll();
						System.out.println("取出的元素为" + ret + ",当前队列为:");
						queueWithArray.showQueue();
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
					break;
				case 'p':
					try {
						int ret = queueWithArray.peek();
						System.out.println("取出的元素为" + ret + ",当前队列为:");
						queueWithArray.showQueue();
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
					break;
			}
		}
	}
}
