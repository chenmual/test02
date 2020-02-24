package com.test.server.http.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 处理404页面
 */
public class Server {

	ServerSocket serverSocket;
	private boolean isRunning;

	public static void main(String[] args){
		Server server = new Server();
		server.start();
	}
	public void start(){
		try {
			serverSocket = new ServerSocket(8888);
			isRunning = true;
			receive();
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("启动失败");
			stop();
		}
	}

	public void receive(){
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
