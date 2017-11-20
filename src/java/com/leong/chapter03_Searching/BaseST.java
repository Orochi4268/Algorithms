package com.leong.chapter03_Searching;

/**
 * 简单泛型符号表
 * @author leongfeng created on 2017-11-10.
 */
public abstract class BaseST<Key, Value> {

    public abstract void put(Key key, Value value);

    public abstract Value get(Key key);

    /**
     *
     * @param key
     */
    public void delete(Key key){
        put(key, null);
    }

    /**
     * 是否有存在key.
     * @param key
     * @return
     */
    public boolean contains(Key key){
        return get(key) != null;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public abstract int size();

    /**
     * 所有的键集合，已排序。
     * @return
     */
    public abstract Iterable<Key> keys();
}
