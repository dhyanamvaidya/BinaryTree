/**
 * 
 */
package binaryTree;

import java.util.HashMap;

/**
 * @author dhyanamvaidya
 * Binary Tree is a node-based binary tree data structure which has the following properties:
 *  -- Each node can contain left and right child tree nodes
 *  -- Both the left and right subtrees must also be binary trees 
 *  -- It can be used (extended) as a Binary Search Tree where the left child of a node is lesser in
 *  	value than the root and right child is greater in value than the root
 */
public class BinaryTree<V> {
	
	private V value;
	private BinaryTree<V> leftChild;
	private BinaryTree<V> rightChild;
	
	/**
	 * @return the value
	 */
	public V getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(V value) {
		this.value = value;
	}

	/**
	 * @return the leftChild
	 */
	public BinaryTree<V> getLeftChild() {
		return leftChild;
	}

	/**
	 * @param leftChild the leftChild to set
	 */
	public void setLeftChild(BinaryTree<V> leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * @return the rightChild
	 */
	public BinaryTree<V> getRightChild() {
		return rightChild;
	}

	/**
	 * @param rightChild the rightChild to set
	 */
	public void setRightChild(BinaryTree<V> rightChild) {
		this.rightChild = rightChild;
	}
	
	public BinaryTree(V value) {
		this.value = value;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	public BinaryTree(V value, BinaryTree<V> leftChild, BinaryTree<V> rightChild) {
		this.value = value;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	/**
	 * Returns the number of nodes including the root in the Binary Tree rooted at this node
	 * @return
	 */
	public int size() {
		if(this == null)
			return 0;
		
		int leftSubTreeSize = 0;
		if(this.leftChild != null)
			leftSubTreeSize = this.leftChild.size();
		
		int rightSubTreeSize = 0;
		if(this.rightChild != null)
			rightSubTreeSize = this.rightChild.size();
		
		return leftSubTreeSize + rightSubTreeSize + 1;
	}
	
	/**
	 * Removes all of the mappings from this BinaryTree and resets the node with null values
	 */
	public void clear() {
		this.value = null;
		this.leftChild = null;
		this.rightChild = null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree<String> bt1 = new BinaryTree<String>("2");
		BinaryTree<String> bt2 = new BinaryTree<String>("1");
		BinaryTree<String> bt3 = new BinaryTree<String>("1");
		bt1.setLeftChild(bt2);
		bt2.setRightChild(bt3);
		System.out.println(bt1.size());
		bt1.clear();
		System.out.println(bt1.size());
	}

}
