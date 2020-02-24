package com.test.algorithm.tree;

public class BinarySortTree {

	public BSTNode root;

	public void add(int val){
		BSTNode newNode = new BSTNode(val);
		if(root == null){
			root = newNode;
		}else{
			root.addNext(newNode);
		}
	}

	public BSTNode search(int value){
		if(root == null){
			return null;
		}else{
			return root.search(value);
		}
	}

	public BSTNode searchParent(int value){
		if(root == null){
			return null;
		}else{
			return root.searchParent(value);
		}
	}

	public void delNode(int value){
		if(root == null){
			return;
		}else{
			BSTNode targetNode = search(value);
			if(targetNode == null){//没有找到要删除的节点
				return;
			}
			if(root.left == null && root.right == null){//只有根节点
				root = null;
				return;
			}
			BSTNode parent = searchParent(value);
			if(parent == null){//targetNode是根节点
				if(targetNode.left != null && targetNode.right != null){//两个子节点
					int minVal = delRightTreeMin(targetNode.right);//删除右子节点的最小值
					targetNode.value = minVal;
				}else{//一个子节点
					if(targetNode.left != null){//左子节点
						root = targetNode.left;
					}else{
						root = targetNode.right;
					}
				}
			}else{
				if(targetNode.left == null && targetNode.right == null){
					if(parent.left != null && parent.left.value == value){//target是parent.left
						parent.left = null;
					}else if(parent.right != null && parent.right.value == value){//target是parent.right
						parent.right = null;
					}
				}else if(targetNode.left != null && targetNode.right != null){//两个子节点
					int minVal = delRightTreeMin(targetNode.right);//删除右子节点的最小值
					targetNode.value = minVal;
				} else{//只有一个子节点
					if(targetNode.left != null){
						if(parent.left.value == value){
							parent.left = targetNode.left;
						}else{
							parent.right = targetNode.left;
						}
					}else{
						if(parent.left.value == value){
							parent.left = targetNode.right;
						}else{
							parent.right = targetNode.right;
						}
					}
				}
			}
		}
	}

	//找到以node为根节点,的二叉排序树的最小节点,并删除
	public int delRightTreeMin(BSTNode node){
		BSTNode target = node;
		while(target.left != null){
			target = target.left;
		}
		delNode(target.value);
		return target.value;
	}

	public int delLeftTreeMax(BSTNode node){
		BSTNode target = node;
		while(target.right != null){
			target = target.right;
		}
		delNode(target.value);
		return target.value;
	}

	public void printInfixOrder(){
		if(root != null){
			root.infixOrder();
		}
	}

	static class BSTNode{
		int value;
		BSTNode left;
		BSTNode right;

		public BSTNode(int value) {
			this.value = value;
		}

		public void addNext(BSTNode node){
			if(node == null){
				return;
			}
			if(node.value < this.value){
				if(this.left == null){
					this.left = node;
				}else{
					this.left.addNext(node);
				}
			}else{
				if(this.right == null){
					this.right = node;
				}else{
					this.right.addNext(node);
				}
			}
		}

		@Override
		public String toString() {
			return "BSTNode{" +
					"value=" + value +
					'}';
		}

		public void infixOrder(){
			if(this.left != null){
				this.left.infixOrder();
			}
			System.out.println(this);
			if(this.right != null){
				this.right.infixOrder();
			}
		}

		public BSTNode searchParent(int value){
			if((this.left != null && this.left.value == value)
					|| (this.right != null && this.right.value == value)){
				return this;
			}else{
				if(value < this.value && this.left != null){
					return this.left.searchParent(value);
				}else if(value >= this.value && this.right != null){
					return this.right.searchParent(value);
				}else{
					return null;
				}
			}
		}

		public BSTNode search(int value){
			if(value == this.value){
				return this;
			}else if(value < this.value){
				if(this.left == null){
					return null;
				}
				return this.left.search(value);
			}else{
				if(this.right == null){
					return null;
				}
				return this.right.search(value);
			}
		}
	}

}

