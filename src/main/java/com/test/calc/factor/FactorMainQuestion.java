package com.test.calc.factor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FactorMainQuestion{
	private String title;

	private List<FactorParam> paramList = new ArrayList<>(16);

	public FactorMainQuestion(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public FactorMainQuestion addParam(FactorParam param){
		this.paramList.add(param);
		return this;
	}

	public void calc(){
		sort();
		for(int i = paramList.size() - 1; i > -1; i--){
			paramList.get(i).calc();
		}

		List<String> collect = paramList.stream().filter(factor -> factor.getConditionIndex() > -1)
				.sorted(Comparator.comparing(FactorParam::getConditionIndex))
				.map(FactorParam::getResult)
				.collect(Collectors.toList());
		String[] strArray = new String[collect.size()];
		collect.toArray(strArray);

		System.out.println(String.format(title, strArray));
	}

	public void sort(){
		this.paramList.sort((o1, o2) -> o1.getFactorIndex() < o2.getFactorIndex()? -1: 1);
	}

	public FactorParam getParamByIndex(int index){
		return paramList.get(index);
	}

	public String returnResult(){
		String collect = paramList.stream().filter(factor -> factor.getResultIndex() > -1)
				.sorted(Comparator.comparing(FactorParam::getResultIndex))
				.map(FactorParam::getResult)
				.collect(Collectors.joining(","));
		return collect;
	}
}
