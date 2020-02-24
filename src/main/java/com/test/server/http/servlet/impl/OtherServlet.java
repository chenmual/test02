package com.test.server.http.servlet.impl;


import com.test.server.http.core.MyHttpRequest;
import com.test.server.http.servlet.MyServlet;
import com.test.server.servlet.MyHttpResponse;

public class OtherServlet implements MyServlet {

	@Override
	public void service(MyHttpRequest request, MyHttpResponse response) {
		response.print("其他");
	}
}
