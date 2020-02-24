package com.test.server.servlet;

import com.test.server.http.core.Dispatcher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 加入分发器
 */
public class Server08 {

	ServerSocket serverSocket;
	private boolean isRunning;

	public static void main(String[] args){
		Server08 server = new Server08();
		server.start();
	}
	public void start(){
		try {
			serverSocket = new ServerSocket(8888);
			isRunning = true;
			recieve();
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("启动失败");
			stop();
		}
	}

	public void recieve(){
		try {
			while(isRunning){
				Socket client = serverSocket.accept();

				new Thread(new Dispatcher(client)).start();
			}
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("客户端错误");
		}
	}

	public void stop(){
		isRunning = false;
		try {
			serverSocket.close();
			System.out.println("服务器已停止");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
