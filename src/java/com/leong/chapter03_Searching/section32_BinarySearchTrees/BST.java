package com.leong.chapter03_Searching.section32_BinarySearchTrees;

import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Queue;
import com.leong.chapter03_Searching.BaseComparableBaseST;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.StdOut;


/**
 * 二叉查找树。
 *
 * @author leongfeng created on 2017/11/13.
 */
public class BST<Key extends Comparable<Key>, Value> extends BaseComparableBaseST<Key, Value> {
    /**
     * 根节点。
     */
    private Node root;

    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
        StdOut.println("S E A R C H E X A M P L E");
//        String[] strings = "S E A R C H E X A M P L E".split("\\s");
        String[] strings = "S E A R C H X M".split("\\s");
        for (int i = 0; i < strings.length; i++) {
            bst.put(strings[i], i);
        }
        StdOut.println("floor: " + bst.floor("G"));
        StdOut.println("ceiling: " + bst.ceiling("G"));
        StdOut.println("select(3):" + bst.select(3));
//        bst.deleteMin();
//        bst.deleteMax();
        StdOut.println();
        bst.print(bst.root);
        StdOut.println();
        bst.delete("E");
        for (String key : bst.keys()){
            StdOut.print(key + " ");
        }
        StdOut.println();
        StdOut.println(bst.get("Y"));
    }

    /**
     * 如果根结点的左链接为空，那么最小值就是根结点；否则，是左子树中的最小键。
     *
     * @return 最小值
     */
    @Override
    public Key min() {
        if (isEmpty()) {
            return null;
        }
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    /**
     * 和 {@linkplain #min()} 相反，递归查询右子树.
     *
     * @return 最大键
     */
    @Override
    public Key max() {
        if (isEmpty()) {
            return null;
        }
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    /**
     * 如果 {@code key} 小于根结点的键，那么<=key 的最大键公共频道在左子树中；反之，则在右子树中.
     *
     * @param key
     * @return
     */
    @Override
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, key);
        }
        // 查找右子树
        Node t = floor(x.right, key);
        // 如果在右子树找到对应的key，返回此t结点；否则
        if (t != null) {
            return t;
        } else {
            return x;
        }

    }

    /**
     * 如果给定的键大于等于根节点，那么大于等于{@code key}的键一定在右子树中；反之，只有左子树中存在大于等于{@code key}
     * 的key的最小键才会出现在左子树中。
     *
     * @param key
     * @return
     */
    @Override
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp > 0) {
            return ceiling(x.right, key);
        }
        Node t = ceiling(x.left, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    /**
     * {@linkplain #rank(Comparable)} 和 {@linkplain #select(int)} 互为逆方法，它会返回给定 {@code key} 的排名：<br/>
     * 1. 如果 {@code key} 和根结点的键相等，返回左子树中的结点总数；<br/>
     * 2. 如果 {@code key} 小于根结点，返回该键在左子树中的排名（递归计算）；<br/>
     * 3. 如果 {@code key} 大于根结点，返回t+1（根结点）加上它在右子树中的排名（递归计算）.<br/>
     *
     * @param key key
     * @return int
     */
    @Override
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(x.left, key);
        }
        if (cmp > 0) {
            return 1 + size(x.left) + rank(x.right, key);
        }
        return size(x.left);
    }

    /**
     * 找到排名为{@code k}的键（树中正好有k个小于它的键）：<br/>
     * 1. 如果左子树的中的结点数t大于k,那么我们继续（递归地）在左子树中查找；<br/>
     * 2. 如果t等于k，那么返回返回根结点的键(Key)；<br/>
     * 3. 如果t小于k，就（递归地）在右子树中查找排名为(k-t-1)的键。<br/>
     *
     * @param k 排名
     * @return 排名为 k 的 key
     */
    @Override
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            return null;
        }
        Node x = select(root, k);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        }
        if (t < k) {
            return select(x.right, k - t - 1);
        }
        return x;
    }

    /**
     * 二叉查找树最难实现的方法，在删除结点 x 后用它的后继结点填补它的位置。由于 x 有一个右子结点，因此它的后继结点就是
     * 其右子树中的最小结点。这样的替换能够保证树的有序性。
     * <ol>
     * <li>将指向即将被删除的结点的链接保存为 t；</li>
     * <li>将 x 指向它的后继结点 min(t.right)；</li>
     * <li>将 x 的右链接指向 deleteMin(t.right)，也就是删除后所有的结点扔然大于 x.key 的子二对查找树；</li>
     * <li>将 x 的左链接（本为空）设为 t.left（其下所有的键都小于被删除的结点和它的后继结点）。</li>
     * </ol>
     * 递归删除后需要修正被删除的结点的父结点的链接，并将由此结点到根结点的路径上的所有结点的计数器减1。
     *
     * @param key 被删除的 key
     */
    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            x.left = delete(x.left, key);
        } else if (cmp > 0){
            x.right = delete(x.right, key);
        } else {
            if (x.right == null){
                return x.left;
            }
            if (x.left == null){
                return x.right;
            }
            // 1. 被删除的结点
            Node t = x;
            // 2. x 指向后续结点
            x = min(t.right);
            // 3. x 的右链接指向右子树最小结点
            x.right = deleteMin(t.right);
            // 4. x 的左链接指向原结点的左链接
            x.left = t.left;
        }
        // 更新父结点计数器
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * 不断递归根结点的左子树直至遇到一个空链接，然后将指向该结点的链接指向该结点的右子树；
     * 递归调用后更新链接和结点计数器。
     */
    @Override
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public int size(Key lo, Key hi) {
        return 0;
    }

    /**
     * 使用 Queue 来收集所有的 key， 并返回.
     * {@inheritDoc}
     * @return key queue
     */
    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null){
            return;
        }
        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);
        if (cmpLo < 0){
            keys(x.left, queue, lo, hi);
        }
        if (cmpLo <=0 && cmpHi >= 0){
            queue.enqueue(x.key);
        }
        if (cmpHi > 0){
            keys(x.right, queue, lo, hi);
        }
    }

    /**
     * @param key
     * @param value
     */
    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.val = value;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node root) {
        if (root == null) {
            return 0;
        }
        return root.N;
    }

    /**
     * lo 和 hi 之间有多少个元素.
     * @param lo
     * @param hi
     * @return
     */
    public int sie(Key lo, Key hi){
        if (contains(hi)){
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }


    private void print(Node x){
        if (x==null) {
            return;
        }
        print(x.left);
        StdOut.print(x.key + " ") ;
        print(x.right);
    }

    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        /**
         * 该结点为根的子树中的结点总数。
         */
        private int N;

        /**
         * @param key key
         * @param val value
         * @param n   size of nodes
         */
        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            N = n;
        }
    }
}
