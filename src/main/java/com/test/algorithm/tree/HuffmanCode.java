package com.test.algorithm.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCode {
	final private byte[] bytes;
	final private HuffmanTree tree;
	final private Map<Byte, String> huffmanMap;

	public HuffmanCode(byte[] bytes){
		int len = bytes.length;
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

		this.tree = new HuffmanTree(nodes);

		this.huffmanMap = HuffmanUtil.getCodes(tree);

		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < bytes.length; i++){
			stringBuilder.append(huffmanMap.get(bytes[i]));
		}
		int strLen = stringBuilder.length();
		int zipLen = (strLen + 7) >> 3;
		byte[] retBytes = new byte[zipLen];
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

		this.bytes = retBytes;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public HuffmanTree getTree() {
		return tree;
	}

	public Map<Byte, String> getHuffmanMap() {
		return huffmanMap;
	}
}
