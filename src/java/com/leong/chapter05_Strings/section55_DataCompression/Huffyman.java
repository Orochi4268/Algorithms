package com.leong.chapter05_Strings.section55_DataCompression;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.MinPQ;

/**
 * 霍夫曼压缩.
 * @author leongfeng created on 2017/12/14.
 */
public class Huffyman {

    // alphabet size of extended ASCII
    private static final int R = 256;
    private static class Node implements Comparable<Node>  {
        /**
         * 叶子结点中需要被编码的字符，内部结点不会使用该变量.
         */
        private char ch;
        /**
         * 字符出现的频率？子树的路径长度？展开过程不会使用该变量.
         */
        private int freq;

        /**
         * 左右结点.
         */
        private final Node left, right;

        /**
         * 结点构造函数.
         * @param left 左子结点
         * @param right 右子结点
         * @param ch 叶子结点需要被编码的字符
         * @param freq 字符出现的频率
         */
        Node(final Node left, final Node right, final char ch, final int freq) {
            this.left = left;
            this.right = right;
            this.ch = ch;
            this.freq = freq;
        }

        /**
         * 判断是否是叶子结点.
         * @return boolean
         */
        public boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(final Node that) {
            return this.freq - that.freq;
        }
    }

    public static void compress() {
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();

        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++) {
            freq[input[i]]++;
        }
        // 构建huffman树
        Node root = buildTrie(freq);

        String[] st = new String[R];
        buildCode(st, root, "");

        writeTrie(root);

        BinaryStdOut.write(input.length);

        // 使用Huffman编码处理输入
        for (int i = 0; i < input.length; i++) {
            String code = st[input[i]];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '0') {
                    BinaryStdOut.write(false);
                } else if (code.charAt(j) == '1') {
                    BinaryStdOut.write(true);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        }
        BinaryStdOut.close();
    }

    /**
     *
     * @param x
     */
    private static void writeTrie(Node x) {
        if (x.isLeaf()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.ch);
            return;
        }
        BinaryStdOut.write(false);
        writeTrie(x.left);
        writeTrie(x.right);
    }


    /**
     * 使用前缀码展开.
     */
    public static void expand() {
        Node root = readTrie();
        int N = BinaryStdIn.readInt();
        for (int i = 0; i < N; i++) {
            Node x = root;
            while (!x.isLeaf()) {
                if (BinaryStdIn.readBoolean()) {
                    x = x.right;
                } else {
                    x = x.left;
                }
                BinaryStdOut.write(x.ch);
            }
        }
        BinaryStdOut.close();

    }

    private static String[] buildCode(Node root) {
        String[] st = new String[R];
        buildCode(st, root, "");
        return st;
    }

    private static void buildCode(String[] st, Node x, String s) {
        if (x.isLeaf()) {
            st[x.ch] = s;
            return;
        }
        buildCode(st, x.left, s + '0');
        buildCode(st, x.right, s + '1');

    }

    private static Node readTrie() {
        boolean isLeaf = BinaryStdIn.readBoolean();
        if (isLeaf) {
            return new Node(null, null, BinaryStdIn.readChar(), -1);
        }
        return new Node(readTrie(), readTrie(), '\0', -1);
    }


    private static Node buildTrie(int[] freq) {
        MinPQ<Node> pq = new MinPQ<>();
        for (char c = 0; c < R; c++) {
            if (freq[c] > 0) {
                pq.insert(new Node(null, null, c, freq[c]));
            }
        }
        // 合并频率最小的树
        while (pq.size() > 1) {
            Node x = pq.delMin();
            Node y = pq.delMin();
            Node parent = new Node(x, y, '\0', x.freq + y.freq);
            pq.insert(parent);
        }
        return pq.delMin();
    }

    public static void main(String[] args) {

        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new RuntimeException("Illegal command line argument");
    }
}
