package com.leong.chapter03_Searching.section31_SymbolTables;

import com.leong.chapter03_Searching.BaseComparableBaseST;

import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author leongfeng created on 2017-11-13.
 */
public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    private TreeMap<Key, Value> st;

    public ST(){
        st = new TreeMap<>();
    }

    public void put(Key key, Value value) {
        if (value == null) {
            st.remove(key);
        }
        st.put(key, value);
    }


    public Value get(Key key) {
        return st.get(key);
    }

    public Key min() {
        return st.firstKey();
    }

    public Key max() {
        return st.lastKey();
    }

    public Key floor(Key key) {
        if (st.containsKey(key)){
            return key;
        }
        SortedMap<Key, Value> head = st.headMap(key);
        if (head.isEmpty()){
            return null;
        }
        return head.lastKey();
    }

    public int size() {
        return 0;
    }


    public Key ceiling(Key key) {
        SortedMap<Key, Value> tail = st.tailMap(key);
        if (tail.isEmpty()){
            return null;
        }
        return tail.firstKey();
    }

    @Override
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }
}
