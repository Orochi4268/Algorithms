package com.leong.chapter03_Searching.section31_SymbolTables;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

/**
 * @author leongfeng created on 2017-11-13.
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> extends BaseComparableBaseST<Key, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    private void resize(int size) {

    }

    @Override
    public Key min() {
        if (isEmpty()) {
            return null;
        }
        return keys[0];
    }

    @Override
    public Key max() {
        if (isEmpty()) {
            return null;
        }
        return keys[N - 1];
    }

    /**
     * 小于等于 key 的最大键
     *
     * @param key
     * @return
     */
    @Override
    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) {
            return keys[i];
        }
        if (i == 0) {
            return null;
        }
        return keys[i - 1];
    }

    /**
     * 大于等于 key 的最小键
     *
     * @param key
     * @return
     */
    @Override
    public Key ceiling(Key key) {
        int i = rank(key);
        if (i == N) {
            return null;
        }
        return keys[i];
    }

    /**
     * 核心方法。
     *
     * @param key key
     * @return 返回表中小于 key 的键的数量
     */
    @Override
    public int rank(Key key) {
        /*int lo = 0, hi = N-1;
        while (lo <= hi) {
            int m = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[m]);
            if      (cmp < 0) hi = m - 1;
            else if (cmp > 0) lo = m + 1;
            else return m;
        }
        return lo;*/
        return rank(key, 0, N - 1);
    }

    private int rank(Key key, int lo, int hi) {
        if (hi < lo) {
            return lo;
        }
        int mid = (lo + hi) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0) {
            return rank(key, 0, mid - 1);
        } else if (cmp > 0) {
            return rank(key, mid + 1, hi);
        } else {
            return mid;
        }
    }

    @Override
    public Key select(int key) {
        return keys[key];
    }

    @Override
    public void deleteMin() {
        for (int i = N; i > 0; N--) {
            keys[i - 1] = keys[i];
            vals[i - 1] = vals[i];
        }
        N--;
    }

    @Override
    public void deleteMax() {
        keys[N - 1] = null;
        vals[N - 1] = null;
        N--;
    }

    @Override
    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) {
            return 0;
        }
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        }
        return rank(hi) - rank(lo);
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++) {
            queue.enqueue(keys[i]);
        }
        if (contains(hi)) {
            queue.enqueue(keys[rank(hi)]);
        }
        return queue;
    }


    @Override
    public void put(Key key, Value value) {
        int i = rank(key);
        // key 存在
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = value;
            return;
        }
        // 不存在，移动后面元素
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = value;
        N++;
    }

    @Override
    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        }
        return null;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<>(10);
        st.put("a", 1);
        st.put("d", 4);
        st.put("c", 3);
        st.put("b", 2);
        for (String key : st.keys()) {
            StdOut.println(key + " " + st.get(key));
        }

    }
}
