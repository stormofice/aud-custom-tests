import org.junit.Test;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class BinNodeCustomTest {

    private AbstractBinNode<String> emptyTree() {
        return new BinNode<>(null, null, null);
    }

    private AbstractBinNode<String> emptyTreeWithNonNullValue() {
        return new BinNode<>(":(", null, null);
    }

    /**
     * Every tree but the AVL tree has the following structure:<br>
     * https://rxzt.xyz/XUfa2/pAWIcidi81.png/raw<br>
     * - black connections indicate connections based on the child/sibling model<br>
     * - grey connections indicate a normal binary tree (together with the black ones)<br>
     * - orange numbers indicate sorted values<br>
     * <p>
     **/
    private AbstractBinNode<String> mediumTree(Mode mode) {

        boolean flag = false;
        if (mode == Mode.AVL) {
            flag = true;
            mode = Mode.SORTED;
        }

        BinNode<String> l2 = new BinNode<>(mode == Mode.SORTED ? "c" : "def", null, null);
        BinNode<String> l1 = new BinNode<>(mode == Mode.SORTED ? "a" : "abc", null, l2);

        BinNode<String> l4 = new BinNode<>(mode == Mode.SORTED ? "h" : "FeelsStrongMen", null, null);
        BinNode<String> l3 = new BinNode<>(mode == Mode.SORTED ? "e" : "PepeHands", null, l4);

        BinNode<String> l6 = new BinNode<>(mode == Mode.SINGLE_NULL ? null : mode == Mode.SORTED ? "u" : "why?", null, null);

        BinNode<String> l5 = new BinNode<>(mode == Mode.SORTED ? "p" : "xdxdxdxdxdxdxd", null, l6);

        BinNode<String> b2 = new BinNode<>(mode == Mode.SORTED ? "f" : "oomph", l3, null);
        BinNode<String> b1 = new BinNode<>(mode == Mode.SORTED ? "b" : ":(:(:(:(:(", l1, b2);

        BinNode<String> b3 = new BinNode<>(mode == Mode.SORTED ? "q" : "yikes", l5, null);

        BinNode<String> hb2 = new BinNode<>(mode == Mode.SORTED ? "z" : "gme 2000$ eow", b3, null);
        BinNode<String> hb1 = new BinNode<>(mode == Mode.SORTED ? "d" : "we getting there", b1, hb2);

        if (flag) {
            // Looks like this v
            // https://rxzt.xyz/XUfa2/HISIZUCo97.png/raw
            forceChangeValue("value", "q", hb2);
            BinNode<String> thirdLevel = new BinNode<>("u", null, null);
            BinNode<String> rightSecondaryLevel = new BinNode<>("z", thirdLevel, null);
            BinNode<String> secondaryLevel = new BinNode<>("p", null, rightSecondaryLevel);
            forceChangeValue("child", secondaryLevel, hb2);
        }

        return new BinNode<>(mode == Mode.SORTED ? "m" : "lebigsad", hb1, null);
    }

    private AbstractBinNode<Integer> binarySearchTree_AVL() {
        //		6           And yes, this is an AVL-Tree.
        //	  /   \         The balance factor is always > -2 and < 2
        //	 2		8       Watch the VL before you ask in #aud
        //	/	  /   \
        //   1	 7     10
        //			  /
        //			 9

        BinNode<Integer> nine = new BinNode<>(9, null, null);
        BinNode<Integer> ten = new BinNode<>(10, nine, null);
        BinNode<Integer> seven = new BinNode<>(7, null, ten);
        BinNode<Integer> eight = new BinNode<>(8, seven, null);
        BinNode<Integer> one = new BinNode<>(1, null, null);
        BinNode<Integer> two = new BinNode<>(2, one, eight);
        return new BinNode<>(6, two, null);
    }

    private AbstractBinNode<Integer> linkedListAsTree() {

        // __________1__________
        // ______//_____________
        // _____2_______________
        // ___//________________
        // ___3_________________
        // ....
        // 10

        BinNode<Integer> nodeTen = new BinNode<>(10, null, null);
        BinNode<Integer> nodeNine = new BinNode<>(9, nodeTen, null);
        BinNode<Integer> nodeEight = new BinNode<>(8, nodeNine, null);
        BinNode<Integer> nodeSeven = new BinNode<>(7, nodeEight, null);
        BinNode<Integer> nodeSix = new BinNode<>(6, nodeSeven, null);
        BinNode<Integer> nodeFive = new BinNode<>(5, nodeSix, null);
        BinNode<Integer> nodeFour = new BinNode<>(4, nodeFive, null);
        BinNode<Integer> nodeThree = new BinNode<>(3, nodeFour, null);
        BinNode<Integer> nodeTwo = new BinNode<>(2, nodeThree, null);
        BinNode<Integer> nodeOne = new BinNode<>(1, nodeTwo, null);

        return new BinNode<>(0, nodeOne, null);
    }

    private AbstractBinNode<Character> maximumHeap() {
        //           z
        //        /     \
        //      g         f
        //     / \       / \
        //    e   d     d   a
        //   / \       /
        //  a   e     A

        BinNode<Character> e1 = new BinNode<>('e', null, null);
        BinNode<Character> a1 = new BinNode<>('a', null, e1);
        BinNode<Character> d1 = new BinNode<>('d', null, null);
        BinNode<Character> e2 = new BinNode<>('e', a1, d1);
        BinNode<Character> A = new BinNode<>('A', null, null);
        BinNode<Character> a2 = new BinNode<>('a', null, null);
        BinNode<Character> d2 = new BinNode<>('d', A, a2);
        BinNode<Character> f = new BinNode<>('f', d2, null);
        BinNode<Character> g = new BinNode<>('g', e2, f);
        return new BinNode<>('z', g, null);
    }

    private AbstractBinNode<Character> nonMaximumHeapTree() {
        //             z
        //          /     \
        //        g         f
        //       / \       / \
        //      e   d     d   g
        //     / \       /
        //    a   e     A

        BinNode<Character> e1 = new BinNode<>('e', null, null);
        BinNode<Character> a1 = new BinNode<>('a', null, e1);
        BinNode<Character> d1 = new BinNode<>('d', null, null);
        BinNode<Character> e2 = new BinNode<>('e', a1, d1);
        BinNode<Character> A = new BinNode<>('A', null, null);
        BinNode<Character> g1 = new BinNode<>('g', null, null);
        BinNode<Character> d2 = new BinNode<>('d', A, g1);
        BinNode<Character> f = new BinNode<>('f', d2, null);
        BinNode<Character> g2 = new BinNode<>('g', e2, f);
        return new BinNode<>('z', g2, null);
    }

    private AbstractBinNode<Character> minimumHeapTree() {
        //           a
        //        /     \
        //      b         a
        //     / \       / \
        //    c   d     d   f
        //   / \       /
        //  g   j     h

        BinNode<Character> j = new BinNode<>('j', null, null);
        BinNode<Character> g = new BinNode<>('g', null, j);
        BinNode<Character> d1 = new BinNode<>('d', null, null);
        BinNode<Character> c = new BinNode<>('c', g, d1);
        BinNode<Character> h = new BinNode<>('h', null, null);
        BinNode<Character> f = new BinNode<>('f', null, null);
        BinNode<Character> d2 = new BinNode<>('d', h, f);
        BinNode<Character> a = new BinNode<>('a', d2, null);
        BinNode<Character> b = new BinNode<>('b', c, a);
        return new BinNode<>('a', b, null);
    }

    private AbstractBinNode<Character> nonMinimumHeapTree() {
        //              a
        //           /     \
        //         b         a
        //        / \       / \
        //       c   d     d   f
        //      / \       /
        //     G   j     h

        BinNode<Character> j = new BinNode<>('j', null, null);
        BinNode<Character> g = new BinNode<>('G', null, j);
        BinNode<Character> d1 = new BinNode<>('d', null, null);
        BinNode<Character> c = new BinNode<>('c', g, d1);
        BinNode<Character> h = new BinNode<>('h', null, null);
        BinNode<Character> f = new BinNode<>('f', null, null);
        BinNode<Character> d2 = new BinNode<>('d', h, f);
        BinNode<Character> a = new BinNode<>('a', d2, null);
        BinNode<Character> b = new BinNode<>('b', c, a);
        return new BinNode<>('a', b, null);
    }

    private AbstractBinNode<Long> bigBoiTree() {

        BinNode<Long> child = new BinNode<>(1L, null, null);
        BinNode<Long> start = new BinNode<>(2L, child, null);

        BinNode<Long> temp = child;
        for (int i = 0; i < 127; i++) {
            temp.sibling = new BinNode<>(ThreadLocalRandom.current().nextLong(Long.MIN_VALUE, -1), null, null);
            temp = temp.sibling;
        }

        return start;
    }

    private AbstractBinNode<Long> longBoiTree() {

        BinNode<Long> child = new BinNode<>(-1L, null, null);
        BinNode<Long> start = new BinNode<>(-2L, child, null);

        BinNode<Long> temp = child;
        for (int i = 0; i < 1023; i++) {
            temp.child = new BinNode<>(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE), null, null);
            temp = temp.child;
        }

        return start;
    }

    private AbstractBinNode<Integer> sameValuesTree() {
        BinNode<Integer> start = new BinNode<>(0, null, null);
        BinNode<Integer> temp = start;
        for (int i = 0; i < 10; i++) {
            temp.child = new BinNode<Integer>(0, null, null);
            temp = temp.child;
            BinNode<Integer> c = temp;
            for (int k = 0; k < 10; k++) {

                temp.sibling = new BinNode<>(0, null, null);
                temp = temp.sibling;

            }
            temp = c;
        }
        return start;
    }

    private AbstractBinNode<String> customGeneralTree() {
        // Contains null values and nodes with 3 children
        // __________a____________
        // ______//______\________
        // _____b==========c______
        // ___//_\______//_|_\____
        // ___d==null___f==g==h____
        // -//|\---------//-------
        // -p=t=z------null------
        BinNode<String> f = new BinNode<>("f", null, new BinNode<>("g", new BinNode<>(null, null, null), new BinNode<>("h", null, null)));
        BinNode<String> z = new BinNode<>("z", null, null);
        BinNode<String> t = new BinNode<>("t", null, z);
        BinNode<String> p = new BinNode<>("p", null, t);
        BinNode<String> d = new BinNode<>("d", p, new BinNode<>(null, null, null));
        BinNode<String> c = new BinNode<>("c", f, null);
        BinNode<String> b = new BinNode<>("b", d, c);
        return new BinNode<>("a", b, null);
    }

    private AbstractBinNode<Integer> customBinarySearchTree() {
        // Just a plain old bin search tree
        // __________7__________
        // ______//_____\_______
        // _____5=======12______
        // ___//_______//_\_____
        // ___3_______9====17____
        BinNode<Integer> three = new BinNode<>(3, null, null);
        BinNode<Integer> seventeen = new BinNode<>(17, null, null);
        BinNode<Integer> nine = new BinNode<>(9, null, seventeen);
        BinNode<Integer> twelve = new BinNode<>(12, nine, null);
        BinNode<Integer> five = new BinNode<>(5, three, twelve);
        return new BinNode<>(7, five, null);
    }

    private AbstractBinNode<Integer> customBinarySearchTree2() {
        // A bin search tree where one node has only a right child
        // __________7__________
        // ______//_____\_______
        // _____5=======12______
        // ___//__________\\_____
        // ___3____________17____
        BinNode<Integer> three = new BinNode<>(3, null, null);
        BinNode<Integer> seventeen = new BinNode<>(17, null, null);
        BinNode<Integer> twelve = new BinNode<>(12, seventeen, null);
        BinNode<Integer> five = new BinNode<>(5, three, twelve);
        return new BinNode<>(7, five, null);
    }

    private AbstractBinNode<Integer> customBinaryNotSearchTree() {
        // A binary tree thats not a binary search tree
        // __________7__________
        // ______//_____\_______
        // _____5=======12______
        // ___//_______//_\_____
        // ___3______19====17___
        // ---------------//----
        // --------------28-----
        BinNode<Integer> three = new BinNode<>(3, null, null);
        BinNode<Integer> twentyeight = new BinNode<>(28, null, null);
        BinNode<Integer> seventeen = new BinNode<>(17, twentyeight, null);
        BinNode<Integer> nineteen = new BinNode<>(19, null, seventeen);
        BinNode<Integer> twelve = new BinNode<>(12, nineteen, null);
        BinNode<Integer> five = new BinNode<>(5, three, twelve);
        return new BinNode<>(7, five, null);
    }

    private AbstractBinNode<Integer> customBinaryNotSearchTree2() {
        // Another binary tree thats not a binary search tree
        // __________7__________
        // ______//_____\_______
        // _____5=======12______
        // ___//_______//_\_____
        // ___3_______6====17___
        // ---------------//----
        // --------------4-----
        BinNode<Integer> three = new BinNode<>(3, null, null);
        BinNode<Integer> four = new BinNode<>(4, null, null);
        BinNode<Integer> seventeen = new BinNode<>(17, four, null);
        BinNode<Integer> six = new BinNode<>(6, null, seventeen);
        BinNode<Integer> twelve = new BinNode<>(12, six, null);
        BinNode<Integer> five = new BinNode<>(5, three, twelve);
        return new BinNode<>(7, five, null);
    }

    @Test(timeout = 20)
    public void customTest_generalTee() {
        assertTrue("isTree() returned false", customGeneralTree().isTree());

        assertFalse("isBinarySearchTree() returned true", customGeneralTree().isBinaryTree());
        assertFalse("isBinarySearchTree() returned true", customGeneralTree().isBinarySearchTree());
        assertFalse("isMaxHeap() returned true", customGeneralTree().isMaxHeap());
        assertFalse("isMinHeap() returned true", customGeneralTree().isMinHeap());
        assertFalse("isAVLTree() returned true", customGeneralTree().isAVLTree());

        assertEquals("Wrong tree height returned:", 3, customGeneralTree().getHeight());
        assertEquals("Wrong branching factor returned:", 3, customGeneralTree().getBranchingFactor());
    }

    @Test(timeout = 20)
    public void customTest_binarySearchTree() {
        assertTrue("isTree() returned false", customBinarySearchTree().isTree());
        assertTrue("isBinaryTree() returned false", customBinarySearchTree().isBinaryTree());
        assertTrue("isBinarySearchTree() returned false", customBinarySearchTree().isBinarySearchTree());
        assertTrue("isAVLTree() returned false", customBinarySearchTree().isAVLTree());

        assertFalse("isMinHeap() returned true", customBinarySearchTree().isMinHeap());
        assertFalse("isMaxHeap() returned true", customBinarySearchTree().isMaxHeap());

        assertEquals("Wrong tree height returned:", 2, customBinarySearchTree().getHeight());
        assertEquals("Wrong branching factor returned:", 2, customBinarySearchTree().getBranchingFactor());
    }

    @Test(timeout = 20)
    public void customTest_binaryNotSearchTree() {
        assertTrue("isTree() returned false", customBinaryNotSearchTree().isTree());
        assertTrue("isBinaryTree() returned false", customBinaryNotSearchTree().isBinaryTree());

        assertFalse("isBinarySearchTree() returned true", customBinaryNotSearchTree().isBinarySearchTree());
        assertFalse("isMinHeap() returned true", customBinaryNotSearchTree().isMinHeap());
        assertFalse("isMaxHeap() returned true", customBinaryNotSearchTree().isMaxHeap());
        assertFalse("isAVLTree() returned true", customBinaryNotSearchTree().isAVLTree());

        assertEquals("Wrong tree height returned:", 3, customBinaryNotSearchTree().getHeight());
        assertEquals("Wrong branching factor returned:", 2, customBinaryNotSearchTree().getBranchingFactor());
    }

    @Test(timeout = 20)
    public void customTest_binaryNotSearchTree2() {
        assertTrue("isTree() returned false", customBinaryNotSearchTree2().isTree());
        assertTrue("isBinaryTree() returned false", customBinaryNotSearchTree2().isBinaryTree());

        assertFalse("isBinarySearchTree() returned true", customBinaryNotSearchTree2().isBinarySearchTree());
        assertFalse("isMinHeap() returned true", customBinaryNotSearchTree2().isMinHeap());
        assertFalse("isMaxHeap() returned true", customBinaryNotSearchTree2().isMaxHeap());
        assertFalse("isAVLTree() returned true", customBinaryNotSearchTree2().isAVLTree());

        assertEquals("Wrong tree height returned:", 3, customBinaryNotSearchTree2().getHeight());
        assertEquals("Wrong branching factor returned:", 2, customBinaryNotSearchTree2().getBranchingFactor());
    }

    @Test(timeout = 20)
    public void customTest_treeList() {
        assertTrue("isTree() returned false", linkedListAsTree().isTree());
        assertTrue("isBinaryTree() returned false", linkedListAsTree().isBinaryTree());
        assertTrue("isMinHeap() returned false", linkedListAsTree().isMinHeap());
        assertFalse("isBinarySearchTree() returned true", linkedListAsTree().isBinarySearchTree());
        assertFalse("isAVLTree() returned true", linkedListAsTree().isAVLTree());
        assertFalse("isMaxHeap() returned true", linkedListAsTree().isMaxHeap());

        assertEquals("Wrong tree height returned:", 10, linkedListAsTree().getHeight());
        assertEquals("Wrong branching factor returned:", 1, linkedListAsTree().getBranchingFactor());
    }

    @Test(timeout = 20)
    public void customTest_emptyTreeWithNonNullValue() {
        assertTrue("isTree() returned false", emptyTreeWithNonNullValue().isTree());
        assertTrue("isBinaryTree() returned false", emptyTreeWithNonNullValue().isBinaryTree());
        assertTrue("isBinarySearchTree() returned false", emptyTreeWithNonNullValue().isBinarySearchTree());
        assertTrue("isAVLTree() returned false", emptyTreeWithNonNullValue().isAVLTree());
        assertTrue("isMinHeap() returned false", emptyTreeWithNonNullValue().isMinHeap());
        assertTrue("isMaxHeap() returned false", emptyTreeWithNonNullValue().isMaxHeap());

        assertEquals("Wrong tree height returned:", 0, emptyTreeWithNonNullValue().getHeight());
        assertEquals("Wrong branching factor returned:", 0, emptyTreeWithNonNullValue().getBranchingFactor());
    }

    @Test(timeout = 20)
    public void customTest_emptyTreeWithNullValue() {
        assertTrue("isTree() returned false", emptyTree().isTree());
        assertTrue("isBinaryTree() returned false", emptyTree().isBinaryTree());
        assertFalse("isBinarySearchTree() returned true", emptyTree().isBinarySearchTree());
        assertFalse("isAVLTree() returned true", emptyTree().isAVLTree());
        assertFalse("isMinHeap() returned true", emptyTree().isMinHeap());
        assertFalse("isMaxHeap() returned true", emptyTree().isMaxHeap());

        assertEquals("Wrong tree height returned:", 0, emptyTree().getHeight());
        assertEquals("Wrong branching factor returned:", 0, emptyTree().getBranchingFactor());
    }

    @Test(timeout = 20)
    public void customTest_mediumTreeUnsorted() {
        assertTrue("isTree() returned false", mediumTree(Mode.NON_NULL).isTree());
        assertTrue("isBinaryTree() returned false", mediumTree(Mode.NON_NULL).isBinaryTree());
        assertFalse("isBinarySearchTree() returned true", mediumTree(Mode.NON_NULL).isBinarySearchTree());
        assertFalse("isMinHeap() returned true", mediumTree(Mode.NON_NULL).isMinHeap());
        assertFalse("isMaxHeap() returned true", mediumTree(Mode.NON_NULL).isMaxHeap());
        assertFalse("isAVLTree() returned true", mediumTree(Mode.NON_NULL).isAVLTree());

        assertEquals("Wrong tree height returned:", 3, mediumTree(Mode.NON_NULL).getHeight());
        assertEquals("Wrong branching factor returned:", 2, mediumTree(Mode.NON_NULL).getBranchingFactor());
    }

    @Test(timeout = 20)
    public void customTest_mediumTreeSorted() {
        assertTrue("isTree() returned false", mediumTree(Mode.SORTED).isTree());
        assertTrue("isBinaryTree() returned false", mediumTree(Mode.SORTED).isBinaryTree());
        assertTrue("isBinarySearchTree() returned false", mediumTree(Mode.SORTED).isBinarySearchTree());

        assertFalse("isMinHeap() returned true", mediumTree(Mode.SORTED).isMinHeap());
        assertFalse("isMaxHeap() returned true", mediumTree(Mode.SORTED).isMaxHeap());
        assertFalse("isAVLTree() returned true", mediumTree(Mode.SORTED).isAVLTree());

        assertEquals("Wrong tree height returned:", 3, mediumTree(Mode.SORTED).getHeight());
        assertEquals("Wrong branching factor returned:", 2, mediumTree(Mode.SORTED).getBranchingFactor());
    }

    @Test(timeout = 20)
    public void customTest_mediumTreeAVL() {
        assertTrue("isTree() returned false", mediumTree(Mode.AVL).isTree());
        assertTrue("isBinaryTree() returned false", mediumTree(Mode.AVL).isBinaryTree());
        assertTrue("isBinarySearchTree() returned false", mediumTree(Mode.AVL).isBinarySearchTree());
        assertTrue("isAVLTree() returned false", mediumTree(Mode.AVL).isAVLTree());
        assertFalse("isMinHeap() returned true", mediumTree(Mode.AVL).isMinHeap());
        assertFalse("isMaxHeap() returned true", mediumTree(Mode.AVL).isMaxHeap());

        assertEquals("Wrong tree height returned:", 3, mediumTree(Mode.AVL).getHeight());
        assertEquals("Wrong branching factor returned:", 2, mediumTree(Mode.AVL).getBranchingFactor());
    }

    @Test(timeout = 20)
    public void customTest_mediumTreeWithNull() {
        assertTrue("isTree() returned false", mediumTree(Mode.SINGLE_NULL).isTree());
        assertTrue("isBinaryTree() returned false", mediumTree(Mode.SINGLE_NULL).isBinaryTree());

        assertFalse("isBinarySearchTree() returned true", mediumTree(Mode.SINGLE_NULL).isBinarySearchTree());
        assertFalse("isMinHeap() returned true", mediumTree(Mode.SINGLE_NULL).isMinHeap());
        assertFalse("isMaxHeap() returned true", mediumTree(Mode.SINGLE_NULL).isMaxHeap());
        assertFalse("isAVLTree() returned true", mediumTree(Mode.SINGLE_NULL).isAVLTree());

        assertEquals("Wrong tree height returned:", 3, mediumTree(Mode.NON_NULL).getHeight());
        assertEquals("Wrong branching factor returned:", 2, mediumTree(Mode.NON_NULL).getBranchingFactor());
    }

    @Test(timeout = 20)
    public void customTest_maximumHeap() {
        assertTrue("isMaxHeap() returned false!", maximumHeap().isMaxHeap());

        assertTrue("isTree() returned false", maximumHeap().isTree());
        assertTrue("isBinaryTree() returned false", maximumHeap().isBinaryTree());

        assertFalse("isMinHeap() returned true!", maximumHeap().isMinHeap());
        assertFalse("isBinarySearchTree() returned true!", maximumHeap().isBinarySearchTree());
        assertFalse("isAvlTree() returned true", maximumHeap().isAVLTree());
        assertEquals("Wrong tree height returned:", 3, maximumHeap().getHeight());
        assertEquals("Wrong branching factor returned:", 2, maximumHeap().getBranchingFactor());
    }

    @Test(timeout = 20)
    public void customTest_minimumHeap() {
        assertTrue("isMinHeap() returned false!", minimumHeapTree().isMinHeap());

        assertTrue("isTree() returned false", maximumHeap().isTree());
        assertTrue("isBinaryTree() returned false", maximumHeap().isBinaryTree());

        assertFalse("isMaxHeap() returned true!", minimumHeapTree().isMaxHeap());
        assertFalse("isBinarySearchTree() returned true!", minimumHeapTree().isBinarySearchTree());
        assertFalse("isAvlTree() returned true", minimumHeapTree().isAVLTree());
        assertSame("Wrong tree height returned:", 3, minimumHeapTree().getHeight());
        assertSame("Wrong branching factor returned:", 2, minimumHeapTree().getBranchingFactor());
    }

    @Test(timeout = 20)
    public void customTest_noHeaps() {
        assertTrue("isTree() returned false", nonMaximumHeapTree().isTree());
        assertTrue("isTree() returned false", nonMinimumHeapTree().isTree());

        assertTrue("isBinaryTree() returned false", nonMaximumHeapTree().isBinaryTree());
        assertTrue("isBinaryTree() returned false", nonMinimumHeapTree().isBinaryTree());

        assertFalse("isMaxHeap() returned true", nonMaximumHeapTree().isMaxHeap());
        assertFalse("isMinHeap() returned true", nonMaximumHeapTree().isMinHeap());

        assertFalse("isMinHeap() returned true", nonMinimumHeapTree().isMinHeap());
        assertFalse("isMinHeap() returned true", nonMinimumHeapTree().isMaxHeap());

        assertFalse("isAVLTree() returned true", nonMaximumHeapTree().isAVLTree());
        assertFalse("isAVLTree() returned true", nonMinimumHeapTree().isAVLTree());
    }

    @Test(timeout = 20)
    public void customTest_enhancedAVL() {
        assertTrue("Your isTree() method is not working", binarySearchTree_AVL().isTree());
        assertTrue("Your isBinaryTree() method is not working!", maximumHeap().isBinaryTree());
        assertTrue("Your isBinarySearchTree() method is not working!", binarySearchTree_AVL().isBinarySearchTree());
        assertTrue("Looks like you did not cover the special case in isAvlTree()", binarySearchTree_AVL().isAVLTree());

        assertFalse("Your isMinHeap() method is wrong.", binarySearchTree_AVL().isMinHeap());
        assertFalse("Your isMaxHeap() method is wrong.", binarySearchTree_AVL().isMaxHeap());

        assertEquals("Wrong branching factor returned:", 2, binarySearchTree_AVL().getBranchingFactor());
        assertEquals("Wrong height returned:", 3, binarySearchTree_AVL().getHeight());
    }

    @Test(timeout = 40)
    public void customTest_broadTree() {
        assertTrue("isTree() returned false", bigBoiTree().isTree());
        assertFalse("isBinaryTree() returned true", bigBoiTree().isBinaryTree());
        assertFalse("isBinarySearchTree() returned true", bigBoiTree().isBinarySearchTree());
        assertFalse("isMaxHeap() returned true", bigBoiTree().isMaxHeap());
        assertFalse("isMinHeap() returned true", bigBoiTree().isMinHeap());
        assertFalse("isAVLTree() returned true", bigBoiTree().isAVLTree());

        assertEquals("Wrong tree height returned:", 1, bigBoiTree().getHeight());
        assertEquals("Wrong branching factor returned:", 128, bigBoiTree().getBranchingFactor());
    }

    @Test(timeout = 750)
    public void customTest_longTree() {
        assertTrue("isTree() returned false", longBoiTree().isTree());
        assertTrue("isBinaryTree() returned false", longBoiTree().isBinaryTree());
        assertFalse("isBinarySearchTree() returned true", longBoiTree().isBinarySearchTree());
        assertFalse("isMaxHeap() returned true", longBoiTree().isMaxHeap());
        assertFalse("isMinHeap() returned true", longBoiTree().isMinHeap());
        assertFalse("isAVLTree() returned true", longBoiTree().isAVLTree());

        assertEquals("Wrong tree height returned:", 1024, longBoiTree().getHeight());
        assertEquals("Wrong branching factor returned:", 1, longBoiTree().getBranchingFactor());
    }

    @Test(timeout = 20)
    public void customTest_sameValuesTree() {
        assertTrue("isTree() returned false", sameValuesTree().isTree());
        assertFalse("isBinaryTree() returned true", sameValuesTree().isBinaryTree());
        assertFalse("isBinarySearchTree() returned true", sameValuesTree().isBinarySearchTree());
        assertFalse("isMinHeap() returned true", sameValuesTree().isMinHeap());
        assertFalse("isMaxHeap() returned true", sameValuesTree().isMaxHeap());
        assertFalse("isAVLTree() returned true", sameValuesTree().isAVLTree());
        assertEquals("Wrong branching factor returned:", 11, sameValuesTree().getBranchingFactor());
        assertEquals("Wrong tree height returned:", 10, sameValuesTree().getHeight());
    }

    private void forceChangeValue(String fieldName, Object o, BinNode<String> node) {
        try {
            Field valueField = node.getClass().getSuperclass().getDeclaredField(fieldName);
            valueField.setAccessible(true);
            valueField.set(node, o);
            valueField.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private enum Mode {
        NON_NULL, SINGLE_NULL, SORTED, AVL
    }

}