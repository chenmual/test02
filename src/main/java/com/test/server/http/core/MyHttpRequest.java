package com.test.server.http.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.*;

/**
 * 封装请求信息(封装请求参数)
 */
public class MyHttpRequest {
	private final String blank = " ";
	private final String CRLF = "\r\n";

	//协议信息
	private String requestInfo;
	//请求方式
	private String method;
	private String url;
	private String queryStr;

	private Map<String, List<String>> parameterMap;

	public MyHttpRequest() {
	}

	public MyHttpRequest(InputStream inputStream) {
		parameterMap = new HashMap<>();
		byte[] datas = null;
		try {
			datas = new byte[1024 * 1024];
			int len = 0;
			try {
				len = inputStream.read(datas);
			} catch(IOException e) {
				e.printStackTrace();
			}

			this.requestInfo = new String(datas, 0, len);

			parseInfo();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public MyHttpRequest(Socket client) throws IOException {
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
		convertMap();
	}

	//处理请求参数
	private void convertMap(){
		//queryStr: param1=v1&param2=v2
		//1 按"&"分割字符串
		String[] params = queryStr.split("&");
		for(String query : params){
			//2 按"="分割每段字符串
			String[] kv = query.split("=");
			kv = Arrays.copyOf(kv, 2);
			//获取key value
			String key = kv[0];
			String val = kv[1] == null? null : decode(kv[1], "UTF-8");
			//存储到map中
			if(!parameterMap.containsKey(key)){
				parameterMap.put(key, new ArrayList<>());
			}
			parameterMap.get(key).add(val);
		}
	}

	/**
	 * 通过name获取多个值
	 * @return
	 */
	public String[] getParameterValues(String key){
		List<String> values = this.parameterMap.get(key);
		if(null == values || values.size() < 1){
			return null;
		}
		return values.toArray(new String[0]);
	}

	/**
	 * 通过name获取一个值
	 * @param key
	 * @return
	 */
	public String getParameter(String key){
		String[] values = getParameterValues(key);
		return values == null? null : values[0];
	}

	/**
	 *
	 * @return
	 */
	private String decode(String value, String enc){
		try {
			return java.net.URLDecoder.decode(value, enc);
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}


	public String getMethod() {
		return method;
	}

	public String getUrl() {
		return url;
	}

	public String getQueryStr() {
		return queryStr;
	}
}
