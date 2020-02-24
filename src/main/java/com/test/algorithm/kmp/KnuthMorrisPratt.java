package com.test.algorithm.kmp;

import java.util.Arrays;

public class KnuthMorrisPratt {
	public static void main(String[] args){
		test02();
	}
	public static void test02(){
		String str1 = "BBC ABCDAB ABCDABCDABDE";
		String str2 = "ABCDABD";

		int[] next = kmpNext("ABCDABD");
		System.out.println("next=" + Arrays.toString(next));

		int ret = kmpSearch(str1, str2, next);
		System.out.println(ret);
	}

	public static void test01(){
		String str1 = "BBC ABCDAB ABCDABCDABDE";
		String str2 = "ABCDABD";

		char[] text = str1.toCharArray();
		char[] pattern = str2.toCharArray();
		int n = pattern.length;
		int[] prefix = new int[n];
//		getPrefixTable(pattern, prefix, n);
//		System.out.println(Arrays.toString(prefix));

	}

	/**
	 * 获得前缀表达式
	 */
	public static void getPrefixTable(char[] pattern, int[] prefix, int n){
		prefix[0] = 0;
		int len = 0;
		int i = 1;
		while(i < n){
			if(pattern[i] == pattern[len]){
				len++;
				prefix[i] = len;
				i++;
			}else{
				if(len > 0){//出界问题
					len = prefix[len - 1];
				}else {
					prefix[i] = len;
					i++;
				}
			}
		}
	}

	/**
	 * 将前缀表每个元素后移,并且第一个元素补为-1
	 * @param prefix
	 * @param n
	 */
	public static void movePrefixTable(int[] prefix, int n){
		for(int i = n - 1; i > 0; i--){
			prefix[i] = prefix[i - 1];
		}
		prefix[0] = -1;
	}

	/**
	 * kmp搜索
	 * @param text
	 * @param parttern
	 */
	public static void kmpSearch(char[] text, char[] parttern){
		int n = parttern.length;
		int[] prefix = new int[n];
		getPrefixTable(parttern, prefix, n);
		movePrefixTable(prefix, n);

		// text.len = m, 指针i
		// parttern.len = n, 指针j
		int m = text.length;
		int i = 0, j = 0;
		while(i < m){
			if(j == n - 1 && text[i] == parttern[j]){
				System.out.printf("找到匹配, 在%d", i - j);
				j = prefix[j];//找到了也要继续找
			}
			if(text[i] == parttern[j]){//如果两个指针相等,i,j后移
				i++;
				j++;
			}else{//不相等时 根据前缀表移动
				j = prefix[j];//prefix往右移动(或者说j往前移动)
				if(j == -1){
					i++;
					j++;
				}
			}
		}
	}


	/**
	 * 获得部分匹配表
	 */
	public static int[] kmpNext(String dest){
		int len = dest.length();
		int[] next = new int[len];
		next[0] = 0;
		for(int i = 1, j = 0; i < len; i++){
			while(j > 0 && dest.charAt(i) != dest.charAt(j)){//如果不匹配则移动j指针 直到相等退出
				j = next[j - 1];
			}
			if(dest.charAt(i) == dest.charAt(j)){//部分匹配
				j++;
			}
			next[i] = j;
		}
		return next;
	}

	/**
	 *
	 * @param str1 源字符串
	 * @param str2 key串
	 * @param next 部分匹配表
	 * @return
	 */
	public static int kmpSearch(String str1, String str2, int[] next){
		int len = str2.length();
		int m = str1.length();
		for(int i = 0, j = 0; i < m; i++){
			while(j > 0 && str1.charAt(i) != str2.charAt(j)){//③利用next表 让j左移
				j = next[j - 1];
			}
			if(str1.charAt(i) == str2.charAt(j)){//①当相等时后移指针
				j++;
			}
			if(j == len){//②找到了
				return i - j + 1;
			}
		}
		return -1;
	}

}
