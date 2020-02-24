package com.test.jvm;

public class MyTest6 {
	public static void main(String[] args){
	    Fruit ap = new Apple();
	    Fruit or = new Orange();
	    ap.test();
		or.test();

		ap = new Orange();
		ap.test();
	}
}
class Fruit{
	public void test(){
		System.out.println("F");
	}
}
class Apple extends Fruit{
	@Override
	public void test() {
		System.out.println("A");
	}
}
class Orange extends Fruit{
	@Override
	public void test() {
		System.out.println("O");
	}
}