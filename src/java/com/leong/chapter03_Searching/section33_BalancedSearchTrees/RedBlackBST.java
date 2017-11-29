package com.leong.chapter03_Searching.section33_BalancedSearchTrees;

import com.leong.chapter03_Searching.BaseComparableBaseST;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author leongfeng created on 2017/11/21.
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> extends BaseComparableBaseST<Key, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    private class Node{
        Key key;
        Value val;
        Node left;
        Node right;
        int N;
        /**
         * 父结点指向它的链接的颜色
         */
        boolean color;

        public Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            N = n;
            this.color = color;
        }
    }

    private boolean isRed(Node x){
        if (x == null){
            return false;
        }
        return x.color == RED;
    }

    /**
     * 将一条红色的右链接转化为左链接.
     * @param h
     * @return
     */
    Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        // 保留原来的颜色
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * 右旋转.
     * @param h
     * @return
     */
    Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        // 保留颜色
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * 返回颜色
     * @param h
     */
    void flipColors(Node h){
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    private int size(Node root) {
        if (root == null) {
            return 0;
        }
        return root.N;
    }

    @Override
    public Key min() {
        return null;
    }

    @Override
    public Key max() {
        return null;
    }

    @Override
    public Key floor(Key key) {
        return null;
    }

    @Override
    public Key ceiling(Key key) {
        return null;
    }

    @Override
    public int rank(Key key) {
        return 0;
    }

    @Override
    public Key select(int k) {
        return null;
    }

    @Override
    public void delete(Key key) {
        if (isEmpty()){
            throw new RuntimeException("BST underflow");
        }
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = delete(root, key);
        if (!isEmpty()){
            root.color = BLACK;
        }
    }

    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0){
            if (!isRed(h.left) && !isRed(h.left.right)){
                h = moveRedLeft(h);
            }
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left)){
                h = rotateRight(h);
            }
            if (key.compareTo(h.key) == 0 && h.right == null){
                return null;
            }
            if (!isRed(h.right) && !isRed(h.right.left)){
                h = moveRedRight(h);
            }
            if (key.compareTo(h.key) == 0){
                h.val = get(h.right, min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            } else {
                h.right = delete(h.right, key);
            }
        }
        return balance(h);
    }


    private Node min(Node h) {
        return h;
    }

    /**
     * 从2-结点中删除一个键会留下一个空结点，会破坏树的完美平衡性。因此为了保证还会删除一个2-结点，我们沿着左链接向下进行
     * 变换，确保当前结点不是2-结点（可以是3/4-结点）。<br/>
     * 根结点有可能有两种情况:<br/>
     *  a. 根结点和它的两个子结点都是2-结点，那么直接将这三个结点变成一个4-结点；<br/>
     *  b. 为了保证左子结点不是2-结点，如有必要可以从它的右侧的兄弟结点借来一个key来。<br/>
     * 沿着左链接向下的过程中，需要保证以下情况之一成立：
     * <ul>
     *     <li>1. 左子结点不是2-结点，完成；</li>
     *     <li>2. 左子结点是2-结点，而它（左子结点）的兄弟结点不是2-结点，将兄弟结点中的一个键移动到左子结点中；</li>
     *     <li>3. 左子结点和兄弟结点都是2-结点，将左子结点、父结点中的最小键和左子结点最近的兄弟结点合并为一个4-结点，
     *     使得父结点由3/4-结点变为2/3-结点</li>
     * </ul>
     * 最后能够得到一个含有最小键的3/4-结点，然后我们就可以直接从中将其删除。然后再回头向上分解所有临时的4-结点。
     */
    @Override
    public void deleteMin() {
        if (isEmpty()){
            throw new RuntimeException("BST underflow");
        }
        // a
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        // b
        root = deleteMin(root);
        if (!isEmpty()){
            root.color = BLACK;
        }
    }

    /**
     * b. 沿左子结点向下。{@linkplain #deleteMin()}
     * @param h
     * @return
     */
    private Node deleteMin(Node h) {
        if (h.left == null){
            return h;
        }
        if (!isRed(h.left) && !isRed(h.left.left)){
            h = moveRedLeft(h);
        }
        h = deleteMin(h.left);
        return balance(h);
    }

    /**
     * 沿左子结点向下过程的实现.
     *
     * @param h
     * @return
     */
    private Node moveRedLeft(Node h) {
        /**
         * 假设h为绝，h.left和h.left.left都是黑色，将h.left或它的子结点之一变红.
         */
        flipColors(h);
        if (isRed(h.right.left)){
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }

    private Node balance(Node h) {
        if (isRed(h.right)){
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)){
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)){
            flipColors(h);
        }
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }


    @Override
    public void deleteMax() {
        if (isEmpty()){
            throw new RuntimeException("BST underflow");
        }
        if (!isRed(root) && !isRed(root)){
            root.color = RED;
        }
        root = deleteMax(root);
        if (!isEmpty()){
            root.color = BLACK;
        }
    }

    private Node deleteMax(Node h) {
        if (isRed(h.left)){
            h = rotateRight(h);
        }
        if (h.right == null){
            return h;
        }
        if (!isRed(h.right) && !isRed(h.right.right)){
            h = moveRedRight(h);
        }
        h.right = deleteMax(h.right);
        return balance(h);
    }

    private Node moveRedRight(Node h) {
        // 假设结点h为红色，h.right和h.right.left都是黑色，将h.right或者h.right的子结点之一变红.
        flipColors(h);
        if (!isRed(h.left.left)) {
            h = rotateRight(h);
        }
        return h;
    }

    @Override
    public int size(Key lo, Key hi) {
        return 0;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    /**
     * 关键操作步骤：要在一个3-结点下插入新键，先创建一个临时的4-结点，将其分解并将红链接由中间键传递给它的父结点。
     * 重复这个过程，我们就能将红链接在树中向上传递，直至遇到一个2-结点或者根结点。
     * 每个节点都需要完成的操作：
     * <ul>
     *     <li>1. rotateLeft: h.right.color 是红色； h.left.color 是黑色；</li>
     *     <li>2. rotateRight: h.left.color 和 h.left.left.color 都是红色；</li>
     *     <li>3. flipColors: h.left.color 和 h.right.color 都是红色</li>
     * </ul>
     * @param h
     * @param key
     * @param value
     * @return
     */
    private Node put(Node h, Key key, Value value) {
        if (h == null){
            new Node(key, value, 1, RED);
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0){
            put(h.left, key, value);
        }
        if (cmp > 0){
            put(h.right, key, value);
        }
        if (cmp == 0){
            h.val = value;
        }
        // 情况1：左旋转
        if (isRed(h.right) && !isRed(h.left)){
            h = rotateLeft(h);
        }
        // 情况2：右旋转
        if (isRed(h.left) && isRed(h.left.left)){
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)){
            flipColors(h);
        }
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }
    private Value get(Node x, Key key) {
        if (x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            return get(x.left, key);
        } else if (cmp > 0){
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    private Value getByWhile(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0){
                x = x.left;
            } else if (cmp > 0){
                x = x.right;
            } else {
                return x.val;
            }
        }
        return null;
    }

    @Override
    public Value get(Key key) {
        if (isEmpty()){
            throw new RuntimeException("BST underflow");
        }
        return get(root, key);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }


    /*************************************************************************
     *  Check integrity of red-black BST data structure
     *************************************************************************/

    private boolean check(){
        if (!isBST()){
            StdOut.println("Not fromFilename symmetric order");
            return false;
        }
        if (!isSizeConsistent()){
            StdOut.println("Subtree counts not consistent");
            return false;
        }
        if (!isRankConsistent()){
            StdOut.println("Rank not consistent");
            return false;
        }
        if (!is23()){
            StdOut.println("Not a 2-3 tree");
            return false;
        }
        if (!isBalanced()){
            StdOut.println("Not balanced");
            return false;
        }
        return true;
    }

    private boolean isBalanced() {
        int black = 0;
        Node x = root;
        while (x != null){
            if (!isRed(x)){
                black ++;
            }
            x = x.left;
        }
        return isBlanaced(root, black);
    }

    private boolean isBlanaced(Node x, int black) {
        if (x == null){
            return black == 0;
        }
        if (!isRed(x)){
            black --;
        }
        return isBlanaced(x.left, black) && isBlanaced(x.right, black);
    }

    private boolean is23() {
        return is23(root);
    }

    private boolean is23(Node x) {
        if (x == null){
            return true;
        }
        if (isRed(x.right)){
            return false;
        }
        if (x != root && isRed(x) && isRed(x.left)){
            return false;
        }
        return is23(x.left) && is23(x.right);
    }

    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++){
            if (i != rank(select(i))){
                return false;
            }
        }
        for (Key key : keys()){
            if (key.compareTo(select(rank(key))) != 0){
                return false;
            }
        }
        return true;
    }

    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null){
            return true;
        }
        if (x.N != size(x.left) + size(x.right) + 1){
            return false;
        }
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    private boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max) {
        if (x == null){
            return true;
        }
        if (min != null && x.key.compareTo(min) <= 0){
            return false;
        }
        if (max != null && x.key.compareTo(max) >= 0){
            return false;
        }
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }
}
