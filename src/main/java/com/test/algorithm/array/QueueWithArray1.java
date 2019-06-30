package com.test.algorithm.array;

import java.util.Scanner;

/**
 * 使用数组实现队列
 * 废弃
 * 算法无法满足需求见{@link QueueWithArray2}
 */

@Deprecated
public class QueueWithArray1 {
	int[] queue;
	int maxSize;
	int front;//数组第一个元素的前一个位置
	int rear;//数组最后一个元素的位置
	public QueueWithArray1(int len){
		maxSize = len;
		queue = new int[len];
		front = -1;//指向队列头的前一个位置
		rear = -1;//队列尾=最后一个元素
	}

	public boolean isFull(){
		return rear == maxSize - 1;
	}

	public boolean isEmpty(){
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
		rear++;
		queue[rear] = element;
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
		front++;
		return queue[front];
	}

	public void showQueue(){
		if(isEmpty()){
			System.out.println("队列为空");
			return;
		}
		for(int i = 0; i < maxSize; i++){
			System.out.printf("arr[%d]=%d ", i, queue[i]);
		}
		System.out.println();
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
		QueueWithArray1 queueWithArray = new QueueWithArray1(3);
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
