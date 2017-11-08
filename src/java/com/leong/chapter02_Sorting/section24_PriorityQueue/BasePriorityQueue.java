package com.leong.chapter02_Sorting.section24_PriorityQueue;

/**
 * @author leongfeng created on 2017/11/5.
 */
public abstract class BasePriorityQueue<Key extends Comparable<Key>> {
    /**
     * exchange a[i] and a[j] position
     * @param a a
     * @param i i
     * @param j j
     */
    public void exch(Comparable[] a, int i, int j) {
        Comparable tempKey = a[i];
        a[i] = a[j];
        a[j] = tempKey;
    }

    /**
     * is a less than b?
     * @param a a
     * @param b b
     * @return if a < b return true.
     */
    public boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }


    /**
     * is empty priority queue?
     * @return boolean
     */
    public abstract boolean isEmpty();

    /**
     * size of queue.
     * @return int
     */
    public abstract int size();
}
