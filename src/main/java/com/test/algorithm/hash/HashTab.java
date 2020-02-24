package com.test.algorithm.hash;

import com.test.algorithm.list.SingleLinkedList;

public class HashTab {
	public static final int BCount = 16;
	SingleLinkedList[] listArray;
	public int size;

	public int size(){
		return size;
	}
	public HashTab(){
		listArray = new SingleLinkedList[BCount];
		for(int i = 0; i < BCount; i++){
			listArray[i] = new SingleLinkedList();
		}
		size = 0;
	}

	public void add(String str){
		int mod = calcModByStr(str);
		listArray[mod].add(str);
		size++;
	}

	private int calcModByStr(String str){
		int mod = str.hashCode() % BCount;
		return mod;
	}

	public SingleLinkedList.ENode searchByString(String str){
		int mod = calcModByStr(str);
		return listArray[mod].getNodeByString(str);
	}

	public void print(){
		for(int i = 0 ; i < BCount; i++){
			System.out.println("链表" + (i + 1) + ":");
			listArray[i].showList();
		}
	}

	public static void main(String[] args){
		HashTab hashtable = new HashTab();
		hashtable.add("张三");
		hashtable.add("李四");
		hashtable.add("王五");
		hashtable.add("赵六");
		hashtable.print();
		SingleLinkedList.ENode node = hashtable.searchByString("张四");
		if(node == null){
			System.out.println("没有找到该元素");
		}else{
			System.out.println(node);
		}
	}

}
