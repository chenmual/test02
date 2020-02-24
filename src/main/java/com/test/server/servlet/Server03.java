package com.test.server.servlet;

import com.test.server.http.core.MyHttpRequest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 封装响应信息
 */
public class Server03 {

	ServerSocket serverSocket;

	public static void main(String[] args){
		Server03 server03 = new Server03();
		server03.start();
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
			boolean needBreak = true;
			while(true){
				Socket client = serverSocket.accept();
				System.out.println("一个客户端建立连接3");
				MyHttpRequest request = new MyHttpRequest(client);

				//返回
				//1 HTTP/1.1 200 OK
				//2 响应头(最后一行空行)
				//3 正文 封装后只关注内容
				MyHttpResponse response = new MyHttpResponse(client);
				response.print("<html>");
				response.print("<head>");
				response.print("<title>");
				response.print("成功");
				response.print("</title>");
				response.print("</head>");
				response.print("<body>");
				response.print("响应回来了");
				response.print(request.getParameter("name"));
				response.print("</body>");
				response.print("</html>");
				//写入客户端
				response.pushToBrowser(200);
				client.close();
			}
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("客户端错误");
		}
	}

	public void stop(){

	}
}
