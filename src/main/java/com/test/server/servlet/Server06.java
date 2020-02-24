package com.test.server.servlet;

import com.test.server.http.core.MyHttpRequest;
import com.test.server.http.core.WebApp;
import com.test.server.http.servlet.MyServlet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 封装响应信息
 */
public class Server06 {

	ServerSocket serverSocket;

	public static void main(String[] args){
		Server06 server = new Server06();
		server.start();
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
				System.out.println("一个客户端建立连接6");
				MyHttpRequest request = new MyHttpRequest(client);

				//返回
				//1 HTTP/1.1 200 OK
				//2 响应头(最后一行空行)
				//3 正文 封装后只关注内容
				MyHttpResponse response = new MyHttpResponse(client);
				MyServlet servlet = WebApp.getServletFromUrl(request.getUrl());
//				if(request.getUrl().equals("login")){
//					servlet= new LoginServlet();
//				}else if(request.getUrl().equals("reg")){
//					servlet= new RegisterServlet();
//				}else{
//					//首页
//				}
				if(null != servlet){
					servlet.service(request, response);
					response.pushToBrowser(200);
				}else{
					//错误
					response.pushToBrowser(404);
				}

//				response.print("<html>");
//				response.print("<head>");
//				response.print("<title>");
//				response.print("第一个servlet");
//				response.print("</title>");
//				response.print("</head>");
//				response.print("<body>");
//				response.print("响应回来了");
//				response.print(request.getParameter("name"));
//				response.print("</body>");
//				response.print("</html>");
				//写入客户端
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
