package com.test.algorithm.list;

import java.util.Stack;

/**
 * 默认插入顺序
 */
public class SingleLinkedList {
	ENode head;
	ENode last;

	public SingleLinkedList(){
		head = new ENode("head");
		last = head;
	}

	public boolean isEmpty(){
		if(null == head.next){
			return true;
		}
		return false;
	}

	public void showList(){
		if(isEmpty()){
			System.out.println("链表为空");
			return;
		}
		ENode temp = head.next;
		while(true){
			if(temp == null){
				break;
			}
			System.out.println(temp);
			temp = temp.next;
		}
	}

	public ENode getNodeByString(String str){
		ENode temp = head.next;
		while(true){
			if(temp == null){
				break;
			}
			if(temp.name.equals(str)){
				return temp;
			}
			temp = temp.next;
		}
		return null;
	}

	public static class ENode{
		protected String name;
		protected ENode next = null;
		public String getName() {
			return name;
		}
		protected ENode(String name){
			this.name = name;
		}
		@Override
		public String toString() {
			return name;
		}
	}

	public void add(String name){
		ENode newNode = new ENode(name);
		last.next = newNode;
		last = newNode;
	}

	public static void testList(){
		SingleLinkedList list = new SingleLinkedList();
		list.add("111");
		list.add("222");
		list.add("333");
		list.add("444");
		list.showList();
		System.out.println("--------倒序打印--------");
		reversePrint(list);

		System.out.println("--------链表倒序--------");
		reverseNode(list);
		list.showList();
	}

	public static void reverseNode(SingleLinkedList oldList){
		if(oldList.isEmpty()){
			System.out.println("列表为空");
			return;
		}
//		SingleLinkedList newlist = new SingleLinkedList();
		ENode reverseHead = new ENode("reverse");
		ENode insert = oldList.head.next;
//		while(insert != null){
//			ENode oldNext = insert.next;//保存要插入的节点的下一个节点(防止遍历oldList断掉)
//			insert.next = newlist.head.next;//插入节点的下一个节点接到newList的的下一个
//			newlist.head.next = insert;//新链表的第一个有效节点设置为insert节点
//			insert = oldNext;
//		}
		while(insert != null){
			ENode oldNext = insert.next;
			insert.next = reverseHead.next;
			reverseHead.next = insert;
			insert = oldNext;
		}
		oldList.head.next = reverseHead.next;
	}

	public static void reversePrint(SingleLinkedList list){
		if(list.isEmpty()){
			System.out.println("链表为空");
			return;
		}
		Stack<ENode> stack = new Stack<>();
		ENode temp = list.head;
		while(temp.next != null){
			temp = temp.next;
			stack.push(temp);
		}
		while(stack.size() > 0){
			System.out.println(stack.pop());
		}
	}
}
