package com.test.algorithm.list;

public class DoubleLinkedList {

	private DNode head;
	private DNode last = head;

	public DoubleLinkedList(){
		head = new DNode("head", 0);
		last = head;
	}

	public void addLast(String name, int val){
		DNode newNode = new DNode(name, val);
		last.next = newNode;
		newNode.pre = last;
		last = last.next;
	}

	public void showList(){
		DNode temp = head;
		while(temp.next != null){
			temp = temp.next;
			System.out.println(temp);
		}
	}

	public boolean isEmpty(){
		if(head == last){
			return true;
		}
		return false;
	}

	public boolean updateByVal(int val, String newName){
		if(isEmpty()){
			System.out.println("修改失败,列表为空");
			return false;
		}
		boolean isFind = false;
		DNode temp = head.next;
		while(true){
			if(temp == null){//遍历完成
				break;
			}
			if(temp.val == val){
				isFind = true;
				break;
			}
			temp = temp.next;
		}
		if(isFind){
			System.out.println("修改成功");
			temp.name = newName;
		}else{
			System.out.println("修改失败,未找到val=" + val + "的节点");
		}
		return isFind;
	}

	public boolean deleteByVal(int val){
		if(isEmpty()){
			System.out.println("删除失败,列表为空");
			return false;
		}
		boolean isFind = false;
		DNode delete = head.next;
		while(true){
			if(delete.val == val){//找到了
				isFind = true;
				break;
			}
			delete = delete.next;
		}
		if(isFind){
			delete.pre.next = delete.next;
			if(delete.next != null){
				delete.next.pre = delete.pre;
			}
			System.out.println("删除成功");
			return true;
		}else{
			System.out.println("删除失败,未找到val=" + val + "的节点");
		}
		return false;

	}


	static class DNode{
		protected int val;
		protected String name;
		protected DNode next;
		protected DNode pre;
		public DNode(String name, int val) {
			this.name = name;
			this.val = val;
		}
		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return "DNode{" +
					"val='" + val + '\'' +
					"name='" + name + '\'' +
					", next=" + (next == null? "null": next.val) +
					", pre=" + (pre == null? "null": pre.val) +
					'}';
		}
	}


	public static void test(){
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
		doubleLinkedList.addLast("111", 1);
		doubleLinkedList.addLast("222", 2);
		doubleLinkedList.addLast("333", 3);
		doubleLinkedList.addLast("444", 4);

		doubleLinkedList.showList();

		doubleLinkedList.updateByVal(3, "3333");
		doubleLinkedList.showList();

		doubleLinkedList.deleteByVal(2);
		doubleLinkedList.deleteByVal(1);
		doubleLinkedList.deleteByVal(4);
		doubleLinkedList.deleteByVal(3);

		doubleLinkedList.showList();
	}
}
