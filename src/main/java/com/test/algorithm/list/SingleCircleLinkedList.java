package com.test.algorithm.list;

/**
 * 单向循环链表, 约瑟夫(Josephu)环
 * 头节点是额外的节点.每次固定(除非删除head.next)
 * 首个元素的id规定为0
 * 容量为init 初始容量至少为1
 * 允许最后减到0
 */
public class SingleCircleLinkedList {
	/**
	 * 在第一个节点之前的一个节点
	 * 当head.next == null时 列表为空
	 */
	private CNode head;

	/**
	 * 初始化几个节点
	 * @param init
	 */
	public SingleCircleLinkedList(int init){
		if(init < 1){
			throw new RuntimeException("初始化节点不能小于1");
		}
		head = new CNode(-1);
		CNode cur = head;
		for(int i = 0; i < init; i++){
			addNode(i, cur);
			cur = cur.next;
		}
	}
	private boolean addNode(int no, CNode node){
		CNode newNode = new CNode(no);
		node.next = newNode;
		newNode.next = head.next;
		return true;
	}

	public void showList(){
		if(head.next == null){
			System.out.println("链表为空");
			return;
		}
		CNode temp = head.next;
		System.out.println(temp);
		temp = temp.next;
		while(temp != head.next){
			System.out.println(temp);
			temp = temp.next;
		}
	}

	/**
	 *
	 * @param startNode 传入起始节点,随着程序进行后移动
	 * @param count 往后移动count - 1后删除
	 * @return
	 */
	public CNode delete(CNode startNode,int count){
		if(startNode.next == startNode){
			//删除最后一个
			head.next = null;
			return null;
		}

		//要删除节点的前一个
		CNode pre = null;
		//移动count - 1次
		while(--count > 0){
			pre = startNode;
			startNode = startNode.next;
		}
		pre.next = startNode.next;
		if(head.next == startNode){
			head.next = startNode.next;
		}
		System.out.println(startNode.id + " out");
		return pre.next;
	}

	static class CNode {
		private int id;
		private CNode next;
		public CNode(int id){
			this.id = id;
		}

		@Override
		public String toString() {
			return "CPNode{" +
					"id='" + id + '\'' +
					"next='" + (next == null? "null": next.id) +
					'}';
		}
	}

	public static void test(){
		SingleCircleLinkedList list = new SingleCircleLinkedList(5);
		list.showList();
		int needDelete = 2;
		CNode first = list.head.next;
		while(first != null){
			System.out.println("-----开始删除");
			first = list.delete(first, needDelete);
			list.showList();
			System.out.println("first:" + first);
		}
	}
}
