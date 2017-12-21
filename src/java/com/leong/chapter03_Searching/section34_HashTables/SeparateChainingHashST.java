package com.leong.chapter03_Searching.section34_HashTables;

import com.leong.chapter03_Searching.section31_SymbolTables.SequentialSearchST;
import edu.princeton.cs.algs4.Queue;

/**
 * 拉链法：
 * 链表的平均长度： N/M。
 *
 * @author leongfeng created on 2017-12-21.
 */
public class SeparateChainingHashST<Key extends Comparable<Key>, Value> {
    /**
     * 键值对总数.
     */
    private int N;
    /**
     * 散列表的大小.
     */
    private int M;

    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int M) {
        this.M = M;
        this.st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return this.st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        this.st[hash(key)].put(key, value);
    }

    public void delete(Key key) {
        this.st[hash(key)].delete(key);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();

        return queue;
    }
}
