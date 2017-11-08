package com.leong.chapter02_Sorting.section24_PriorityQueue.exercise;

import com.leong.chapter02_Sorting.section24_PriorityQueue.BasePriorityQueue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.stream.IntStream;

/**
 * 有序链表实现优先队列：
 * 像 Stack 一样，插入时将最大值放在队列顶部， delMax 时弹出顶部元素。
 * @author leongfeng created on 2017/11/6.
 */
public class Q_2_4_3_MaxPQWithOrderedLinkedList<Key extends Comparable<Key>>  extends BasePriorityQueue<Key>{

    private int N;
    private Node<Key> top;

    public Q_2_4_3_MaxPQWithOrderedLinkedList(){
        N = 0;
    }

    private class Node<Key>{
        /**
         * next node.
         */
        private Node<Key> next;
        /**
         * key value.
         */
        private Key key;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public int size() {
        return N;
    }

    /**
     * 插入时进行排序，最大值放在顶部。
     */
    public void insert(Key key){
        /*Node<Key> oldTop = top;
        top = new Node<>();
        top.key = key;
        top.next = oldTop;*/
        Node<Key> node = new Node<>();
        node.key = key;
        node.next = null;
        if (isEmpty()){
            top = node;
            N ++;
            return;
        }
        // 先跟top 比较
        if (less(top.key, key) || top.key.compareTo(key) == 0){
            Node<Key> oldTop = top;
            top = node;
            top.next = oldTop;
            N ++;
            return;
        }
        if (N == 1) {
            top.next = node;
            N ++;
            return;
        }

        Node<Key> curr = top.next;
        Node<Key> prev = top;
        while (curr != null){
            Key currKey = curr.key;
            if (less(currKey, key)){
                node.next = curr;
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        prev.next = node;
        N ++;
    }

    /**32 21 55 31 88 78 56 34 35 22
     * 弹出顶部的值。
     * @return max key
     */
    public Key delMax(){
        if (isEmpty()){
            throw new NullPointerException("under stack flow");
        }
        Key maxKey = top.key;
        top = top.next;
        N --;
        return maxKey;
    }

    public static void main(String[] args) {
        Q_2_4_3_MaxPQWithOrderedLinkedList<Integer> priorityQueue = new Q_2_4_3_MaxPQWithOrderedLinkedList<>();
        for (int i = 0; i < 5; i++){
            int key = StdRandom.uniform(0, 10);
            StdOut.print(key + " ");
            priorityQueue.insert(key);
        }

        StdOut.println();
        int len = priorityQueue.N;
        for (int i = 0; i < len; i++){
            StdOut.print(priorityQueue.delMax() + " ");
        }
    }
}
