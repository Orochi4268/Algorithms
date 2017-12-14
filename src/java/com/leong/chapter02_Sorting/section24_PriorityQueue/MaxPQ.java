package com.leong.chapter02_Sorting.section24_PriorityQueue;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.PriorityQueue;

/**
 * 二叉堆表示优先队列。
 * 解题思想：
 * 1. 使用数组来表示二叉树，根结点在位置1（K），子结点在2和3（2K/2K+1）；
 * 2. 插入：将元素key放到数组末尾，然后作上浮操作；
 * 3. 删除：将根节点删除，然后将最后一个值和根节点交换，然后将这个元素下沉到合适的位置。
 *
 * @author leongfeng created on 2017/11/8.
 */
public class MaxPQ<Key extends Comparable<Key>> extends BasePriorityQueue<Key> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }


    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    /**
     * 插入，需要由下至上作堆的有序化。
     *
     * @param key key
     */
    public void insert(Key key) {
        // TODO resize
        pq[++N] = key;
        swim(N);
    }

    private void swim(int k) {
        while (k > 1 && less(pq[k / 2], pq[k ])) {
            exch(pq, k / 2, k);
            k = k / 2;
        }
    }

    /**
     * 删除：返回根节点值，然后将最后一个值和根节点交换，然后作下沉操作。
     *
     * @return max
     */
    public Key delMax() {
        Key max = pq[1];
        exch(pq, 1, N--);
        pq[N + 1] = null;
        sink(1);
        return max;
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(pq[j], pq[j + 1])) {
                j++;
            }
            if (!less(pq[k], pq[j])) {
                break;
            }
            exch(pq, k, j);
            k = j;
        }
    }

    public void display(){
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N+1; i++){
            sb.append(pq[i]).append(" ");
        }
        StdOut.println(sb.toString());
    }



    public static void main(String[] args) {
        MaxPQ<String> maxPQ = new MaxPQ<>(20);
        maxPQ.insert("P");
        maxPQ.insert("Q");
        maxPQ.insert("E");
        maxPQ.delMax();
        maxPQ.display();
        maxPQ.insert("X");
        maxPQ.insert("A");
        maxPQ.insert("M");
        maxPQ.delMax();
        maxPQ.display();
        maxPQ.insert("P");
        maxPQ.insert("L");
        maxPQ.insert("E");
        maxPQ.delMax();
        maxPQ.display();
        java.util.Queue<String> queue = new PriorityQueue<>(10);
        queue.add("A");
    }
}
