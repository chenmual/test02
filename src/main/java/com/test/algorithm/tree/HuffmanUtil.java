package com.test.algorithm.tree;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanUtil {

	public static byte[] zip(Map<Byte, String> huffmancodeMap, String str){

		byte[] oldBytes = str.getBytes();
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < oldBytes.length; i++){
			stringBuilder.append(huffmancodeMap.get(oldBytes[i]));
		}
		int strLen = stringBuilder.length();
		int len = (strLen + 7) >> 3;
		byte[] retBytes = new byte[len];
		int index = 0;
		for(int i = 0; i < strLen; i += 8){
			String strByte;
			if(i + 8 > strLen){
				strByte = stringBuilder.substring(i);
			}else{
				strByte = stringBuilder.substring(i, i + 8);
			}
			retBytes[index] = (byte)Integer.parseInt(strByte, 2);
			index++;
		}
//		System.out.println(stringBuilder.toString());
		return retBytes;
	}

	public static Map<Byte, String> getCodes(String str){
		HuffmanTree tree = transform(str);
		return getCodes(tree);
	}

	public static HuffmanCode getCodes(byte[] bytes){
		return new HuffmanCode(bytes);
	}

	/**
	 * @param tree 输入树
	 */
	public static Map<Byte, String> getCodes(HuffmanTree tree){
		Map<Byte, String> resultMap = new HashMap<>();
		getCodes(tree.root, new StringBuilder(), resultMap);
		return resultMap;
	}
	/**
	 * 节点->编码字符串(生成路径字符串0101...到builder中)
	 * 并将该byte, 0101..键值对信息存入map中
	 */
	public static void getCodes(HFMNode node, StringBuilder builder, Map<Byte, String> resultMap){
		if(node != null){
			if(node.data == null){
				getCodes(node.left, builder.append("0"), resultMap);
				builder.deleteCharAt(builder.length() - 1);
				getCodes(node.right, builder.append("1"), resultMap);
				builder.deleteCharAt(builder.length() - 1);
			}else{
				resultMap.put((Byte) node.data, builder.toString());
			}
		}
	}

	public static HuffmanTree transform(String str){
		if(str == null){
			return null;
		}
		byte[] bytes = str.getBytes();
		int len = bytes.length;
//		System.out.println(len);

		//每个节点信息value = byte, data = byte出现的次数
		List<HFMNode<Byte>> nodes = new ArrayList<>();

		//统计byte出现的次数 -> map[byte, value]
		Map<Byte, Integer> counts = new HashMap<>();
		for(int i = 0; i < len; i++){
			byte b = bytes[i];
			Integer integer = counts.get(b);
			if(integer == null){
				counts.put(b, 1);
			}else{
				counts.put(b, integer + 1);
			}
		}

		for(Map.Entry<Byte, Integer> entry : counts.entrySet()){
			nodes.add(new HFMNode<>(entry.getValue(), entry.getKey()));
		}

		HuffmanTree tree = new HuffmanTree(nodes);
		return tree;
	}

	/**
	 * 将一个byte转换成一个由010101组成的String
	 * @return
	 */
	public static String byteToBinaryString(boolean flag, byte b){
		int temp = b;
		if(flag){
			temp |= 256;
		}
		String str = Integer.toBinaryString(temp);
		if(flag){
			return str.substring(str.length() - 8);
		}else{
			return str;
		}
	}

	public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes){
		StringBuilder stringBuilder = new StringBuilder();
		int oldLen = huffmanBytes.length;
		for(int i = 0; i < oldLen; i++){
			byte b = huffmanBytes[i];
			boolean needFillHigh = (i == oldLen - 1);
			stringBuilder.append(byteToBinaryString(!needFillHigh, b));
		}
//		System.out.println(stringBuilder.toString());

//		Map<Byte, String> huffmanCodes = new HashMap<>();
		Map<String, Byte> map = new HashMap<>();
		for(Map.Entry<Byte, String> entry : huffmanCodes.entrySet()){
			map.put(entry.getValue(), entry.getKey());
		}
		int len = stringBuilder.length();
		List<Byte> list = new ArrayList<>();//结果列表 存放所有字符串的byte
		for(int i = 0; i < len;){
			int count = 1;
			Byte b = null;
			boolean flag = true;
			while(flag){
				String key = stringBuilder.substring(i, i + count);
				b = map.get(key);
				if(b == null){
					count++;
				}else{
					flag = false;
				}
			}
			list.add(b);
			i += count;
		}
		byte[] retbyte = new byte[list.size()];
		for(int i = 0; i < retbyte.length; i++){
			retbyte[i] = list.get(i);
		}
		return retbyte;
	}


	public static void zipFile(String srcPath, String dstPath) throws IOException {
		FileInputStream fis = null;
		OutputStream os = null;
		ObjectOutputStream oos = null;
		try {
			fis = new FileInputStream(srcPath);
			byte[] readbytes = new byte[fis.available()];
			fis.read(readbytes);
			HuffmanCode huffmanCode = getCodes(readbytes);
			os = new FileOutputStream(dstPath);
			oos = new ObjectOutputStream(os);
			oos.writeObject(huffmanCode.getBytes());
			oos.writeObject(huffmanCode.getHuffmanMap());
//			oos.flush();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(fis != null){
				fis.close();
			}
			if(oos != null){
				oos.close();
			}
			if(os != null){
				os.close();
			}
		}
	}

	public static void unzipFile(String zipFile, String dstFile) throws IOException {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		OutputStream os = null;
		try {
			fis = new FileInputStream(zipFile);
			ois = new ObjectInputStream(fis);
			byte[] readBytes = (byte[])ois.readObject();
			Map<Byte, String> codeMap = (Map<Byte, String>)ois.readObject();

			byte[] retBytes = decode(codeMap, readBytes);

			os = new FileOutputStream(dstFile);
			os.write(retBytes);
//			os.flush();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(fis != null){
				fis.close();
			}
			if(ois != null){
				ois.close();
			}
			if(os != null){
				os.close();
			}
		}

	}
}
