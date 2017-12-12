package com.leong.chapter05_Strings.section52_Tries;

import com.leong.chapter05_Strings.StringData;
import edu.princeton.cs.algs4.Queue;

import java.util.HashMap;

import static edu.princeton.cs.algs4.StdOut.print;
import static edu.princeton.cs.algs4.StdOut.println;

/**
 * 单词查找树（Trie）.
 *
 * @author leongfeng created on 2017/12/11.
 */
public class StringST<Value> {

    /**
     * 基数.
     */
    private static final int R = 256;

    /**
     * 根节点.
     */
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    /**
     * Constructor.
     */
    public StringST() {

    }

    /**
     * 向表中插入键值对，如果值为 null，则删除 key.
     *
     * @param key key - key with which the specified value is to be associated
     * @param val value - value to be associated with the specified key
     */
    public void put(final String key, final Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) {
            x = new Node();
        }
        // 值保存在最后一个结点
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    /**
     * 键对应的值.
     *
     * @param key key of value
     * @return value
     */
    public Value get(final String key) {
        if (isEmpty()){
            return null;
        }
        final Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return (Value) x.val;
    }

    /**
     * 递归查找 key 值.
     *
     * @param x   结点
     * @param key key
     * @param d   第 d 个字符
     * @return
     */
    private Node get(final Node x, final String key, final int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        // 找到第d个字符
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    /**
     * 删除键key 和它对应的值.
     *
     * @param key key
     */
    public void delete(final String key) {

    }

    /**
     * 量少保存着key的值.
     *
     * @param key key
     * @return trure if key in this ST
     */
    public boolean contains(final String key) {
        return get(key) != null;
    }

    /**
     * 是否为空.
     *
     * @return true if is empty
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * s 的前缀中最长的键.<br/>
     * 对于 she sells seashells by the sea shore,
     * 如果 longestPrefixOf("shell") 的结果是 she, longestPrefixOf("shellsort") 结果是 shells.
     *
     * @param s 字符串
     * @return key
     */
    public String longestPrefixOf(final String s) {
        return "";
    }

    /**
     * 所有以 s 为前缀的键.<br/>
     * keysWithPrefix("she") is she and shells, and keysWithPrefix("se") is sells and sea.
     *
     * @param pre 前缀
     * @return pre 为前缀的键
     * @see #keysWithPrefix(String)
     */
    public Iterable<String> keysWithPrefix(final String pre) {
        Queue<String> queue = new Queue<>();
        collect(get(root, pre, 0), pre, queue);
        return queue;
    }

    /**
     * 递归遍历所有key. 在每次调用之前，都将链接对应的字符附加到当前键的末尾作为调用的参数键 pre.
     * @param x 结点
     * @param pre 键前缀
     * @param queue 存储队列
     */
    private void collect(Node x, String pre, Queue<String> queue) {
        if (x == null){
            return;
        }
        // 到达一个单词末尾，放入队列中
        if (x.val != null){
            queue.enqueue(pre);
        }
        // 遍历所有结点
        for (char c = 0; c < R; c++){
            collect(x.next[c], pre + c, queue);
        }
    }

    /**
     * 所有和 s 匹配的键（其中“.”匹配任意字符）.<br/>
     * keysThatMatch(".he") returns she and the, and keysThatMatch("s..") returns she and sea
     *
     * @param s 表达式
     * @return s 匹配的键
     * @see #keysWithPrefix(String)
     */
    public Iterable<String> keysThatMatch(final String pat) {
        Queue<String> queue = new Queue<>();
        collect(root, "", pat, queue);
        return queue;
    }

    private void collect(Node x, String pre, String pat, Queue<String> queue) {
        if (x == null){
            return;
        }
        int d = pre.length();
        if (d == pat.length() && x.val != null){
            queue.enqueue(pre);
        }
        if (d == pat.length()){
            return;
        }
        char next = pat.charAt(d);
        for (char c = 0; c < R; c++){
            if (next == '.' || next == c){
                collect(x.next[c], pre + c, pat, queue);
            }
        }
    }


    /**
     * size of ST.
     *
     * @return int
     */
    public int size() {
        return 0;
    }

    /**
     * 所有的键.
     *
     * @return all keys
     */
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }


    public static void main(final String[] args) {
        StringST<Integer> st = new StringST<>();
        String[]  a = StringData.fromFilename("shells.txt").readAllStrings();
        for (int i = 0; i < a.length; i++){
            st.put(a[i], i);
        }
        println(st.get(a[0]));
        for (String s : st.keysWithPrefix("")){
            println(s);
        }
        println("-----------------------------match-----------------");
        for (String s : st.keysThatMatch("she")){
            println(s);
        }
    }
}
