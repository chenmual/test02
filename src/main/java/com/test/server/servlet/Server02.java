package com.test.server.servlet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 接受协议并返回响应协议
 */
public class Server02 {

	ServerSocket serverSocket;

	public static void main(String[] args){
		Server02 server02 = new Server02();
		server02.start();
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

			//返回
			//1 HTTP/1.1 200 OK
			//2 响应头(最后一行空行)
			//3 正文
			StringBuilder content = new StringBuilder();
			content.append("<html>");
			content.append("<head>");
			content.append("<title>");
			content.append("成功");
			content.append("</title>");
			content.append("</head>");
			content.append("<body>");
			content.append("响应回来了");
			content.append("</body>");
			content.append("</html>");
			int size = content.toString().getBytes().length;
			StringBuilder responseInfo = new StringBuilder();
			String blank = " ";
			String CRLF = "\r\n";
			responseInfo.append("HTTP/1.1").append(blank);
			responseInfo.append(200).append(blank);
			responseInfo.append("OK").append(CRLF);
			responseInfo.append("Date:").append(new Date()).append(CRLF);
			responseInfo.append("Server:").append("fdsaf Server/0.0.1;charset=GBK").append(CRLF);
			responseInfo.append("Content-type:text/html").append(CRLF);
			responseInfo.append("Content-Length:").append(size).append(CRLF);
			responseInfo.append(CRLF);

			responseInfo.append(content.toString());

			//写入客户端
			OutputStream outputStream = client.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
			bw.write(responseInfo.toString());
			bw.flush();
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("客户端错误");
		}
	}

	public void stop(){

	}
}
