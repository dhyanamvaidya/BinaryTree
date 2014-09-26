/**
 * 
 */
package binaryTree;

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
	
	public BinaryTree(V value, BinaryTree<V> leftChild, BinaryTree<V> rightChild) {
		this.value = value;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}
