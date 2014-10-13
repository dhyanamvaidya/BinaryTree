/**
 * 
 */
package test.java.com.github.dhyanamvaidya.binaryTree;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import main.java.com.github.dhyanamvaidya.binaryTree.BinaryTree;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Dhyanam Vaidya
 */
public class BinaryTreeTest {
	
	BinaryTree<String> tree, tree1, tree2;
    BinaryTree<String> a, b, c, d, e, f, g, a1, b1, c1, d1, e1, f1, g1, a2, b2, c2, d2, e2, f2, g2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		/*
         *              a1              a2
         *             / \             / \
         *            /   \           /   \
         *          b1     c1        b2    c2
         *          /\     /\       /\     /\
         *        d1  e1 f1  g1   e2  d2 f2  g2
         */
        a1 = new BinaryTree<String>("a");
        b1 = new BinaryTree<String>("b");
        c1 = new BinaryTree<String>("c");
        d1 = new BinaryTree<String>("d");
        e1 = new BinaryTree<String>("e");
        f1 = new BinaryTree<String>("f");
        g1 = new BinaryTree<String>("g");
        
        a1.setLeftChild(b1);
        a1.setRightChild(c1);
        b1.setLeftChild(d1);
        b1.setRightChild(e1);
        c1.setLeftChild(f1);
        c1.setRightChild(g1);
        
        tree1 = a1;

        a2 = new BinaryTree<String>("a");
        b2 = new BinaryTree<String>("b");
        c2 = new BinaryTree<String>("c");
        d2 = new BinaryTree<String>("d");
        e2 = new BinaryTree<String>("e");
        f2 = new BinaryTree<String>("f");
        g2 = new BinaryTree<String>("g");
        
        a2.setLeftChild(b2);
        a2.setRightChild(c2);
        b2.setLeftChild(d2);
        b2.setRightChild(e2);
        c2.setLeftChild(f2);
        c2.setRightChild(g2);
        
        tree2 = a2;
        
