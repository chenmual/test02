package com.test.algorithm.list;

import java.util.Random;

/**
 * 约瑟夫(Josephu)环加强版
 * 头节点是额外的节点.每次固定(除非删除head.next)
 * 首个元素的id规定为0
 * 容量为init 初始容量至少为1
 * 允许最后减到0
 */
public class SingleCircleLinkedListPlus {
	/**
	 * 在第一个节点之前的一个节点
	 * 当head.next == null时 列表为空
	 */
	private CPNode head;

	/**
	 * 初始化几个节点
	 * @param init
	 */
	public SingleCircleLinkedListPlus(int init, int maxRnd){
		if(init < 1){
			throw new RuntimeException("初始化节点不能小于1");
		}
//		head = new CPNode(-1, -1);
		head = new CPNode(-1);
		CPNode cur = head;
		for(int i = 0; i < init; i++){
			addNode(new Random().nextInt(maxRnd) + 1, cur, i);
			cur = cur.next;
		}
	}
	private boolean addNode(int no, CPNode node, int index){
//		CPNode newNode = new CPNode(no, index);
		CPNode newNode = new CPNode(no);
		node.next = newNode;
		newNode.next = head.next;
		return true;
	}

	public void showList(){
		if(head.next == null){
			System.out.println("链表为空");
			return;
		}
		CPNode temp = head.next;
		System.out.println(temp.toString2());
		temp = temp.next;
		while(temp != head.next){
			System.out.println(temp.toString2());
			temp = temp.next;
		}
	}

	/**
	 *
	 * @param startNode 传入起始节点,随着程序进行后移动
	 * @param count 往后移动count - 1后删除
	 * @return
	 */
	public CPNode delete(CPNode startNode,int count,CPNode head){
		if(startNode.next == startNode){
			//删除最后一个
			head.next = null;
			return null;
		}

		//要删除节点的前一个
		CPNode pre = null;
		if(count == 1){//如果只需要报1的话,需要走一圈才能计算startNode的pre
			CPNode temp = startNode;
			do {
				pre = temp;
				temp = temp.next;
			}while(temp != startNode);
			pre.next = startNode.next;
		}else{
			//移动count - 1次
			while(--count > 0){
				pre = startNode;
				startNode = startNode.next;
			}
			pre.next = startNode.next;
		}
		if(head.next == startNode){
			head.next = startNode.next;
		}
		head.id = startNode.id;
		System.out.println(startNode.id + " out");
		return pre.next;
	}

	static class CPNode {
		private int id;
//		private int index;
		private CPNode next;
		public CPNode(int id){
			this.id = id;
		}
//		public CPNode(int id, int index){
//			this.id = id;
//			this.index = index;
//		}

		@Override
		public String toString() {
			return "CPNode{" +
					"id='" + id + '\'' +
//					"index='" + index + '\'' +
//					"next='" + (next == null? "null": next.index) +
					"next='" + (next == null? "null": next.id) +
					'}';
		}

		public String toString2() {
			return "id='" + id + '\'' +
//			"index='" + index + '\'' +
					"";
		}
	}

	public static void test(){
		startCount(20, 1, 10, 9);
	}

	public static void startCount(int max, int startIndex, int delCount, int rndMax){
		if(startIndex < 1){
			System.out.println("起始报数人序号需要大于1");
			return;
		}
		if(rndMax < 1){
			System.out.println("无法报从0或0以下的数");
			return;
		}
		SingleCircleLinkedListPlus list = new SingleCircleLinkedListPlus(max, rndMax);
		list.showList();
		list.head.id = delCount;
		CPNode first = list.head.next;
		while(startIndex > 1){
			first = first.next;
			startIndex--;
		}
		while(first != null){
			System.out.println("-----开始删除");
			first = list.delete(first, list.head.id, list.head);
//			list.showList();
			System.out.println("first:" + (first==null?"null" : first.toString2()));
		}
	}
}
