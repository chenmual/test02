package com.test.server.http.core;

import com.test.server.http.servlet.MyServlet;
import com.test.server.servlet.MyHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Dispatcher implements Runnable {
	private final Socket client;
	private MyHttpRequest request;
	private MyHttpResponse response;

	public Dispatcher(Socket client) {
		this.client = client;
		try {
			this.request = new MyHttpRequest(client);
			this.response = new MyHttpResponse(client);
		} catch(IOException e) {
			e.printStackTrace();
			this.release();
		}
	}

	@Override
	public void run() {
		System.out.println("一个客户端建立连接9");
		try {
			if(null == request.getUrl() || request.getUrl().equals("")){
				InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("index.html");
				byte[] bytes = new byte[is.available()];
				is.read(bytes);
				response.print(new String(bytes));
				response.pushToBrowser(200);
				return;
			}

			MyServlet servlet = WebApp.getServletFromUrl(request.getUrl());
			if(null != servlet){
				servlet.service(request, response);
				response.pushToBrowser(200);
			}else{
				//错误
				InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("error.html");
				byte[] bytes = new byte[is.available()];
				is.read(bytes);
				response.print(new String(bytes));
				response.pushToBrowser(404);
			}
		} catch(IOException e) {
			try {
				response.pushToBrowser(505);
			} catch(IOException e1) {
				e1.printStackTrace();
			}
			System.out.println("服务器错误");
		} finally {
			this.release();
		}
	}

	private void release(){
		try {
			client.close();
		} catch(IOException e1) {
			e1.printStackTrace();
		}
	}
}
