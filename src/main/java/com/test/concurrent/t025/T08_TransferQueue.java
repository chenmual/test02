package com.test.concurrent.t025;

import java.util.concurrent.LinkedTransferQueue;

/**
 * 往往消费者先启动
 * 生产者会先检查有没有消费者有的话直接转发给消费者
 *
 * 服务器消息队列 转发客户端消息 更高的并发时 Netty里使用比较多
 */
public class T08_TransferQueue {
	public static void main(String[] args) throws InterruptedException {
		//要先起消费者线程
		LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		//一直阻塞 等待消费者
		strs.transfer("aaa");

//		strs.checkPos("aaa");//队列也可以往里仍  有一定容量

//		后起消费者线程
//		new Thread(() -> {
//			try {
//				System.out.println(strs.take());
//			} catch(InterruptedException e) {
//				e.printStackTrace();
//			}
//		}).start();
	}
}
