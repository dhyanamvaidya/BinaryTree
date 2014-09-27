/**
 * 
 */
package binaryTree;

import java.util.ArrayList;

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
		int leftSubTreeSize = 0;
		if(this.leftChild != null)
			leftSubTreeSize = this.leftChild.size();
		
		int rightSubTreeSize = 0;
		if(this.rightChild != null)
			rightSubTreeSize = this.rightChild.size();
		
		return leftSubTreeSize + rightSubTreeSize + 1;
	}
	
	/**
	 * Prints the nodes of this BinaryTree in an inOrder traversal
	 * @return
	 */
	public void inOrderTraversal() {
		if(this.leftChild != null)
			this.leftChild.inOrderTraversal();
		System.out.println(this.getValue());
		if(this.rightChild != null)
			this.rightChild.inOrderTraversal();
	}
	
	/**
	 * Prints the nodes of this BinaryTree in a preOrder traversal
	 * @return
	 */
	public void preOrderTraversal() {
		System.out.println(this.getValue());
		if(this.leftChild != null)
			this.leftChild.preOrderTraversal();
		if(this.rightChild != null)
			this.rightChild.preOrderTraversal();
	}
	
	/**
	 * Prints the nodes of this BinaryTree in a postOrder traversal
	 * @return
	 */
	public void postOrderTraversal() {
		if(this.leftChild != null)
			this.leftChild.postOrderTraversal();
		if(this.rightChild != null)
			this.rightChild.postOrderTraversal();
		System.out.println(this.getValue());
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
	 * Returns true if this BinaryTree contains the specified BinaryTree node
	 * @param key
	 * @return
	 */
	public boolean contains(BinaryTree<V> key) {
		if(key == null)
			return false;
		
		if(this == key)
			return true;
		
		if(this.leftChild != null)
			if(this.leftChild.contains(key))
				return true;
		
		if(this.rightChild != null)
			if(this.rightChild.contains(key))
				return true;

		return false;
	}

	/**
	 * Translates a String description of a tree into a Tree<String> object. 
	 * The treeDescription has the form value(child child), where 
	 * a value is any sequence of characters not containing parentheses or 
	 * whitespace, and each child is either just a (String) value or is 
	 * another treeDescription. Whitespace is used to separate values.
	 * For example, the String "one(two three(four(six seven) five)) six" 
	 * becomes the Tree:
	 *                            one
	 *                            / \
	 *                           /   \
	 *                         two  three
	 *                         		 /\
	 *                         		/  \
	 *                            four five
	 *                             /\
	 *                            /  \
	 *                          six seven
	 * @param treeDescription
	 * @return
	 */
	public static BinaryTree<String> parse(String treeDescription) {
		BinaryTree<String> root = null;
		treeDescription = treeDescription.trim();
		if(!treeDescription.contains("(")) {
			root = new BinaryTree<String>(treeDescription.trim());
			return root;
		}
		else {
			int firstParenOccurrence = treeDescription.indexOf('(');
			String rootValue = treeDescription.substring(0, firstParenOccurrence);
			root = new BinaryTree<String>(rootValue.trim());
			String childrenValue = treeDescription.substring(firstParenOccurrence+1, treeDescription.length()-1);
			
			ArrayList<String> tokenizedChildren = tokenizeChildren(childrenValue);
			if(tokenizedChildren.size() == 1)
				root.leftChild = parse(tokenizedChildren.get(0));
			else if(tokenizedChildren.size() == 2)
				root.leftChild = parse(tokenizedChildren.get(0));
				root.rightChild = parse(tokenizedChildren.get(1));
		}
		return root;
	}
	
	/**
	 * Helper method which tokenizes the given string so that it can be split
	 * into two separate child strings out of which two separate BinaryTree
	 * node can be made
	 * @param childrenValue
	 * @return
	 */
	public static ArrayList<String> tokenizeChildren(String childrenValue) {
		ArrayList<String> tokenizedChildren = new ArrayList<String>();
		StringBuilder temp = new StringBuilder("");
		int parenCounter = 0;
		for(int i=0; i<childrenValue.length(); i++) {
			if(childrenValue.charAt(i) == ' ' || i == childrenValue.length()-1) {
				temp.append(childrenValue.charAt(i));
				tokenizedChildren.add(temp.toString().trim());
				temp.delete(0, temp.length());
			}
			else if(childrenValue.charAt(i) == '(') {
				parenCounter += 1;
				temp.append(childrenValue.charAt(i));
				i += 1;
				while(parenCounter > 0) {
					if(childrenValue.charAt(i) == '(')
						parenCounter += 1;
					else if(childrenValue.charAt(i) == ')')
						parenCounter -= 1;
					temp.append(childrenValue.charAt(i));
					i += 1;
				}
				tokenizedChildren.add(temp.toString().trim());
				temp.delete(0, temp.length());
			}
			else {
				temp.append(childrenValue.charAt(i));
			}
		}
		return tokenizedChildren;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//checking contains
		/*BinaryTree<String> bt1 = new BinaryTree<String>("2");
		BinaryTree<String> bt2 = new BinaryTree<String>("1");
		BinaryTree<String> bt3 = new BinaryTree<String>("1");
		bt1.setLeftChild(bt2);
		bt2.setRightChild(bt3);
		System.out.println(bt1.contains(bt3));*/
		
		//checking substring in parse
		String treeDescription = "one(two three(four(six seven) five))";
		/*int firstParenOccurrence = treeDescription.indexOf('(');
		String rootValue = treeDescription.substring(0, firstParenOccurrence);
		String childrenValue = treeDescription.substring(firstParenOccurrence+1, treeDescription.length()-1);*/
		
		//checking tokenized children
		/*ArrayList<String> al = new ArrayList<String>();
		al = tokenizeChildren(childrenValue);
		for(String s : al)
			System.out.println(s);*/
		
		//checking whether the tree has been built correctly or not
		BinaryTree<String> root = BinaryTree.parse(treeDescription);
		System.out.println("Size: "+root.size());
		System.out.println("-------------PreOrder Traversal-------------");
		root.preOrderTraversal();
		System.out.println("-------------InOrder Traversal-------------");
		root.inOrderTraversal();
		System.out.println("-------------PostOrder Traversal-------------");
		root.postOrderTraversal();
		
		//checking string builder delete method
		/*StringBuilder sb = new StringBuilder("dhyanam");
		System.out.println(sb.toString());
		sb.delete(0, sb.length());	*/	
	}

}
