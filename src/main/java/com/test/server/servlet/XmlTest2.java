package com.test.server.servlet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class XmlTest2 {

	public static void main(String[] args) throws ClassNotFoundException {
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

			//获取数据
			List<Entity> list = handler.getEntities();
			for(Entity entity : list){
				System.out.println(entity);
			}
			int a = handler.getMappings().size();
			System.out.println(a);
			System.out.println(list.size());

			WebContent webContent = new WebContent(list, handler.getMappings());
			String name = webContent.getClz("/g");
			Class clz = Class.forName(name);
			Servlet servlet = (Servlet) clz.getConstructor().newInstance();
			servlet.service();

		} catch(ParserConfigurationException e) {
			e.printStackTrace();
		} catch(SAXException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		} catch(InstantiationException e) {
			e.printStackTrace();
		} catch(NoSuchMethodException e) {
			e.printStackTrace();
		} catch(InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	public static class WebHandler extends DefaultHandler {
		private List<Entity> entities;
		private List<Mapping> mappings;
		private Entity entity;
		private Mapping mapping;
		private String tag;
		private boolean isMapping = false;
		@Override
		public void startDocument() throws SAXException {
			entities = new ArrayList<>();
			mappings = new ArrayList<>();
		}

		@Override
		public void endDocument() throws SAXException {
		}

		@Override
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			if(null != qName){
				tag = qName;
				if(tag.equals("servlet")){
					entity = new Entity();
					isMapping = false;
				}
				if(tag.equals("servlet-mapping")){
					mapping = new Mapping();
					isMapping = true;
				}
			}

		}

		@Override
		public void endElement(String uri,String localName,String qName) throws SAXException {
			if(null != qName){
				if(qName.equals("servlet")){
					entities.add(entity);
				}
				if(qName.equals("servlet-mapping")){
					mappings.add(mapping);
				}
			}
			tag = null;
		}

		@Override
		public void characters(char[] ch,int start,int length) throws SAXException {
			String str = new String(ch, start, length).trim();
			if(null != tag){
				if(isMapping){
					if(tag.equals("servlet-name")){
						mapping.setName(str);
					}else if(tag.equals("url-pattern")){
						mapping.addPattern(str);
					}
				}else{
					if(tag.equals("servlet-name")){
						entity.setName(str);
					}else if(tag.equals("servlet-class")){
						entity.setClz(str);
					}
				}
			}
		}

		public List<Entity> getEntities() {
			return entities;
		}

		public List<Mapping> getMappings() {
			return mappings;
		}
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
