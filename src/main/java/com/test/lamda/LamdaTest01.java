package com.test.lamda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class LamdaTest01 {
	public static void main(String[] args){
		Arrays.sort(new String[]{"abc", "bcd"}, String::compareTo);
//		Test01::getName
	}
}
