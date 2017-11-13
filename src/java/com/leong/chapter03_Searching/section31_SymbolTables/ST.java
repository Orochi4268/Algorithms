package com.leong.chapter03_Searching.section31_SymbolTables;

/**
 * @author leongfeng created on 2017-11-13.
 */
public class ST<Key extends Comparable<Key>, Value> extends BaseComparableBaseST<Key, Value> {
    @Override
    public void put(Key key, Value value) {

    }

    @Override
    public Key select(int key) {
        return null;
    }

    @Override
    public Value get(Key key) {
        return null;
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
    public int size() {
        return 0;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }

    @Override
    public Key ceiling(Key key) {
        return null;
    }

    /**
     * 小于 key 的键的数量.
     * @param key
     * @return
     */
    @Override
    public int rank(Key key) {
        return 0;
    }

    @Override
    public void deleteMin() {
        delete(min());
    }

    @Override
    public void deleteMax() {
        delete(max());
    }

    @Override
    public int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0){
            return rank(hi) - rank(lo) + 1;
        }
        return rank(hi) - rank(lo);
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return keys(min(), max());
    }
}
