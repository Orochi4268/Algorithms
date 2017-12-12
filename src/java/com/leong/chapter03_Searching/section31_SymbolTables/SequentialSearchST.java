package com.leong.chapter03_Searching.section31_SymbolTables;


import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Queue;
import com.leong.chapter03_Searching.BaseST;

/**
 * 顺序查找（基于无序链表）
 * 未命中的查找和插入操作都需要 N 次比较，命中的查找的最坏情况也是 N 次比较。
 * 向一个空表中插入 N 个不同的键需要 ~ N^2/2 次比较。
 * @author leongfeng created on 2017-11-13.
 */
public class SequentialSearchST<Key extends Comparable<Key>, Value> extends BaseST<Key, Value> {
    private int N = 0;
    private Node first;

    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public void put(Key key, Value value) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        N++;
    }

    @Override
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (Node x = first; x != null; x = x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }


    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.equals(x.key)) {
            N--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    @Override
    public void delete(Key key) {
        first = delete(first, key);
    }

    public static void main(String[] args) {
    }

}
