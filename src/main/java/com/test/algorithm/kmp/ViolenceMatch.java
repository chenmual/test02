package com.test.algorithm.kmp;

/**
 * 暴力匹配
 *
 * 效率低
 * 遍历str1,str2的字符
 * 如果匹配成功str1[i] == str2[i], 则i++; j++;
 * 匹配失败时,i回溯,j置0. i = i - (j - 1); j = 0;
 * 算法问题.需要大量回溯
 */
public class ViolenceMatch {
	public static void main(String[] args){
		String str1 = "尚硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
		String str2 = "尚硅谷你尚硅你";
		int ret = violenceMatch(str1, str2);
		System.out.println(ret);
	}
	public static int violenceMatch(String str1, String str2){
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int s1Len = s1.length;
		int s2Len = s2.length;

		int i = 0;int j = 0;
		while(i < s1Len && j < s2Len){//保证不越界
			if(s1[i] == s2[j]){
				i++;
				j++;
			}else{
				i = i - (j - 1);
				j = 0;
			}
		}
		if(j == s2.length){
			return i - j;
		}else{
			return -1;
		}
	}
}