        a = new BinaryTree<String>("a");
        b = new BinaryTree<String>("b");
        c = new BinaryTree<String>("b");
        d = new BinaryTree<String>("d");
        e = new BinaryTree<String>("e");
        f = new BinaryTree<String>("d");
        g = new BinaryTree<String>("e");
	}
	
	@Test
    public void testBinaryTree() {
		/* With normal value */
        BinaryTree<String> bt = new BinaryTree<String>("x");
        assertTrue(bt.getNumberOfChildren() == 0);
        assertEquals("x", bt.getValue());
        
        /* With null value */
        BinaryTree<String> bt1 = new BinaryTree<String>(null);
        assertTrue(bt1.getNumberOfChildren() == 0);
        assertEquals(null, bt1.getValue());
    }
	
	@Test
    public void testBinaryTreeWithChildren() {
        BinaryTree<String> lc = new BinaryTree<String>("y");
        BinaryTree<String> rc = new BinaryTree<String>("z");
        BinaryTree<String> bt = new BinaryTree<String>("x", lc, rc);
        assertTrue(bt.getNumberOfChildren() == 2);
        assertEquals("x", bt.getValue());
        assertEquals(bt.getLeftChild(), lc);
        assertEquals(bt.getRightChild(), rc);
    }
	
	@Test
    public void testGetValue() {
        assertEquals("a", a1.getValue());
        assertEquals("b", b1.getValue());
        assertEquals("a", a2.getValue());
        assertThat("x", is(not(a1.getValue())));
    }
	
	@Test
    public void testSetValue() {
        a1.setValue("A");
        assertEquals("A", a1.getValue());
        a1.setValue(null);
        assertNull(a1.getValue());
    }
	
	@Test
    public void testGetLeftChild() {
        assertEquals(b1, a1.getLeftChild());
        assertEquals(d1, b1.getLeftChild());
        assertEquals(f2, c2.getLeftChild());
        assertThat(f2, is(not(a1.getLeftChild())));
    }
	
	@Test
    public void testSetLeftChild() {
		BinaryTree<String> lc = new BinaryTree<String>("y");
        BinaryTree<String> root = new BinaryTree<String>("x");
        
        root.setLeftChild(lc);
        assertEquals(lc, root.getLeftChild());
        
        assertThat(f2, is(not(root.getLeftChild())));
    }
	
	@Test
    public void testGetRightChild() {
        assertEquals(c1, a1.getRightChild());
        assertEquals(g1, c1.getRightChild());
        assertEquals(e2, b2.getRightChild());
        assertThat(f2, is(not(a1.getRightChild())));
    }
	
	@Test
    public void testSetRightChild() {
		BinaryTree<String> rc = new BinaryTree<String>("y");
        BinaryTree<String> root = new BinaryTree<String>("x");
        
        root.setRightChild(rc);
        assertEquals(rc, root.getRightChild());
        
        assertThat(f2, is(not(root.getLeftChild())));
    }
	
	@Test
    public void testRemoveLeftChild() {
		BinaryTree<String> lc = new BinaryTree<String>("y");
        BinaryTree<String> root = new BinaryTree<String>("x");
        
        root.setLeftChild(lc);
        assertEquals(lc, root.getLeftChild());
        root.removeLeftChild();
        assertNull(root.getLeftChild());
        /* Removing null left child */
        try {
			root.removeLeftChild();
        }
        catch (NoSuchElementException e) { }
        catch (Throwable t) {
            throw new AssertionError("Wrong type of Exception thrown: " + t);
        }
		
		/* Removing non-null left child */
		lc = new BinaryTree<String>("y");
        root = new BinaryTree<String>("x");
        root.setLeftChild(lc);
        assertEquals(lc, root.getLeftChild());
		try {
			root.removeLeftChild();
        }
        catch (NoSuchElementException e) { }
        catch (Throwable t) {
            throw new AssertionError("Wrong type of Exception thrown: " + t);
        }
    }
	
	@Test
    public void testRemoveRightChild() {
		BinaryTree<String> rc = new BinaryTree<String>("y");
        BinaryTree<String> root = new BinaryTree<String>("x");
        
        root.setRightChild(rc);
        assertEquals(rc, root.getRightChild());
        root.removeRightChild();
        assertNull(root.getRightChild());
        /* Removing null right child */
        try {
			root.removeRightChild();
        }
        catch (NoSuchElementException e) { }
        catch (Throwable t) {
            throw new AssertionError("Wrong type of Exception thrown: " + t);
        }
        
        rc = new BinaryTree<String>("y");
        root = new BinaryTree<String>("x");
        
        root.setRightChild(rc);
        assertEquals(rc, root.getRightChild());
        /* Removing non-null right child */
        try {
			root.removeRightChild();
        }
        catch (NoSuchElementException e) { }
        catch (Throwable t) {
            throw new AssertionError("Wrong type of Exception thrown: " + t);
        }
	}
	
	@Test
    public void testNumberOfChildren() {
        BinaryTree<String> lc = new BinaryTree<String>("y");
        BinaryTree<String> rc = new BinaryTree<String>("z");
        BinaryTree<String> root = new BinaryTree<String>("x");
        assertEquals(0, root.getNumberOfChildren());
        root.setLeftChild(lc);
        assertEquals(1, root.getNumberOfChildren());
        root.setRightChild(rc);
        assertEquals(2, root.getNumberOfChildren());
        
	}
	
	@Test
    public void testIsLeaf() {
        BinaryTree<String> root = new BinaryTree<String>("x");
        assertTrue(root.isLeaf());
        BinaryTree<String> lc = new BinaryTree<String>("y");
        root.setLeftChild(lc);
        assertFalse(root.isLeaf());
	}
	
	@Test
    public void testContains() {
		assertTrue(a1.contains(a1));
        assertTrue(a1.contains(b1));
        assertTrue(a1.contains(c1));
        assertTrue(a1.contains(d1));
        assertTrue(b1.contains(d1));
        assertFalse(b1.contains(a1));
        assertFalse(d1.contains(a1));
        assertFalse(d1.contains(b1));
        assertFalse(c1.contains(b1));
	}
	
	@Test
	public void testEquals() {
		assertTrue(f1.equals(f2));
        assertFalse(f1.equals(g1));
        assertTrue(a1.equals(a1));
        assertTrue(a1.equals(a2));
        assertTrue(c1.equals(c2));
        assertTrue(b1.equals(b2));
        BinaryTree<String> x = new BinaryTree<String>("x");
        BinaryTree<String> y = new BinaryTree<String>(null);
        assertFalse(f1.equals(null));
        assertFalse(x.equals(y));
        assertFalse(y.equals(x));
        BinaryTree<String> z = new BinaryTree<String>(null);
        assertTrue(y.equals(z));
        z.setLeftChild(new BinaryTree<String>("child"));
        assertFalse(y.equals(z));
	}
	
	@Test
    public void testToString() {
		assertEquals("a(b(d e) c(f g))", a1.toString());
    }
	
	@Test
    public void testParse() {
		BinaryTree<String> root = BinaryTree.parse("a(b(d e) c(f g))");
		assertEquals(root, a1);
		
    }
	
	@Test
    public void testSize() {
		assertEquals(7, a1.size());
		assertEquals(3, b1.size());
		assertEquals(1, d1.size());
    }
	
	@Test
    public void testClear() {
		BinaryTree<String> lc = new BinaryTree<String>("y");
        BinaryTree<String> rc = new BinaryTree<String>("z");
        BinaryTree<String> root = new BinaryTree<String>("x", lc, rc);
		assertEquals(3, root.size());
		root.clear();
		assertEquals(1, root.size());
		assertNull(root.getValue());
		assertNull(root.getLeftChild());
		assertNull(root.getRightChild());
    }
	
	@Test
    public void testGetLeaves() {
		LinkedList<BinaryTree<String>> list = a1.getLeaves();
		assertEquals(4, list.size());
		assertTrue(list.contains(d1));
		assertTrue(list.contains(d1));
		assertTrue(list.contains(f1));
		assertTrue(list.contains(g1));
		assertFalse(list.contains(a1));
	}
	
	@Test
    public void testIsSymmetric() {
		BinaryTree<String> lc = new BinaryTree<String>("y");
        BinaryTree<String> rc = new BinaryTree<String>("y");
        BinaryTree<String> root = new BinaryTree<String>("x", lc, rc);
        assertTrue(root.isSymmetric());
        assertTrue(a.isSymmetric());
        assertFalse(a1.isSymmetric());
	}
	
	@Test
    public void testIsBalanced() {
		BinaryTree<String> lc = new BinaryTree<String>("y");
        BinaryTree<String> rc = new BinaryTree<String>("y");
        BinaryTree<String> root = new BinaryTree<String>("x", lc, rc);
        assertTrue(root.isBalanced());
        root = BinaryTree.parse("a(b(d(h(i(j))) e) c(f g))");
        assertFalse(root.isBalanced());
	}

	@Test
    public void testGetHeight() {
		BinaryTree<String> lc = new BinaryTree<String>("y");
        BinaryTree<String> rc = new BinaryTree<String>("y");
        BinaryTree<String> root = new BinaryTree<String>("x", lc, rc);
        assertEquals(1, root.getHeight());
        assertEquals(2, a1.getHeight());
        root = new BinaryTree<String>(null);
        assertEquals(0, root.getHeight());
	}
	
	@Test
    public void testGetKthLargestNode() {
		BinaryTree<String> root = BinaryTree.parse("10(5(2(1 3) 8(6 9)) 15(12(11 13) 18(16 20)))");
		BinaryTree<String> b = root.getKthLargestNode(10);
		assertEquals("8", b.getValue());
		b = root.getKthLargestNode(1);
		assertEquals("20", b.getValue());
		b = root.getKthLargestNode(15);
		assertEquals("1", b.getValue());
		b = root.getKthLargestNode(8);
		assertEquals("10", b.getValue());
		b = root.getKthLargestNode(3);
		assertEquals("16", b.getValue());
		b = root.getKthLargestNode(5);
		assertEquals("13", b.getValue());
	}
	
	@Test
    public void testGetKthSmallestNode() {
		BinaryTree<String> root = BinaryTree.parse("10(5(2(1 3) 8(6 9)) 15(12(11 13) 18(16 20)))");
		BinaryTree<String> b = root.getKthSmallestNode(10);
		assertEquals("12", b.getValue());
		b = root.getKthSmallestNode(1);
		assertEquals("1", b.getValue());
		b = root.getKthSmallestNode(15);
		assertEquals("20", b.getValue());
		b = root.getKthSmallestNode(8);
		assertEquals("10", b.getValue());
		b = root.getKthSmallestNode(3);
		assertEquals("3", b.getValue());
		b = root.getKthSmallestNode(5);
		assertEquals("6", b.getValue());
	}

}
