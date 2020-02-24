package com.test.server.http.servlet;

import com.test.server.http.core.MyHttpRequest;
import com.test.server.servlet.MyHttpResponse;

/**
 * 加入servlet 解耦不同的业务代码
 */
public interface MyServlet {
	void service(MyHttpRequest request,MyHttpResponse response);
}
