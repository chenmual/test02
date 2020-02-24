package com.test.server.servlet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * 封装响应信息
 * 1.内容动态添加
 * 2.关注状态码
 */
public class MyHttpResponse {

	public static final String CHARSETSTR = "<meta charset=\"UTF-8\">";
	private final String blank = " ";
	private final String CRLF = "\r\n";

	private BufferedWriter bw;

	//正文
	private StringBuilder content;
	//协议头信息(状态行与请求头 回车)
	private StringBuilder headInfo;
	private int len = 0;//正文的字节数

	public MyHttpResponse() {
		content = new StringBuilder();
		headInfo = new StringBuilder();
		len = 0;
	}

	public MyHttpResponse(Socket client) {
		this();
		try {
			bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch(IOException e) {
			e.printStackTrace();
			headInfo = null;
		}
	}

	public MyHttpResponse(OutputStream outputStream){
		this();
		bw = new BufferedWriter(new OutputStreamWriter(outputStream));
	}

	public MyHttpResponse print(String info){
		content.append(info);
		len += info.getBytes().length;
		return this;
	}
	public MyHttpResponse println(String info){
		content.append(info).append(CRLF);
		len += (info + CRLF).getBytes().length;
		return this;
	}

	private void createHeaderInfo(int code){
		//1 响应行
		headInfo.append("HTTP/1.1").append(blank);
		headInfo.append(code).append(blank);
		switch(code){
			case 200:
				headInfo.append("OK").append(CRLF);
				break;
			case 404:
				headInfo.append("NOT FOUND").append(CRLF);
				break;
			case 505:
				headInfo.append("SERVER ERROR").append(CRLF);
				break;
		}
		//2 响应行
		headInfo.append("Date:").append(new Date()).append(CRLF);
		headInfo.append("Server:").append("fdsaf Server/0.0.1;charset=GBK").append(CRLF);
		headInfo.append("Content-type:text/html").append(CRLF);
		headInfo.append("Content-Length:").append(len).append(CRLF);
		headInfo.append(CRLF);
	}

	/**
	 * 推送响应信息
	 */
	public void pushToBrowser(int code) throws IOException {
		if(null == headInfo){
			code = 505;
		}
		createHeaderInfo(code);
		bw.append(headInfo);
		bw.append(content);
		bw.flush();
	}
}
