package com.test.server.base;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxTest {
	public static void main(String[] args){
		try {
			//1 获取解析工厂
			SAXParserFactory factory = SAXParserFactory.newInstance();
			//2 从工厂获得解析器
			SAXParser parser = factory.newSAXParser();
			//3 加载文档注册处理器
			//4 编写处理器
			PersonHandler hanlder = new PersonHandler();
			parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("person.xml"),
					hanlder);

			//获取数据
			List<Person> list = hanlder.getPersons();
			for(Person person : list){
				System.out.println(person);
			}
		} catch(ParserConfigurationException e) {
			e.printStackTrace();
		} catch(SAXException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static class PersonHandler extends DefaultHandler {
		private List<Person> persons;
		private Person person;
		private String tag;
		@Override
		public void startDocument() throws SAXException {
			System.out.println("start");
			persons = new ArrayList<>();
		}

		@Override
		public void endDocument() throws SAXException {
			System.out.println("end");
		}

		@Override
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			System.out.println(qName + "--start");
			if(null != qName){
				tag = qName;
				if(tag.equals("name")){
					person = new Person();
				}
			}

		}

		@Override
		public void endElement(String uri,String localName,String qName) throws SAXException {
			System.out.println(qName + "--end");
			if(null != qName && qName.equals("person")){
				persons.add(person);
			}
			tag = null;
		}

		@Override
		public void characters(char[] ch,int start,int length) throws SAXException {
			String str = new String(ch, start, length).trim();
			if(null != tag){
				if(tag.equals("name")){
					person.setName(str);
				}else if(tag.equals("age")){
					if(str.length() > 0){
						person.setAge(Integer.parseInt(str));
					}
				}
			}
		}

		public List<Person> getPersons() {
			return persons;
		}
	}

	public static class Person{
		String name;
		int age;
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "Person{" +
					"name='" + name + '\'' +
					", age=" + age +
					'}';
		}
	}
}