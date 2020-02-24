package com.test.algorithm.tree;

import java.util.Map;

public class AVLTree {

	public AVLNode root;

	public void add(int val) {
		AVLNode newNode = new AVLNode(val);
		if(root == null) {
			root = newNode;
		} else {
			root.addNext(newNode);
		}
		root.checkAndRotate();
	}

	public AVLNode search(int value) {
		if(root == null) {
			return null;
		} else {
			return root.search(value);
		}
	}

	public AVLNode searchParent(int value) {
		if(root == null) {
			return null;
		} else {
			return root.searchParent(value);
		}
	}

	public void delNode(int value) {
		if(root == null) {
			return;
		} else {
			AVLNode targetNode = search(value);
			if(targetNode == null) {//没有找到要删除的节点
				return;
			}
			if(root.left == null && root.right == null) {//只有根节点
				root = null;
				return;
			}
			AVLNode parent = searchParent(value);
			if(parent == null) {//targetNode是根节点
				if(targetNode.left != null && targetNode.right != null) {//两个子节点
					int minVal = delRightTreeMin(targetNode.right);//删除右子节点的最小值
					targetNode.value = minVal;
				} else {//一个子节点
					if(targetNode.left != null) {//左子节点
						root = targetNode.left;
					} else {
						root = targetNode.right;
					}
				}
			} else {
				if(targetNode.left == null && targetNode.right == null) {
					if(parent.left != null && parent.left.value == value) {//target是parent.left
						parent.left = null;
					} else if(parent.right != null && parent.right.value == value) {//target是parent.right
						parent.right = null;
					}
				} else if(targetNode.left != null && targetNode.right != null) {//两个子节点
					int minVal = delRightTreeMin(targetNode.right);//删除右子节点的最小值
					targetNode.value = minVal;
				} else {//只有一个子节点
					if(targetNode.left != null) {
						if(parent.left.value == value) {
							parent.left = targetNode.left;
						} else {
							parent.right = targetNode.left;
						}
					} else {
						if(parent.left.value == value) {
							parent.left = targetNode.right;
						} else {
							parent.right = targetNode.right;
						}
					}
				}
			}
		}
	}

	//找到以node为根节点,的二叉排序树的最小节点,并删除
	public int delRightTreeMin(AVLNode node) {
		AVLNode target = node;
		while(target.left != null) {
			target = target.left;
		}
		delNode(target.value);
		return target.value;
	}

	public int delLeftTreeMax(AVLNode node) {
		AVLNode target = node;
		while(target.right != null) {
			target = target.right;
		}
		delNode(target.value);
		return target.value;
	}

	public void printInfixOrder() {
		if(root != null) {
			root.infixOrder();
		}
	}

	static class AVLNode {
		int value;
		AVLNode left;
		AVLNode right;

		public AVLNode(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "AVLNode{" +
					"value=" + value +
					'}';
		}

		/**
		 * 左旋转
		 * 在添加节点后 发现高度不符合AVL树规则的则调用
		 */
		public void leftRotate(){
			// 创建新节点
			AVLNode newNode = new AVLNode(value);
			// 新节点的左子树设置为当前节点的左子树
			newNode.left = this.left;
			// 把新的节点的右子树设置成当前节点的右子节点的左子树
			newNode.right = this.right.left;
			// 把当前节点的值替换成右子节点的值
			this.value = this.right.value;
			// 把当前节点的右子树设置成右子树的右子树
			this.right = this.right.right;
			// 把当前节点的左子节点设置成新的节点
			this.left = newNode;
		}
		public void rightRotate(){
			AVLNode newNode = new AVLNode(value);
			newNode.right = this.right;
			newNode.left = this.left.right;
			this.value = this.left.value;
			this.left = this.left.left;
			this.right = newNode;
		}

		public int height() {
			return Math.max(left == null ? 0 : left.height(),right == null ? 0 : right.height()) + 1;
		}

		public int leftHeight() {
			if(left == null) {
				return 0;
			}
			return left.height();
		}

		public int rightHeight() {
			if(right == null) {
				return 0;
			}
			return right.height();
		}


		public void infixOrder() {
			if(this.left != null) {
				this.left.infixOrder();
			}
			System.out.println(this);
			if(this.right != null) {
				this.right.infixOrder();
			}
		}

		public AVLNode searchParent(int value) {
			if((this.left != null && this.left.value == value)
					|| (this.right != null && this.right.value == value)) {
				return this;
			} else {
				if(value < this.value && this.left != null) {
					return this.left.searchParent(value);
				} else if(value >= this.value && this.right != null) {
					return this.right.searchParent(value);
				} else {
					return null;
				}
			}
		}

		public AVLNode search(int value) {
			if(value == this.value) {
				return this;
			} else if(value < this.value) {
				if(this.left == null) {
					return null;
				}
				return this.left.search(value);
			} else {
				if(this.right == null) {
					return null;
				}
				return this.right.search(value);
			}
		}

		public void addNext(AVLNode node) {
			if(node == null) {
				return;
			}
			if(node.value < this.value) {
				if(this.left == null) {
					this.left = node;
				} else {
					this.left.addNext(node);
				}
			} else {
				if(this.right == null) {
					this.right = node;
				} else {
					this.right.addNext(node);
				}
			}
		}

		/**
		 * 添加一个节点后调用此方法
		 */
		public void checkAndRotate(){
			int rightHeight = this.rightHeight();
			int leftHeight = this.leftHeight();
			if(rightHeight - leftHeight > 1){
				if(this.right != null && right.leftHeight() > right.rightHeight()){
					right.rightRotate();
				}
				this.leftRotate();
			}else if(leftHeight - rightHeight > 1){
				if(this.left != null && left.rightHeight() > left.leftHeight()){
					left.leftRotate();
				}
				this.rightRotate();
			}
		}
	}
}
