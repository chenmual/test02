package com.test.server.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 接受协议
 */
public class Server01 {

	ServerSocket serverSocket;

	public static void main(String[] args){
		Server01 server01 = new Server01();
		server01.start();
	}
	public void start(){
		try {
			serverSocket = new ServerSocket(8888);
			recieve();
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("启动失败");
		}
	}

	public void recieve(){
		try {
			Socket client = serverSocket.accept();
			System.out.println("一个客户端建立连接");
			InputStream is = client.getInputStream();

			byte[] datas = new byte[1024 * 1024];
			int len = is.read(datas);

			String requestInfo = new String(datas, 0, len);
			System.out.println(requestInfo);
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("客户端错误");
		}
	}

	public void stop(){

	}
}
