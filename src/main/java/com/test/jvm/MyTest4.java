package com.test.jvm;

public class MyTest4 {
	public static void test(){
		System.out.println("test invoked");
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratorFiles", "true");
	}
	public static void main(String[] args){
	    test();
	}
}
