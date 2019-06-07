package com.test.concurrent.t023;

public class Singleton {
//	private static Singleton instance;//直接new出来的是饿汉模式
	private Singleton(){
		System.out.println("single");
	}
//	//双重锁模式
//	public static Singleton getInstance(){
//		if(instance == null){
//			synchronized(Singleton.class){
//				if(instance == null){
//					instance = new Singleton();
//				}
//			}
//		}
//		return instance;
//	}

	//静态类 实现懒加载 不用加锁
	private static class Inner{
		private static Singleton s = new Singleton();
	}

	public static Singleton getSingleton(){
		return Inner.s;
	}

	public static void main(String[] args){

	}

}
