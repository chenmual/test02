package com.test.server.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 封装请求信息
 */
public class MyHttpRequest1 {
	private final String blank = " ";
	private final String CRLF = "\r\n";

	//协议信息
	private String requestInfo;
	//请求方式
	private String method;
	private String url;
	private String queryStr;


	public MyHttpRequest1() {
	}

	public MyHttpRequest1(InputStream inputStream) {
		byte[] datas = new byte[1024 * 1024];
		int len = 0;
		try {
			len = inputStream.read(datas);
		} catch(IOException e) {
			e.printStackTrace();
		}

		this.requestInfo = new String(datas, 0, len);

		parseInfo();
	}

	public MyHttpRequest1(Socket client) throws IOException {
		this(client.getInputStream());
	}

	public void parseInfo(){
		System.out.println(requestInfo);
		//1 获取请求方式
		this.method = this.requestInfo.substring(0, this.requestInfo.indexOf("/")).toLowerCase();
		this.method = this.method.trim();
		//2 获取请求url(可能包含请求参数)
		//2.1 获取/的位置
		int idx1 = this.requestInfo.indexOf("/") + 1;
		//2.2 获取HTTP的位置
		int idx2 = this.requestInfo.indexOf("HTTP/");
		this.url = this.requestInfo.substring(idx1, idx2).toLowerCase().trim();
		//2.3 分割参数?xxx=xxx
		//2.4 获取?的位置
		int queryIdx = this.url.indexOf("?");
		if(queryIdx >= 0){
			String[] urlArray = this.url.split("\\?");
			this.url = urlArray[0].trim();
			queryStr = urlArray[1];
		}
		//3 获取url后面的参数
		if(method.equals("post")){
			String qStr = this.requestInfo.substring(this.requestInfo.lastIndexOf(CRLF)).trim();
			if(null == queryStr){
				queryStr = qStr;
			}else{
				if(!qStr.equals("")){
					queryStr += "&" + qStr;
				}
			}
		}
		queryStr = null == queryStr? "" : queryStr;
		System.out.println(method + "-->" + url + "-->" + queryStr);
	}
}
