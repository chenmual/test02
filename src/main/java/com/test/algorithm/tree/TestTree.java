package com.test.algorithm.tree;


import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class TestTree {
	public static void main(String[] args){
//		test02();
		test03();
	}

	public static void test03(){
//		int[] arr = {4, 3, 6, 5, 7, 8};
		int[] arr = {10, 11, 7, 6, 8, 9};
		AVLTree avlTree = new AVLTree();
		for(int i = 0; i < arr.length; i++){
			avlTree.add(arr[i]);
		}
		avlTree.printInfixOrder();
		System.out.println(avlTree.root.height());

		System.out.println(avlTree.root.leftHeight());
		System.out.println(avlTree.root.rightHeight());

		System.out.println(avlTree.root);
	}

	public static void test02(){
		int[] a = new int[]{7, 3, 10, 12, 5, 1, 9};
		BinarySortTree tree = new BinarySortTree();
		for(int i = 0; i < a.length; i++){
			tree.add(a[i]);
		}
		tree.printInfixOrder();
		System.out.println("------");
		tree.delNode(7);
		tree.delNode(10);
		tree.delNode(12);
		tree.delNode(5);
		tree.delNode(1);
		tree.delNode(8);
		tree.delNode(12);
		tree.delNode(9);
		tree.delNode(3);
		tree.printInfixOrder();
	}

	public static void test01(){

//	    BinaryTree.test();
//	    ThreadedBinaryTree.test();
//		HuffmanTree.test();

		String str = "i like like like java do you like a java";
		Map<Byte, String> huffmanCodeMap = HuffmanUtil.getCodes(str);
//		System.out.println(map);
		byte[] ret = HuffmanUtil.zip(huffmanCodeMap, str);
//		ret = HuffmanUtil.getCodes(str.getBytes());
		System.out.println("---");
		System.out.println(Arrays.toString(ret));
//		for(int i = 0; i < ret.length; i++){
//			System.out.print(ret[i] + " ");
//		}
//		System.out.println("---");
//		for(int i = 0; i < ret.length; i++){
//			System.out.print(Integer.toString(ret[i], 2));
//		}
		byte[] ret2 = HuffmanUtil.decode(huffmanCodeMap, ret);
		System.out.println(" " + new String(ret2));

		System.out.println("-------");

		String srcFile = "J:\\p\\temp\\a.bmp";
		String dstFile = "J:\\p\\temp\\a.hzr";
		try {
			HuffmanUtil.zipFile(srcFile, dstFile);
		} catch(IOException e) {
			e.printStackTrace();
		}

		String zipFile = dstFile;
		String dstFile2 = "J:\\p\\temp\\a2.bmp";
		try {
			HuffmanUtil.unzipFile(zipFile, dstFile2);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
