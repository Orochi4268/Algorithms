package com.leong.chapter02_Sorting.section24_PriorityQueue;

import java.util.Comparator;

/**
 * //TODO 需要实现
 * @author leongfeng created on 2017/11/5.
 */
public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int size;
    private Comparator<Key> comparator;
    public MinPQ(int capacity){
        this.pq = (Key[]) new Object[capacity];
    }
    public void insert(Key key){
        pq[size++] = key;
    }
    public Key min(){
        return null;
    }

    public Key delMin(){
        return null;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }
}
