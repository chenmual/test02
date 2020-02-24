package com.test.algorithm.tree;

public class ThreadedBinaryTree {

	private TBTNode root;

	private TBTNode pre;

	public void setRoot(TBTNode root) {
		this.root = root;
	}

	/**
	 * 当前节点线索化
	 * @param node
	 */
	public void threadedNodes(TBTNode node){
		if(node == null){
			return;
		}
		//左中右线索化
		threadedNodes(node.left);
		if(node.left == null){//将左子节点为空的,指向前驱节点
			node.left = pre;
			node.leftType = 1;
		}
		if(pre != null && pre.right == null){//前驱节点的右空节点指向后续节点,即node
			//前驱节点的右指针指向当前节点
			pre.right = node;
			//修改前驱节点的右指针类型
			pre.rightType = 1;
		}
		pre = node;
		threadedNodes(node.right);
	}

	/**
	 * 中序遍历线索化二叉树
	 */
	public void showThreadListInfix(){
		TBTNode node = root;
		while(node != null){
			while(node.leftType == 0){
				node = node.left;
			}
			//打印当前节点
			System.out.println(node);
			while(node.rightType == 1){
				node = node.right;
				System.out.println(node);
			}
			node = node.right;
		}
	}


	@Deprecated
	public void delNode(int id){
		if(root != null){
			//如果只有一个节点,判断root是否就是要删除的节点
			if(root.id == id){
				root = null;
			}else{
				root.delNode(id);
			}
		}else{
			System.out.println("无法删除, 树为空");
		}
	}

	static class TBTNode{
		private int id;
		private String name;
		private TBTNode left;
		private TBTNode right;

		//0表示指向子树 1表示前驱节点
		private int leftType;
		private int rightType;

		public int getLeftType() {
			return leftType;
		}

		public void setLeftType(int leftType) {
			this.leftType = leftType;
		}

		public int getRightType() {
			return rightType;
		}

		public void setRightType(int rightType) {
			this.rightType = rightType;
		}

		public TBTNode(int id,String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "TBTNode{" +
					"id=" + id +
					", name='" + name + '\'' +
					'}';
		}


		/**
		 * 前序遍历
		 */
		@Deprecated
		public void preOrder(){
			System.out.println(this);//先当前
			if(this.left != null){
				this.left.preOrder();
			}
			if(this.right != null){
				this.right.preOrder();
			}
		}

		/**
		 * 中序遍历
		 */
		@Deprecated
		public void infixOrder(){
			if(this.left != null){
				this.left.infixOrder();
			}
			System.out.println(this);
			if(this.right != null){
				this.right.infixOrder();
			}
		}
		/**
		 * 后序遍历
		 */
		@Deprecated
		public void postOrder(){
			if(this.left != null){
				this.left.postOrder();
			}
			if(this.right != null){
				this.right.postOrder();
			}
			System.out.println(this);
		}

		@Deprecated
		public TBTNode preOrderSearch(int id){
			if(this.id == id){
				return this;
			}
			if(this.left != null){
				TBTNode ret = this.left.preOrderSearch(id);
				if(ret != null){
					return ret;
				}
			}
			if(this.right != null){
				TBTNode ret = this.right.preOrderSearch(id);
				if(ret != null){
					return ret;
				}
			}
			return null;
		}


		@Deprecated
		public TBTNode infixOrderSearch(int id){
			if(this.left != null){
				TBTNode ret = this.left.infixOrderSearch(id);
				if(ret != null){
					return ret;
				}
			}
			if(this.id == id){
				return this;
			}
			if(this.right != null){
				TBTNode ret = this.right.infixOrderSearch(id);
				if(ret != null){
					return ret;
				}
			}
			return null;
		}


		@Deprecated
		public TBTNode postOrderSearch(int id){
			if(this.left != null){
				TBTNode ret = this.left.postOrderSearch(id);
				if(ret != null){
					return ret;
				}
			}
			if(this.right != null){
				TBTNode ret = this.right.postOrderSearch(id);
				if(ret != null){
					return ret;
				}
			}
			if(this.id == id){
				return this;
			}
			return null;
		}

		public void delNode(int id){
			if(this.left != null && this.left.id == id){
				this.left = null;
				return;
			}
			if(this.right != null && this.right.id == id){
				this.right = null;
				return;
			}
			if(this.left != null){
				this.left.delNode(id);
			}
			if(this.right != null){
				this.right.delNode(id);
			}
		}
	}

	public static void test(){
		TBTNode root = new TBTNode(1, "1");
		TBTNode n2 = new TBTNode(3, "3");
		TBTNode n3 = new TBTNode(6, "6");
		TBTNode n4 = new TBTNode(8, "8");
		TBTNode n5 = new TBTNode(10, "10");
		TBTNode n6 = new TBTNode(14, "14");

		ThreadedBinaryTree tree = new ThreadedBinaryTree();
		tree.setRoot(root);

		root.left = n2;
		root.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6;

		tree.threadedNodes(root);

		System.out.println("10号的前驱节=" + n5.left);
		System.out.println("10号的后续节点=" + n5.right);
		tree.showThreadListInfix();
	}
}
