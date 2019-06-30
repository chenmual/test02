package com.test.algorithm.list;

public class SortedSingleLinkedList {
	VNode head;
	VNode last;
	//允许重复插入 默认为false
	public boolean canSameValue = false;

	public SortedSingleLinkedList(){
		head = new VNode("head", 0);
		last = head;
	}

	public boolean isEmpty(){
		if(null == head.next){
			return true;
		}
		return false;
	}

	/**
	 * 添加
	 * @param name
	 * @param val
	 */
	public void addByVal(String name,int val) {//插入对应位置的前一个位置,初始为head
		VNode temp = head;
		VNode newNode = new VNode(name, val);
		boolean flag = false;
		while(true){
			if(temp.next == null){//没有下一个节点,退出循环
				break;
			}
			if(temp.next.val > newNode.val){//
				break;
			}else if(temp.next.val == newNode.val){
				flag = true;
				temp = temp.next;
				break;
			}else{
				temp = temp.next;
			}
		}
		if(flag && !canSameValue){
			System.out.println("无法加入重复值:" + newNode.toString());
			return;
		}
		newNode.next = temp.next;
		temp.next = newNode;
	}

	/**
	 * 显示列表
	 */
	public void showList(){
		//为空?
		if(isEmpty()){
			System.out.println("链表为空");
			return;
		}
		VNode temp = head.next;
		while(true){
			if(temp == null){
				break;
			}
			System.out.println(temp);
			temp = temp.next;
		}
	}

	/**
	 * 将val为val的节点,设置name=newname
	 * @return true 修改成功
	 */
	public boolean updateVNode(int val,String newName){
		if(isEmpty()){
			System.out.println("修改失败,列表为空");
			return false;
		}
		boolean isFind = false;
		VNode temp = head.next;
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

	public boolean deleteVNode(int val){
		if(isEmpty()){
			System.out.println("删除失败,列表为空");
			return false;
		}
		boolean isFind = false;
		VNode preTemp = head;
		while(true){
			if(preTemp.next == null){//遍历完成,未找到
				break;
			}
			if(preTemp.next.val == val){//找到了
				isFind = true;
				break;
			}
			preTemp = preTemp.next;
		}
		if(isFind){
			preTemp.next = preTemp.next.next;
			System.out.println("删除成功");
			return true;
		}else{
			System.out.println("删除失败,未找到val=" + val + "的节点");
		}
		return false;
	}

	/**
	 * 返回有效节点,不包括头节点
	 * @return
	 */
	public int getLength(){
		if(isEmpty()){
			return 0;
		}
		int count = 0;
		VNode temp = head;
		while(temp.next != null){
			temp = temp.next;
			count++;
		}
		return count;
	}

	/**
	 * 获取倒数第index个元素(从1开始)
	 * @return 返回该元素或者null
	 */
	public VNode getReverseByIndex(int index){
		//第一遍遍历,获取有效长度
		if(isEmpty()){
			System.out.println("无法找到,列表为空");
			return null;
		}
		int len = getLength();
		if(len < index || index < 1){
			System.out.println("无法找到,索引大于列表长度,或小于1");
			return null;
		}
		int needIndex = len - index;
		VNode temp = head;
		int count = 0;
		while(temp.next != null){
			temp = temp.next;
			if(count == needIndex){
				break;
			}
			count++;
		}
		return temp;
	}

	/**
	 * VNode还包含了一个int val;信息 并在初始化时排序插入
	 */
	static class VNode {
		String name;
		VNode next;
		int val;
		VNode(String name,int val){
			this.name = name;
			this.val = val;
		}
		@Override
		public String toString() {
			return "VNode{" +
					"name='" + name + '\'' +
					", val=" + val +
					'}';
		}
	}

	public static void testList(){
		SortedSingleLinkedList list = new SortedSingleLinkedList();
		System.out.println("此时有" + list.getLength() + "个节点");
		list.addByVal("333", 3);
		list.addByVal("222", 2);
		list.addByVal("111", 1);
		list.addByVal("444", 4);
		list.addByVal("666", 6);
		list.addByVal("555", 5);
		list.addByVal("444", 4);
		System.out.println("此时有" + list.getLength() + "个节点");
		list.showList();
		if(list.updateVNode(6, "6666")){
			list.showList();
		}
		int idx = -1;
		System.out.println("查找倒叙第" + idx + "个元素");
		System.out.println(list.getReverseByIndex(idx));
//		if(list.deleteVNode(5)){
//			list.showList();
//		}
//		if(list.deleteVNode(4)){
//			list.showList();
//		}
//		if(list.deleteVNode(1)){
//			list.showList();
//		}
//		if(list.deleteVNode(7)){
//			list.showList();
//		}
//		if(list.deleteVNode(6)){
//			list.showList();
//		}
//		if(list.deleteVNode(2)){
//			list.showList();
//		}
//		if(list.deleteVNode(3)){
//			list.showList();
//		}
	}
}
