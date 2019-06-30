package com.test.sort;

import com.test.util.NumberUtil;

public class RadixSort {
	public static final int NUMBER_COUNT_PER_BIT = 10;//每一位的数字数量 最多是10个(0~9) 如果是8进制就是8个
	/**
	 * 基数排序
	 * 平均n * k
	 * 最好n * k
	 * 最坏n * k
	 * 空间(n + k)
	 * 稳
	 * 10000次测试长度为4000的数组(随机取值范围0~99999)
	 * 此方法为2秒左右
	 * 按位数一次进行排序(个位,十位,百位...)
	 */
	public static void sort(int[] arr){
		int len = arr.length;
		//---------处理负数---------
		int min = arr[0];
		int max = arr[0];
		//遍历1(n)求出最小值
		for(int i = 1; i < len; i++){
			if(min > arr[i]){
				min = arr[i];
			}
			if(max < arr[i]){
				max = arr[i];
			}
		}
		if(min < 0){
			// 如果最小值小于0则把所有数调整为>=0
			for(int i = 0;i < len; i++){
				arr[i] = arr[i] - min;
			}
			max = max - min;
		}
		if(max <= 0){
			System.out.println("最大最小都是0, 无需排序");
			return;
		}
		//---------处理负数结束---------
		//把最大值的位数计算出来
		int bit = NumberUtil.getBit(max);
		//---------
		//一共bit位 执行bit次
		int[] temp = new int[len];
		for(int i = 0; i < bit; i++){
			int div = (int) Math.pow(10, i);//当前的位数 - 1
			//存储每一位的数量
			int[] countArr = new int[NUMBER_COUNT_PER_BIT];
			//执行len次
			for(int j = 0;j < len; j++){
				int bitnum = (arr[j] / div) % 10;
				countArr[bitnum]++;
			}
			//把count的数量转换成索引,当位数字是0的有5个,当位数字是1的有7个,3的是3个所以索引是0, 5, 12,
			for(int k = 1; k < NUMBER_COUNT_PER_BIT; k++){
				countArr[k] = countArr[k] + countArr[k - 1];
			}
			for(int j = len - 1, k = len; j > -1; j--){//j: len-1 -> 0
				int bitnum = (arr[j] / div) % 10;//算出当前位的数字
				temp[--countArr[bitnum]] = arr[j];//索引为当前位的索引+当前位出现该数字的次数
			}
			for(int j = 0; j < len; j++){
				arr[j] = temp[j];
			}
		}
		//---------之前有负数处理时需要加回去---------
		if(min < 0){
			for(int j = 0; j < len; j++){
				arr[j] = arr[j] + min;
			}
		}
	}
	public static void main(String[] args){
		System.out.println(NumberUtil.getBit(101));
	}
}
