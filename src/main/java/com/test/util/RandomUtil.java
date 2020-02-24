package com.test.util;

import com.test.sort.RadixSort;

import java.util.Arrays;
import java.util.Random;

public class RandomUtil {
	public static Random s_rnd = null;
	static{
		s_rnd = new Random();
	}

	/**
	 * 生成(可重复的)随机数,放入数组
	 * @param targetArray
	 * @param min
	 * @param max
	 */
	public static void putRandomNumberIntoArray(int[] targetArray, int min, int max){
		if(targetArray == null){
			System.out.println("生成失败,targetArray不能为null");
			return;
		}
		if(min > max){
			System.out.println("生成失败,min不能>max");
			return;
		}
		for(int i = targetArray.length - 1; i > -1; i--){
			targetArray[i] = s_rnd.nextInt(max - min + 1) + min;
		}
	}

	/**
	 * 从数组中随机取N个(不重复的)数
	 * @param src
	 * @param m
	 * @param copy true:使用一个(新)拷贝数组,原数组不会改变;false:原数组会改变
	 * @return
	 */
	public static int[] getRandomArrayFromSourceArray(int[] src, int m, boolean copy){
		int[] arr = src;
		int len = arr.length;
		if(copy){
			arr = Arrays.copyOf(arr, len);
		}
		if(len < m){
			System.out.println("无法从长度为" + len + "的数组中随机抽取" + m + "个不重复的数");
			return null;
		}
		int[] pickedArray = new int[m];
		int count = 0;
		int temp = 0;
		for(int i = len - 1,index = s_rnd.nextInt(len); i > -1; index = s_rnd.nextInt(i--)){
//			System.out.println("抽出index=" + index);
			temp = arr[index];
			arr[index] = arr[i];
			arr[i] = temp;
			pickedArray[count] = temp;
			count++;
			if(count == m){
				break;
			}
		}
		return pickedArray;
	}

	/**
	 * 将数组元素随机洗牌(随机调换位置)
	 * @param array
	 */
	public static void shuffleArray(int[] array){
		int len = array.length;
		int temp = 0;
		for(int i = len - 1, rindex = s_rnd.nextInt(len); i > 0; rindex = s_rnd.nextInt(i--)){
			temp = array[rindex];
			array[rindex] = array[i];
			array[i] = temp;
		}
	}

	public static void main(String[] args){
		int[] count = new int[5];
		for(int m = 0; m < 100; m++){
			int[] arr = new int[5];
//			putRandomNumberIntoArray(arr, -3, 5);
			for(int i = 0; i < arr.length; i++){
				arr[i] = i;
//				System.out.println(arr[i]);
			}
//			RadixSort.sort(arr);
//			System.out.println("---");
//			int[] ret = getRandomArrayFromSourceArray(arr, 1, false);
			shuffleArray(arr);
			for(int i = 0; i < arr.length; i++){
//				System.out.println(arr[i]);
				if(i == 0){
					count[arr[i]]++;
				}
			}
//			System.out.println("-------");
//			for(int i = 0; i < arr.length; i++){
//				System.out.println(arr[i]);
//			}
		}

		System.out.println("=============");
		for(int i = 0; i < count.length; i++){
			System.out.println(count[i]);
		}
	}
}
