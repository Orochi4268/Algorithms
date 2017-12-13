package com.leong.chapter05_Strings.section52_Tries;

import edu.princeton.cs.algs4.Queue;

/**
 * 三向单词查找树（Ternary search tries）.
 * 空间：由 N 个平均长度为 w 的字符串构造的 TST 中链接总数在 3N~3Nw 之间；
 * 查找成本：未命中平均需要比较 ~lnN 次，
 * @author leongfeng created on 2017-12-13.
 */
public class TST<Value> {
    /**
     * 根节点
     */
    private Node root;

    private class Node {
        char c;
        Value val;
        /**
         * 首字母较小的
         */
        Node left;
        /**
         * 相等
         */
        Node mid;
        /**
         * 较大
         */
        Node right;
    }

    public Value get(String key) {
        if (isEmpty()){
            return null;
        }
        Node x = get(root, key, 0);
        if (x == null){
            return null;
        }
        return x.val;
    }

    private Node get(Node x, String key, int d) {
        // 节点为空
        if (x == null){
            return null;
        }
        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c){
            return get(x.right, key, d);
        } else if (d < key.length() - 1){
            // 匹配到其中一个字符
            return get(x.mid, key, d + 1);
        } else {
            return x;
        }
    }

    public void put(String key, Value val){
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null){
            return null;
        }
        int c = key.charAt(d);
        if (c < x.c){
            x.left = put(x.left, key, val, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            x.mid = put(x.mid, key, val, d);
        } else {
            x.val = val;
        }
        return x;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public Iterable<String> keysWithPrefix(String pre){
        Queue<String> queue = new Queue<>();
        return queue;
    }

    public Iterable<String> keysThatMatch(String pre) {
        Queue<String> queue = new Queue<>();

        return queue;
    }

}
