package com.test.algorithm.tree;

public class BinaryTree {

	private BTNode root;

	public void setRoot(BTNode root) {
		this.root = root;
	}

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

	/**
	 * 前序遍历
	 */
	public void printByPreOrder(){
		if(root != null){
			root.preOrder();
		}else{
			System.out.println("无法遍历,二叉树为空");
		}
	}

	public void printByInfixOrder(){
		if(this.root != null){
			root.infixOrder();
		}else{
			System.out.println("无法遍历,二叉树为空");
		}
	}

	public void printByPostOrder(){
		if(this.root != null){
			root.postOrder();
		}else{
			System.out.println("无法遍历,二叉树为空");
		}
	}

	public BTNode preOrderSearchById(int id){
		if(root != null){
			return root.preOrderSearch(id);
		}else{
			System.out.println("无法查找,二叉树为空");
			return null;
		}
	}

	public BTNode infixOrderSearchById(int id){
		if(root != null){
			return root.infixOrderSearch(id);
		}else{
			System.out.println("无法查找,二叉树为空");
			return null;
		}
	}

	public BTNode postOrderSearchById(int id){
		if(root != null){
			return root.postOrderSearch(id);
		}else{
			System.out.println("无法查找,二叉树为空");
			return null;
		}
	}

	/**
	 * 线索化二叉树节点
	 * 除了包含左右节点信息外还包含左右子节点的类型信息(指向子树或者前驱节点)
	 */
	public static class BTNode{
		private int id;
		private String name;
		private BTNode left;
		private BTNode right;

		public BTNode(int id, String name) {
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

		public BTNode getLeft() {
			return left;
		}

		public void setLeft(BTNode left) {
			this.left = left;
		}

		public BTNode getRight() {
			return right;
		}

		public void setRight(BTNode right) {
			this.right = right;
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

		@Override
		public String toString() {
			return "BTNode{" +
					"id=" + id +
					", name='" + name + '\'' +
					'}';
		}

		/**
		 * 前序遍历
		 */
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
		public void postOrder(){
			if(this.left != null){
				this.left.postOrder();
			}
			if(this.right != null){
				this.right.postOrder();
			}
			System.out.println(this);
		}

		public BTNode preOrderSearch(int id){
			if(this.id == id){
				return this;
			}
			if(this.left != null){
				BTNode ret = this.left.preOrderSearch(id);
				if(ret != null){
					return ret;
				}
			}
			if(this.right != null){
				BTNode ret = this.right.preOrderSearch(id);
				if(ret != null){
					return ret;
				}
			}
			return null;
		}


		public BTNode infixOrderSearch(int id){
			if(this.left != null){
				BTNode ret = this.left.infixOrderSearch(id);
				if(ret != null){
					return ret;
				}
			}
			if(this.id == id){
				return this;
			}
			if(this.right != null){
				BTNode ret = this.right.infixOrderSearch(id);
				if(ret != null){
					return ret;
				}
			}
			return null;
		}


		public BTNode postOrderSearch(int id){
			if(this.left != null){
				BTNode ret = this.left.postOrderSearch(id);
				if(ret != null){
					return ret;
				}
			}
			if(this.right != null){
				BTNode ret = this.right.postOrderSearch(id);
				if(ret != null){
					return ret;
				}
			}
			if(this.id == id){
				return this;
			}
			return null;
		}
	}

	public static void test(){
		BinaryTree tree = new BinaryTree();

		BTNode node1 = new BTNode(1, "node1");
		BTNode node2 = new BTNode(2, "node2");
		BTNode node3 = new BTNode(3, "node3");
		BTNode node4 = new BTNode(4, "node4");
		BTNode node5 = new BTNode(5, "node5");
		tree.setRoot(node1);
		node1.setLeft(node2);
		node1.setRight(node3);
		node3.setRight(node4);
		node3.setLeft(node5);

		System.out.println("===前序遍历:");
		tree.printByPreOrder();
		System.out.println("===中序遍历:");
		tree.printByInfixOrder();
		System.out.println("===后序遍历:");
		tree.printByPostOrder();


		System.out.println("===查找:");
		System.out.println(tree.postOrderSearchById(6));
		System.out.println("===删除:");
		tree.delNode(3);
		tree.printByPreOrder();
	}
}
