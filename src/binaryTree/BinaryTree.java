/**
 * 
 */
package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;

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
		if(!this.contains(leftChild))
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
		if(!this.contains(rightChild))
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
	 * For example, the String "one(two three(four(six seven) five))" 
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
			else if(tokenizedChildren.size() == 2) {
				root.leftChild = parse(tokenizedChildren.get(0));
				root.rightChild = parse(tokenizedChildren.get(1));
			}
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
	 * Prints a multi-line version of the tree
	 * For example, for the Tree:
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
	 * print method will output the below tree
	 * one
	 * |  two
	 * |  three
	 * |  |  four
	 * |  |  |  six
	 * |  |  |  seven
	 * |  |  five
	 */
	public void print() {
		this.printHelper("");
	}

	/**
	 * Helper method to print which does the actual work
	 * @param string
	 */
	private void printHelper(String string) {
		StringBuilder indent = new StringBuilder(string);
		System.out.println(indent.toString()+this.getValue());
		indent.append("|\t");
		if(this.leftChild != null)
			this.leftChild.printHelper(indent.toString());
		if(this.rightChild != null)
			this.rightChild.printHelper(indent.toString());
	}
	
	/**
	 * Returns a String representing this Tree. The returned String
	 * is in the same format as the parse method expects as input.
	 * For example, for the Tree:
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
	 * toString method will return the below String,
	 * "one(two three(four(six seven) five))"
	 * @Override
	*/
	public String toString() {
		StringBuilder representation = new StringBuilder("");
		representation.append(this.getValue());
		if(this.leftChild != null && this.rightChild != null) {
			representation.append("(");
			if(this.leftChild != null) 
				representation.append(this.leftChild.toString());
			if(this.rightChild != null)
				representation.append(" "+this.rightChild.toString());
			representation.append(")");
		}
		return representation.toString();
	}
	
	/**
	 * Returns true if obj is a Tree and has the same shape
	 * and contains the same values as this Tree
	 * @Override
	 */
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof BinaryTree) {
			final BinaryTree<V> other = (BinaryTree<V>) obj;
			if(this.getValue().equals(other.getValue())) {
				/* Check left subtree equality */
				if(this.leftChild == null) {
					if(other.leftChild != null)
						return false;
				}
				else {
					if(!this.leftChild.equals(other.leftChild))
						return false;
				}
				
				/* Check right subtree equality */
				if(this.rightChild == null) {
					if(other.rightChild != null)
						return false;
				}
				else {
					if(!this.rightChild.equals(other.rightChild))
						return false;
				}
				return true;
			}
			return false;
		}
		return false;
	}
	
	/**
	 * Returns the hash code value for this BinaryTree. The hash code of
	 * a BinaryTree is defined to be the sum of the hash codes of root and
	 * its left child and its right child. This ensures that node1.equals(node2)
	 * implies that node1.hashCode()==node2.hashCode() for any two nodes node1
	 * and node2 of type BinaryTree as required by the general contract of Object.hashCode().
	 * 	@Override
	 */
    public int hashCode() {
    	int rootHash = this.getValue().hashCode();
    	int leftChildHash = 0;
    	if(this.leftChild != null)
    		this.getLeftChild().hashCode();
    	int rightChildHash = 0;
    	if(this.rightChild != null)
    		this.getRightChild().hashCode();
    	return 17 * (rootHash + leftChildHash + rightChildHash);
	}
    
    @SuppressWarnings("rawtypes")
	public LinkedList<BinaryTree<V>> getLeaves() {
    	LinkedList<BinaryTree<V>> list = new LinkedList<BinaryTree<V>>();
    	if(this.leftChild == null && this.rightChild == null)
    		list.add(this);
    	
    	if(this.leftChild != null)
    		list.addAll(this.leftChild.getLeaves());
    	if(this.rightChild != null)
    		list.addAll(this.rightChild.getLeaves());
    		
    	return list;
    }
    
    /**
     * Returns true if the left subtree of this node is the same as its
     * right subtree in values and shape
     * @return
     */
    public boolean isSymmetric() {
    	if(this.leftChild == null)
    		return this.rightChild == null;
    	else
    		return this.leftChild.equals(this.rightChild);
	}
    
    /**
     * Returns true if difference between heights of its left subtrees and right subtrees
     * is not more than 1 and left and right subtrees are balanced, otherwise return false.
     * @return
     */
    public boolean isBalanced() {
    	int leftSubTreeHeight = getHeightHelper(this.leftChild);
    	int rightSubTreeHeight = getHeightHelper(this.rightChild);
    	
		int difference = leftSubTreeHeight - rightSubTreeHeight;
		int absDifference = (difference < 0) ? -difference : difference;
		
		return absDifference < 2;
	}
	
    /**
     * Returns the height of the BinaryTree on which this method is called
     * For a tree with just one node, the root node, the height is defined to be 0,
     * 	if there are 2 levels of nodes the height is 1 and so on
     * 	A null tree (no nodes except the null node) is defined to have a height of Ð1
     * Height of a tree is the depth of farthest most leaf node from the root (max depth)
     * @param root
     * @return
     */
	public int getHeight() {
		return getHeightHelper(this);
	}
	
	/**
	 * Private helper method to the getHeight() method which does the actual work
	 * @param root
	 * @return
	 */
	private int getHeightHelper(BinaryTree<V> root) {
		if(root==null) {
			return -1;
		}
		else {
			int leftSubTreeHeight = getHeightHelper(root.leftChild)+1;
			int rightSubTreeHeight = getHeightHelper(root.rightChild)+1;
			
			return leftSubTreeHeight > rightSubTreeHeight ? leftSubTreeHeight : rightSubTreeHeight;
		}
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
		String treeDescription = "one(two(four(six seven) five))";
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
		System.out.println("-------------Print-------------");
		root.print();
		System.out.println("-------------ToString-------------");
		System.out.println(root.toString());
		
//		BinaryTree<String> anotherRoot = BinaryTree.parse(treeDescription);
		BinaryTree<String> anotherRoot = BinaryTree.parse("13(10(1 12(11)) 15(13 17(21(18))))");
		System.out.println(root.equals(anotherRoot));
		System.out.println("Size: "+anotherRoot.size());
		System.out.println(root.hashCode() + " -- "+anotherRoot.hashCode());
		System.out.println("3".hashCode() + " -- "+"3".hashCode());
		
		System.out.println("Balanced or not: "+anotherRoot.isBalanced());
		System.out.println("Ex Height: "+anotherRoot.getHeight());
		
		//checking string builder delete method
		/*StringBuilder sb = new StringBuilder("dhyanam");
		System.out.println(sb.toString());
		sb.delete(0, sb.length());	*/	
		
		LinkedList<BinaryTree<String>> l = new LinkedList<BinaryTree<String>>();
		l = anotherRoot.getLeaves();
		
		System.out.println("-------------Print-------------");
		anotherRoot.print();
		System.out.println("-------------PrintLeaves-------------");
		for(BinaryTree<String> bt : l)
			System.out.println(bt.value);
		
	}

}
