package com.leong.chapter02_Sorting.section24_PriorityQueue;

/**
 * 索引优先队列
 * @author leongfeng created on 2017/11/8.
 */
public class IndexMaxPQ<Key extends Comparable<Key>> extends BasePriorityQueue<Key>{
    private Key[] pq;
    private int N = 0;

    public IndexMaxPQ(int capacity){
        pq = (Key[])new Comparable[capacity];
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    public void delete(int k){

    }

    public Key delMax(){
        return null;
    }

    public void insert(Key key){

    }

}
