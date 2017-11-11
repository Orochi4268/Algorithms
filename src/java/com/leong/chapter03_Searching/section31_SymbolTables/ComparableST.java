package com.leong.chapter03_Searching.section31_SymbolTables;

/**
 * 有序符号表，只要声明中含有泛型变量 Key extends Comparable<Key> 的都要实现所有的API.
 * @author leongfeng created on 2017-11-10.
 */
public abstract class ComparableST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {

    public abstract Key min();
    public abstract Key max();

    /**
     * 小于等于 key 的最大键
     * @param key
     * @return
     */
    public abstract Key floor(Key key);

    /**
     * 大于等于 key 的最小键
     * @param key
     * @return
     */
    public abstract Key ceiling(Key key);

    /**
     * 小于 key 的键数量
     * @param key
     * @return
     */
    public abstract int rank(Key key);

    /**
     * 排名为k的键
     * @param key
     * @return
     */
    public abstract int select(Key key);

    public abstract void deleteMin();
    public abstract void deleteMax();

    /**
     * [lo...hi] 之间键的数量
     * @param lo
     * @param hi
     */
    public abstract void size(Key lo, Key hi);

    /**
     * [lo...hi] 之间的所有键，已排序
     * @param lo
     * @param hi
     * @return
     */
    public abstract Iterable<Key> keys(Key lo, Key hi);
}
