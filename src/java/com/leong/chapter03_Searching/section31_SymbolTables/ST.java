package com.leong.chapter03_Searching.section31_SymbolTables;

/**
 * 简单泛型符号表
 * @author leongfeng created on 2017-11-10.
 */
public abstract class ST<Key, Value> {

    public abstract void put(Key key, Value value);

    public abstract Value get(Key key);

    public void delete(Key key){
        put(key, null);
    }

    public boolean contains(Key key){
        return get(key) != null;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public abstract int size();

    public abstract Iterable<Key> keys();
}
