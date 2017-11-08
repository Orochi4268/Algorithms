package com.leong.chapter02_Sorting.section24_PriorityQueue.exercise;

import com.leong.chapter02_Sorting.section24_PriorityQueue.BasePriorityQueue;
import edu.princeton.cs.algs4.StdOut;

import java.util.EmptyStackException;

/**
 * 无序数组实现优先队列。即插入key时不用排序，取值时再进行排序。
 * @author leongfeng created on 2017/10/28.
 */
public class Q_2_4_3_MaxPQWithUnorderedArray<Key extends Comparable<Key>> extends BasePriorityQueue {
    private Key[] a;
    private int N;
    public Q_2_4_3_MaxPQWithUnorderedArray(){
        this.a = (Key[]) new Comparable[1];
    }

    /**
     * 插入。
     * @param v
     */
    public void insert(Key v){
        if (N == a.length){
            resize(N * 2);
        }
        a[N++] = v;
    }

    /**
     * 找到最大元素，然后和边界元素交换后删除。
     * @return the max key
     */
    public Key delMax(){
        if (isEmpty()){
            throw new NullPointerException("under stack over.");
        }
        int maxIndex = 0;
        for (int i = 0; i < N; i++){
            /*比较a[N-1]和a[i]，如果N-1比较小，交换。暂时默认没有重复元素，如果有重复元素需要定义 hi 指针*/
            if (less(a[maxIndex], a[i])){
                maxIndex = i;
            }
        }
        exch(a, maxIndex, N - 1);
        Key max = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length / 4){
            resize(a.length / 4);
        }
        return max;
    }

    private void resize(int size) {
        Key[] tempArr = (Key[])new Comparable[size];
        for (int i = 0; i < N; i++){
            tempArr[i] = a[i];
        }
        a = tempArr;
    }

    @Override
    public boolean isEmpty(){
        return N == 0;
    }

    @Override
    public int size(){
        return N;
    }

    public static void main(String[] args) {
        Q_2_4_3_MaxPQWithUnorderedArray<String> priorityQueue = new Q_2_4_3_MaxPQWithUnorderedArray<>();
        priorityQueue.insert("P");
        priorityQueue.insert("Q");
        priorityQueue.insert("E");
        priorityQueue.insert("Y");
        String maxKey = priorityQueue.delMax();
        StdOut.println(maxKey);
    }
}
