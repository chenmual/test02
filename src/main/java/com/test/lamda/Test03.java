package com.test.lamda;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test03 {
	public static void main(String[] args){
		String[] playerNames = null;
	    String name = "";
	    Set<String> newNameSet = new HashSet<String>();
		playerNames = new String[]{"abc", "a,b", "w,f", "sdf,m", "ppp,few"};
		for(String playerName : playerNames){
			String[] names = playerName.split(",");
			Arrays.stream(names).forEach(System.out::println);
		}
		System.out.println("------");
	    boolean needReturn = false;
		Arrays.stream(playerNames).forEach(playerName -> Arrays.stream(playerName.split(",")).forEach(nameStr -> newNameSet.add(nameStr)));
		System.out.println(newNameSet);
		if(needReturn){
			return;
		}

	    for(String playerName : playerNames){
	    	if(playerName.contains(",")){
	    		String[] names = playerName.split(",");
//				newNameSet.addAll(names);
				Arrays.stream(names).forEach(nameStr -> {newNameSet.add(nameStr);});
			}else{
				newNameSet.add(playerName);
			}
		}

		//------------------
		Arrays.stream(playerNames).forEach(playerName -> {
			if(playerName.contains(",")){
				Arrays.stream(playerName.split(",")).forEach(nameStr -> {newNameSet.add(nameStr);});
			}else{
				newNameSet.add(playerName);
			}
		});

//		Arrays.stream(playerNames).filter(playerName -> playerName.contains(",")).forEach(player);

		for(String playerName : newNameSet){
			name += playerName + ",";
		}
		int index = name.lastIndexOf(",");
		String newName = name.substring(0, index);

//		newNameSet.stream().collect(Collectors.joining(","));
	}
}
