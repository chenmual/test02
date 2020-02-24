package com.test.server.http.core;

import com.test.server.http.core.Entity;
import com.test.server.http.core.Mapping;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class WebHandler extends DefaultHandler{

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
