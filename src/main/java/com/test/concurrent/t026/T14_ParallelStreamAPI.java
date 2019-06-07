package com.test.concurrent.t026;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 未完善
 */
public class T14_ParallelStreamAPI {
	public static void main(String[] args){
		List<Integer> nums = new ArrayList<>();
		Random r = new Random();
		for(int i = 0; i < 10000; i++){
			nums.add(1000000 + r.nextInt(1000000));
		}
//		System.out.println(nums);

		//使用原始的(单线程)
		long start = System.currentTimeMillis();
		nums.forEach(v -> isPrime(v));
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		//使用Parallel stream api
		start = System.currentTimeMillis();
		nums.parallelStream().forEach(T14_ParallelStreamAPI::isPrime);
		end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	static boolean isPrime(int num){
		int max = num >> 1;
		for(int i = 2; i <= max; i++){
			if(num % i == 0){
				return false;
			}
		}
		return true;
	}
}
