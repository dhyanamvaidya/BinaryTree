/**
 * 
 */
package main.java.com.github.dhyanamvaidya.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Binary Tree is a node-based binary tree data structure which has the following properties:
 *  -- Each node can contain left and right child tree nodes
 *  -- Both the left and right subtrees must also be binary trees 
 *  -- It can be used (extended) as a Binary Search Tree where the left child of a node is lesser in
 *  	value than the root and right child is greater in value than the root
 *  
 * Each object in the BinaryTree class represents a single node; however, nodes are linked together,
 * so that any node may be considered as the "root" of a complete tree
 *  
 *  @author Dhyanam Vaidya
 *  @version 26 September 2014
 *  @param <V> The type of values held in the Tree
 */
public class BinaryTree<V> {
	
	private V value;
	private BinaryTree<V> leftChild;
	private BinaryTree<V> rightChild;
	
	/**
	 * Creates a BinaryTree node with the given value
	 * @param value The value to put in this node
	 */
	public BinaryTree(V value) {
		this.value = value;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	/**
	 * Creates a BinaryTree node and sets its plus its left and right child values with the given values
	 * @param value The value to put in this node
	 * @param leftChild The left child node
	 * @param rightChild The right child node
	 */
	public BinaryTree(V value, BinaryTree<V> leftChild, BinaryTree<V> rightChild) {
		this.value = value;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	/**
	 * Gets the value in this node of the BinaryTree
	 * @return The value in this node
	 */
	public V getValue() {
		return value;
	}

	/**
	 * Sets the value in this node of the BinaryTree
	 * @param value The value to be set
	 */
	public void setValue(V value) {
		this.value = value;
	}

	/**
	 * Gets the value of the left child of this BinaryTree node
	 * @return the leftChild
	 */
	public BinaryTree<V> getLeftChild() {
		return leftChild;
	}

	/**
	 * Sets the node supplied as the left child of this BinaryTree node
	 * @param leftChild The leftChild to be set
	 */
	public void setLeftChild(BinaryTree<V> leftChild) {
		if(!this.contains(leftChild))
			this.leftChild = leftChild;
	}

	/**
	 * Gets the value of the right child of this BinaryTree node
	 * @return the rightChild
	 */
	public BinaryTree<V> getRightChild() {
		return rightChild;
	}

	/**
	 * Sets the node supplied as the right child of this BinaryTree node
	 * @param rightChild The rightChild to be set
	 */
	public void setRightChild(BinaryTree<V> rightChild) {
		if(!this.contains(rightChild))
			this.rightChild = rightChild;
	}
	
	/**
     * Removes and returns the left child of this BinaryTree (as a
     * complete subtree), or throws a NoSuchElementException if
     * the left child is not present
     * 
     * @return The left child subtree that is removed
     * @throws NoSuchElementException If there is no such child
     */
    public BinaryTree<V> removeLeftChild() throws NoSuchElementException {
        try {
        	BinaryTree<V> leftChild = this.getLeftChild();
        	leftChild.getValue();
        	this.setLeftChild(null);
            return leftChild;
        }
        catch (Exception e) {
            throw new NoSuchElementException(e + "");
        }
    }
    
    /**
     * Removes and returns the right child of this BinaryTree (as a
     * complete subtree), or throws a NoSuchElementException if
     * the left child is not present
     * 
     * @return The right child subtree that is removed
     * @throws NoSuchElementException If there is no such child
     */
    public BinaryTree<V> removeRightChild() throws NoSuchElementException {
        try {
        	BinaryTree<V> rightChild = this.getRightChild();
        	rightChild.getValue();
        	this.setRightChild(null);
            return rightChild;
        }
        catch (Exception e) {
            throw new NoSuchElementException(e + "");
        }
    }

    /**
     * Returns the number of (immediate) children of this node
     * @return The number of children of this node.
     */
    public int getNumberOfChildren() {
    	if(this.leftChild == null && this.rightChild == null)
    		return 0;
    	if(this.leftChild != null && this.rightChild != null)
    		return 2;
    	return 1;
    }
    
    /**
     * Returns <code>true</code> if this node has no children.
     * @return <code>true</code> if this node is a leaf.
     */
    public boolean isLeaf() {
        return this.getNumberOfChildren() == 0;
    }
    
    /**
	 * Returns <code>true</code> if this BinaryTree contains the
     * given node (not an equal node). The root of this tree
     * is included in the recursive search
	 * 
	 * @param node The node to be searched for
	 * @return
	 */
	public boolean contains(BinaryTree<V> node) {
		if(this == node)
			return true;
		
		if(this.leftChild != null)
			if(this.leftChild.contains(node))
				return true;
		
		if(this.rightChild != null)
			if(this.rightChild.contains(node))
				return true;

		return false;
	}

	/**
	 * Returns <code>true</code> if (1) given object 
	 * is a BinaryTree, and (2) the value field of the
	 * two BinaryTrees are equal, and (3) each child of
	 * one BinaryTree equals the corresponding child of
	 * the other BinaryTree
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @param obj The object to be compared with
	 */	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof BinaryTree) {
			final BinaryTree<?> other = (BinaryTree<?>) obj;
			/* Check for null equality first, If both of them are not null then if 'this' object is not null
			 * then check the value equality. This is done to prevent NullPointerExceptions from being thrown */
			if( (this.getValue() == other.getValue()) || 
				(this.getValue() != null && (this.getValue().equals(other.getValue()))) ) {
				
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
	 * 
	 * @return toString method will return the below String,
	 * "one(two three(four(six seven) five))"
	 * @see java.lang.Object#toString()
	*/
	@Override
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
	 * 
	 * @param treeDescription The String to be parsed
	 * @return The resultant Tree&lt;String&lt;
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
	private static ArrayList<String> tokenizeChildren(String childrenValue) {
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
	 * Returns the number of nodes including the root in the Binary Tree
	 * rooted at this node
	 * @return The number of total nodes in this subtree
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
	 * Prints the nodes of this BinaryTree in an inOrder traversal to the console
	 */
	public void inOrderTraversal() {
		if(this.leftChild != null)
			this.leftChild.inOrderTraversal();
		System.out.println(this.getValue());
		if(this.rightChild != null)
			this.rightChild.inOrderTraversal();
	}
	
	/**
	 * Prints the nodes of this BinaryTree in a preOrder traversal to the console
	 */
	public void preOrderTraversal() {
		System.out.println(this.getValue());
		if(this.leftChild != null)
			this.leftChild.preOrderTraversal();
		if(this.rightChild != null)
			this.rightChild.preOrderTraversal();
	}
	
	/**
	 * Prints the nodes of this BinaryTree in a postOrder traversal to the console
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
	 * @param string The string containing the information about the indent
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
     * Returns the LinkedList containing only the leaves of this BinaryTree on which
     * this method is called
     * @return The LinkedList containing the leaves of this BinaryTree
     */
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
     * Returns <code>true</code> if the left subtree of this node is the same as its
     * right subtree in values and shape
     * @return <code>true</code> if the node is symmetric
     */
    public boolean isSymmetric() {
    	if(this.leftChild == null)
    		return this.rightChild == null;
    	else
    		return this.leftChild.equals(this.rightChild);
	}
    
    /**
     * Returns <code>true</code> if difference between heights of its left subtrees and right subtrees
     * is not more than 1 and left and right subtrees are balanced, otherwise return false.
     * @return <code>true</code> if the BinaryTree is balanced
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
     * 	A null tree (no nodes except the null node) is defined to have a height of negative 1
     * Height of a tree is the depth of farthest most leaf node from the root (max depth)
     * @return The height of this BinaryTree
     */
	public int getHeight() {
		return getHeightHelper(this);
	}
	
	/**
	 * Private helper method to the getHeight() method which does the actual work
	 * @param root The root node whose height is to be returned
	 * @return The height of this BinaryTree
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
	 * Returns the Kth Largest element present in the BinaryTree rooted at this node or NULL
	 * if not present
	 * This method will give accurate results only if the BinaryTree on which this method is
	 * called is a BinarySearchTree
	 * @param k The Kth largest element to be returned
	 * @return The Kth largest element
	 */
	public BinaryTree<V> getKthLargestNode(int k) {
		if(k > this.size())
			return null;
		
		if(this.rightChild != null && k < this.rightChild.size()+1) 
			return this.rightChild.getKthLargestNode(k);
		
		else if(this.rightChild != null && k > this.rightChild.size()+1) 
			return this.leftChild.getKthLargestNode(k-(this.rightChild.size()+1));
		
		else 
			return this;
	}
	
	/**
	 * Returns the Kth Smallest element present in the BinaryTree rooted at this node or NULL
	 * if not present
	 * This method will give accurate results only if the BinaryTree on which this method is
	 * called is a BinarySearchTree
	 * @param k The Kth smallest element to be returned
	 * @return The Kth smallest element
	 */
	public BinaryTree<V> getKthSmallestNode(int k) {
		if(k > this.size()) 
			return null;
		
		if(this.leftChild != null && k < this.leftChild.size()+1) 
			return this.leftChild.getKthSmallestNode(k);
		
		else if(this.leftChild != null && k > this.leftChild.size()+1) 
			return this.rightChild.getKthSmallestNode(k-(this.leftChild.size()+1));
		
		else 
			return this;
	}

}
