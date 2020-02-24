package com.test.server.http.core;

import com.test.server.http.core.Entity;
import com.test.server.http.core.Mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebContent {
	private List<Entity> entities = null;
	private List<Mapping> mappings = null;

	//key ---> servlet-name value ---> servlet-class
	private Map<String, String> entityMap = new HashMap<>();
	//key ---> url-pattern value ---> servlet-name
	private Map<String, String> mappingMap = new HashMap<>();

	public WebContent(List<Entity> entities,List<Mapping> mappings) {
		this.entities = entities;
		this.mappings = mappings;

		for(Entity entity : entities){
			entityMap.put(entity.getName(), entity.getClz());
		}

		for(Mapping mapping : mappings){
			for(String pattern : mapping.getPatterns()){
				mappingMap.put(pattern, mapping.getName());
			}
		}
	}

	//通过url找到对应路径
	public String getClz(String pattern){
		String name = mappingMap.get(pattern);
		return entityMap.get(name);
	}
}
