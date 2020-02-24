package com.test.server.http.servlet.impl;


import com.test.server.http.core.MyHttpRequest;
import com.test.server.http.servlet.MyServlet;
import com.test.server.servlet.MyHttpResponse;

public class RegisterServlet implements MyServlet {

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
		response.print("注册成功");
		//关注业务逻辑
		String uname = request.getParameter("uname");
		String[] favs = request.getParameterValues("fav");
		response.println("名字:" + uname);
		response.println("类型:");
		for(String str : favs){
			if(str.equals("0")){
				response.print("傲娇");
			}
			if(str.equals("1")){
				response.print("腹黑");
			}
			if(str.equals("1")){
				response.print("中二");
			}
			if(str.equals("1")){
				response.print("弟弟");
			}
			if(str.equals("1")){
				response.print("强者");
			}
		}
		response.print("</body>");
		response.print("</html>");
	}
}
