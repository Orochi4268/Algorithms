package com.leong.chapter03_Searching.section34_HashTables;

/**
 * @author leongfeng created on 2017-12-21.
 */
public class LinearProbingHashST<Key, Value> {

    private static final int INIT_CAPACITY = 4;
    private int N;
    private int M = 16;
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    public LinearProbingHashST(int capacity) {
        this.M = capacity;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], values[i]);
            }
        }
        keys = temp.keys;
        values = temp.values;
        M    = temp.M;
    }

    public void put(Key key, Value value) {
        if (N > M/2) {
            resize(2 * M);
        }
        int i = hash(key);
        for (; keys[i] != null; i = (i+1) % M) {
            if (key.equals(keys[i])) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    public void delete(Key key) {
        if (!contains(key)) {
            return;
        }
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i+1) % M;
        }
        keys[i] = null;
        values[i] = null;
        i = (i+1) % M;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = values[i];
            keys[i] = null;
            values[i] = null;
            N--;
            put(keyToRedo, valToRedo);
            i = (i+1) % M;
        }
        N--;
        if (N > 0 && N == M/8) {
            resize(M/2);
        }
    }

    private boolean contains(Key key) {
        return get(key) != null;
    }

    private Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }
}
