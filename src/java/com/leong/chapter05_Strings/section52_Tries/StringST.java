package com.leong.chapter05_Strings.section52_Tries;

import com.leong.chapter05_Strings.StringData;
import edu.princeton.cs.algs4.Queue;

import static edu.princeton.cs.algs4.StdOut.print;
import static edu.princeton.cs.algs4.StdOut.printf;
import static edu.princeton.cs.algs4.StdOut.println;

/**
 * 单词查找树（Trie）.
 * 查找和插入操作时间：在Trie中查找或插入一个键时，访问数组的次数最多为键的长度加1；<br/>
 * 时间：字母表为大小 R，在一棵由 N 个随机键构造的单词查找树中，未命中查找平均所需检查的结点数量为 ~log(下标R)N，
 *      查找未命中的成本与键的长度无关；<br/>
 * 空间：一棵Trie中链接总数在 RN 和 RNw 之间，其中 w 为键的平均长度，缩小 R 可以节省大量空间。
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

    /**
     * 插入单词.
     *
     * @param x   节点
     * @param key 键
     * @param val 值
     * @param d   第 d 个字符
     * @return
     */
    private Node put(Node x, final String key, final Value val, final int d) {
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
        if (isEmpty()) {
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
        root = delete(root, key, 0);
    }

    /**
     * 递归删除某个结点x之后，如果该结点的值和所有的链接均为空，则返回null.
     *
     * @param x   结点
     * @param key key
     * @param d   dth char
     * @return x
     */
    private Node delete(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        // 键的末尾
        if (d == key.length()) {
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        // 非空值， she(0) 中的 e(d:3) -> x.value = 0
        if (x.val != null) {
            return x;
        }

        /**
         * 非空链接递归到根节点时返回: she -> h(d:2) -> s(d:0 还有sea)
         */
        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        //递归返回值，值和链接均为空：shells -> s(d:6-1) -> l(d:5-1) -> l(d:4-1)
        return null;
    }

    /**
     * 量少保存着key的值.
     *
     * @param key key
     * @return true if key in this ST
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
     * 找出最长的 key 的 length.
     *
     * @param s 字符串
     * @return 最长的 key
     */
    public String longestPrefixOf(final String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    /**
     * 找出最长键的长度.
     * 查找会在被查找的字符串结束或者遇到空链接时终止.
     *
     * @param x      结点
     * @param s      需要查找的字符串
     * @param d      第d个字符
     * @param length 最长长度
     * @return 最长长度
     */
    private int search(final Node x, final String s, final int d, int length) {
        if (x == null) {
            return length;
        }
        if (x.val != null) {
            length = d;
        }
        if (d == s.length()) {
            return length;
        }
        char c = s.charAt(d);
        return search(x.next[c], s, d + 1, length);
    }

    /**
     * 所有以 s 为前缀的键；如果为空字符串""，则返回全部键.<br/>
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
     *
     * @param x     结点
     * @param pre   键前缀
     * @param queue 存储队列
     */
    private void collect(final Node x, final String pre, final Queue<String> queue) {
        if (x == null) {
            return;
        }
        // 到达一个单词末尾，放入队列中
        if (x.val != null) {
            queue.enqueue(pre);
        }
        // 遍历所有结点
        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre + c, queue);
        }
    }

    /**
     * 所有和 pat 匹配的键（其中“.”匹配任意一个字符）.<br/>
     * keysThatMatch(".he") returns she and the, and keysThatMatch("s..") returns she and sea
     *
     * @param pat 表达式
     * @return s 匹配的键
     * @see #keysWithPrefix(String)
     */
    public Iterable<String> keysThatMatch(final String pat) {
        Queue<String> queue = new Queue<>();
        collect(root, "", pat, queue);
        return queue;
    }

    /**
     * @see #keysThatMatch(String)
     */
    private void collect(final Node x, final String pre, final String pat, final Queue<String> queue) {
        if (x == null) {
            return;
        }
        int d = pre.length();
        if (d == pat.length() && x.val != null) {
            queue.enqueue(pre);
        }
        if (d == pat.length()) {
            return;
        }

        char next = pat.charAt(d);
        for (char c = 0; c < R; c++) {
            if (next == '.' || next == c) {
                collect(x.next[c], pre + c, pat, queue);
            }
        }
    }


    /**
     * 返回所有的键.
     *
     * @return all keys
     * @see #keysWithPrefix(String)
     */
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }


    public static void main(final String[] args) {
        StringST<Integer> st = new StringST<>();
        String[] a = StringData.fromFilename("shells.txt").readAllStrings();
        for (int i = 0; i < a.length; i++) {
            st.put(a[i], i);
        }
        for (String s : st.keys()) {
            printf("%s %d\n", s, st.get(s));
        }
        println("------prefix------------");
        for (String s : st.keysWithPrefix("she")) {
            println(s);
        }
        println("-----------------------------match-----------------");
        for (String s : st.keysThatMatch("sh.")) {
            println(s);
        }
        st.delete("shells");
    }
}
