package com.test.server.http.servlet.impl;


import com.test.server.http.core.MyHttpRequest;
import com.test.server.http.servlet.MyServlet;
import com.test.server.servlet.MyHttpResponse;

public class LoginServlet implements MyServlet {
	@Override
	public void service(MyHttpRequest request, MyHttpResponse response) {
		response.print("<html>");
		response.print("<head>");
		response.print(MyHttpResponse.CHARSETSTR);
		response.print("<title>");
		response.print("第一个servlet");
		response.print("</title>");
		response.print("</head>");
		response.print("<body>");
		response.print("欢迎回来");
		response.print(request.getParameter("uname"));
		response.print("</body>");
		response.print("</html>");
	}
}
