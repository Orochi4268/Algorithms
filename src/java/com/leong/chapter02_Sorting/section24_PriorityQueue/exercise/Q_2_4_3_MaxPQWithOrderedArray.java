package com.leong.chapter02_Sorting.section24_PriorityQueue.exercise;

import com.leong.chapter02_Sorting.section24_PriorityQueue.BasePriorityQueue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 有序数组实现优先队列。
 * @author leongfeng created on 2017/11/5.
 */
public class Q_2_4_3_MaxPQWithOrderedArray<Key extends Comparable<Key>> extends BasePriorityQueue {
    private Key[] a = (Key[]) new Comparable[1];
    private int N;

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    public void insert(Key key){
        if (N == a.length){
            resize(N * 2);
        }
        a[N++] = key;

        for (int i = N - 1; i > 0 && less(a[i], a[i-1]); i--){
            // 如果高位i小于低位i-1，那么交换两个元素
            exch(a, i, i-1);
        }
    }

    public Key delMax(){
        if (isEmpty()){
            throw new NullPointerException("under stack over.");
        }
        if (N > 0 && N == a.length / 4){
            resize(a.length / 2);
        }
        return a[--N];
    }

    private void resize(int size) {
        Key[] tempArr = (Key[])new Comparable[size];
        for (int i = 0; i < N; i++){
            tempArr[i] = a[i];
        }
        a = tempArr;
    }
    public static void main(String[] args) {
        Q_2_4_3_MaxPQWithOrderedArray<String> priorityQueue = new Q_2_4_3_MaxPQWithOrderedArray<>();
        priorityQueue.insert("P");
        priorityQueue.insert("Q");
        priorityQueue.insert("E");
        priorityQueue.insert("A");
        String maxKey = priorityQueue.delMax();
        StdOut.println(maxKey);
    }
}
