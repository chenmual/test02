package com.test.algorithm.greedy;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 广播台覆盖问题 - 集合覆盖问题
 * 选择最少的广播台,覆盖所有(最多的)地区
 *
 * 广播台	覆盖
 * K1		北京,上海,天津
 * K2		广州,北京,深圳
 * K3		成都,上海,杭州
 * K4		上海,天津
 * K5		杭州,大连
 */
public class BroadCast {

	public static void main(String[] args){
		HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
		HashSet<String> hashset1 = new HashSet<>();
		hashset1.add("北京");
		hashset1.add("上海");
		hashset1.add("天津");

		HashSet<String> hashset2 = new HashSet<>();
		hashset2.add("广州");
		hashset2.add("北京");
		hashset2.add("深圳");

		HashSet<String> hashset3 = new HashSet<>();
		hashset3.add("成都");
		hashset3.add("上海");
		hashset3.add("杭州");

		HashSet<String> hashset4 = new HashSet<>();
		hashset4.add("杭州");
		hashset4.add("上海");


		HashSet<String> hashset5 = new HashSet<>();
		hashset5.add("天津");
		hashset5.add("大连");

		broadcasts.put("K1", hashset1);
		broadcasts.put("K2", hashset2);
		broadcasts.put("K3", hashset3);
		broadcasts.put("K4", hashset4);
		broadcasts.put("K5", hashset5);

		HashSet<String> allAreas = new HashSet<>();
		allAreas.addAll(hashset1);
		allAreas.addAll(hashset2);
		allAreas.addAll(hashset3);
		allAreas.addAll(hashset4);
		allAreas.addAll(hashset5);
		System.out.println(allAreas);

		HashSet<String> selectKeys = new HashSet<>();

		HashSet<String> tmpSet = new HashSet<>();
		String maxKey = null;
		int maxCount = 0;
		while(allAreas.size() != 0){
			maxKey = null;
			maxCount = 0;
			for(String key : broadcasts.keySet()){
				HashSet<String> currentKeyAreas = broadcasts.get(key);
				tmpSet.clear();
				tmpSet.addAll(currentKeyAreas);
				tmpSet.retainAll(allAreas);//tmpSet被重新定义为currentKeyAreas与allArea的交集
				int size = tmpSet.size();
//				if(tmpSet.size() > 0 && (maxKey == null || tmpSet.size() > broadcasts.get(maxKey).size())){
//				}
				if(size > 0 && size > maxCount){
					maxCount = size;
					maxKey = key;
				}
			}
			if(maxKey != null){
				selectKeys.add(maxKey);
				allAreas.removeAll(broadcasts.get(maxKey));
			}
		}

		System.out.println(selectKeys);
	}
}
