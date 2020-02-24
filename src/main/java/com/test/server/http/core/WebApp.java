package com.test.server.http.core;

import com.test.server.http.servlet.MyServlet;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class WebApp {

	private static WebContent webContext;

	static{
		try {
			//1 获取解析工厂
			SAXParserFactory factory = SAXParserFactory.newInstance();
			//2 从工厂获得解析器
			SAXParser parser = factory.newSAXParser();
			//3 加载文档注册处理器
			//4 编写处理器
			WebHandler handler = new WebHandler();
			parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("web.xml"),
					handler);
			List<Entity> list = handler.getEntities();
			webContext = new WebContent(list, handler.getMappings());
		}catch(Exception e){
			System.out.println("解析配置文件错误");
			e.printStackTrace();
		}
	}

	/**
	 * 通过url获取配置文件对应的servlet
	 * @param url
	 * @return
	 */
	public static MyServlet getServletFromUrl(String url){
		try {

			//获取数据
//			for(Entity entity : list){
//				System.out.println(entity);
//			}
//			int a = handler.getMappings().size();
//			System.out.println(a);
//			System.out.println(list.size());

			String name = webContext.getClz("/" + url);
			if(name == null){
				return null;
			}
			Class clz = Class.forName(name);
			MyServlet servlet = (MyServlet) clz.getConstructor().newInstance();
//			servlet.service();
			return servlet;
		} catch(InstantiationException e) {
			e.printStackTrace();
		} catch(InvocationTargetException e) {
			e.printStackTrace();
		} catch(NoSuchMethodException e) {
			e.printStackTrace();
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws ClassNotFoundException {
	}

//	public static class Person{
//		String name;
//		int age;
//		public String getName() {
//			return name;
//		}
//
//		public void setName(String name) {
//			this.name = name;
//		}
//
//		public int getAge() {
//			return age;
//		}
//
//		public void setAge(int age) {
//			this.age = age;
//		}
//
//		@Override
//		public String toString() {
//			return "Person{" +
//					"name='" + name + '\'' +
//					", age=" + age +
//					'}';
//		}
//	}
}
