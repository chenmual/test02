package com.test.algorithm.list;

/**
 * 没有头节点的结构
 * 首个元素的id规定为1
 * 容量为init 且不允许init < 2
 */
public class SingleCircleLinkedListWithoutHead {

	private CNode first;

	public SingleCircleLinkedListWithoutHead(int init){
		if(init <= 1){
			throw new RuntimeException("初始化数量必须大于1");
		}
		CNode newNode = new CNode(1);
		first = newNode;
		for(int i = 2; i < init + 1; i++){
			newNode.next =  new CNode(i);
			newNode = newNode.next;
		}
		newNode.next = first;
	}

	public void showList(){
		if(first == null){
			System.out.println("链表为空");
			return;
		}
		CNode temp = first;
		while(true){
			System.out.println(temp);
			if(temp.next == first){//遍历完成
				break;
			}
			temp = temp.next;
		}
	}

	public int getSize(){
		if(first == null){
			System.out.println("链表为空");
			return 0;
		}
		int count  = 0;
		CNode temp = first;
		while(true){
			count++;
			if(temp.next == first){//遍历完成
				break;
			}
			temp = temp.next;
		}
		return count;
	}

	/**
	 * @param start 起始位置 >= 1
	 * @param count
	 */
	public void countOut(int start, int count){
		int size = getSize();
		if(size < 2){
			System.out.println("链表长度不足2");
			return;
		}
		CNode pre = first;//first的前一个
		while(true){
			if(pre.next == first){
				break;
			}
			pre = pre.next;
		}
		//first移动到起始位置
		for(int i = 0; i < start - 1; i++){
			pre = first;
			first = first.next;
		}
		while(true){
			if(pre == first){//此时只有一个元素,可退出循环
				break;
			}
			//需要移动count - 1次
			for(int i = 0; i < count - 1; i++){
				first = first.next;
				pre = pre.next;
			}
			System.out.println(first.id + " is out");
			first = first.next;
			pre.next = first;
		}
		System.out.println(first.id + " is the last!");
	}


	static class CNode{
		private int id;
		private CNode next;

		public CNode(int id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "CPNode{" +
					"id=" + id +
					", next=" + (next == null?"null" :next.id) +
					'}';
		}
	}

	public static void test(){
		SingleCircleLinkedListWithoutHead list = new SingleCircleLinkedListWithoutHead(5);
		list.showList();
		list.countOut(1, 2);
	}
}
