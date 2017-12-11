package com.leong.chapter05_Strings.section52_Tries;

import java.util.HashMap;

/**
 * 单词查找树（Trie）.
 * @author leongfeng created on 2017/12/11.
 */
public class StringST<Value> {

    /**
     * Constructor.
     */
    public StringST(){

    }

    /**
     * 向表中插入键值对，如果值为 null，则删除 key.
     * @param key key - key with which the specified value is to be associated
     * @param val value - value to be associated with the specified key
     */
    public void put(final String key, final Value val){

    }

    /**
     * 键对应的值.
     * @param key key of value
     * @return value
     */
    public Value get(final String key){
        return null;
    }

    /**
     * 删除键key 和它对应的值.
     * @param key key
     */
    public void delete(final String key){

    }

    /**
     * 量少保存着key的值.
     * @param key key
     * @return trure if key in this ST
     */
    public boolean contains(final String key){
        return get(key) != null;
    }

    /**
     * 是否为空.
     * @return true if is empty
     */
    public boolean isEmpty(){
        return true;
    }

    /**
     * s 的前缀中最长的键.<br/>
     * 对于 she sells seashells by the sea shore,
     * 如果 longestPrefixOf("shell") 的结果是 she, longestPrefixOf("shellsort") 结果是 shells.
     * @param s 字符串
     * @return key
     */
    public String longestPrefixOf(final String s){
        return "";
    }

    /**
     * 所有以 s 为前缀的键.<br/>
     * keysWithPrefix("she") is she and shells, and keysWithPrefix("se") is sells and sea.
     * @param s 前缀
     * @return s 为前缀的键
     * @see #keysWithPrefix(String)
     */
    public Iterable<String> keysWithPrefix(final String s){
        return null;
    }

    /**
     * 所有和 s 匹配的键（其中“.”匹配任意字符）.<br/>
     * keysThatMatch(".he") returns she and the, and keysThatMatch("s..") returns she and sea
     * @param s 表达式
     * @return s 匹配的键
     * @see #keysWithPrefix(String)
     */
    public Iterable<String> keysThatMatch(final String s){
        return null;
    }

    /**
     * size of ST.
     * @return int
     */
    public int size(){
        return 0;
    }

    /**
     * 所有的键.
     * @return all keys
     */
    public Iterable<String> keys(){
        return null;
    }


    public static void main(final String[] args) {
        HashMap map = new HashMap(10);
    }
}
