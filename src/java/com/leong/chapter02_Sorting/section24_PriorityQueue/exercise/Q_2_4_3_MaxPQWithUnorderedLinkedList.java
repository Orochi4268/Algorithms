package com.leong.chapter02_Sorting.section24_PriorityQueue.exercise;

import com.leong.chapter02_Sorting.section24_PriorityQueue.BasePriorityQueue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 无序链表实现优先队列。
 * @author leongfeng created on 2017/11/5.
 */
public class Q_2_4_3_MaxPQWithUnorderedLinkedList<Key extends Comparable> extends BasePriorityQueue {

    private Node<Key> first;
    private int N;

    class Node<Key>{
        /**
         * next node.
         */
        private Node<Key> next;
        /**
         * key value.
         */
        private Key key;
    }

    public Q_2_4_3_MaxPQWithUnorderedLinkedList(){

        N = 0;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return N;
    }

    /**
     * 只将key值放到最后。
     * @param key
     */
    public void insert(Key key){
        Node oldFirst = first;
        first = new Node<>();
        first.key = key;
        first.next = oldFirst;
        N ++;
    }

    /**
     * 循环和列表最后一个进行比较，如果比最后一个元素要大，则将当前元素移动到末尾。
     * @return key
     */
    public Key delMax(){
        if (isEmpty()){
            throw new NullPointerException("under stack over.");
        }
        if (N == 1){
            return first.key;
        }

        Node<Key> max = first;
        Node<Key> maxPrev = null;
        Node<Key> curr = max.next;
        Node<Key> prev = first;
        while (curr != null){
            Key maxKey = max.key;
            Key nextKey = curr.key;
            if (less(maxKey, nextKey)){
                max = curr;
                maxPrev = prev;
            }
            prev = curr;
            curr = curr.next;
        }
        // 最大值不在first节点，直接将 prev ->(max)-> next
        if (maxPrev != null){
            maxPrev.next = max.next;
        } else {
            first = max.next;
        }
        N --;
        return max.key;
    }

    public static void main(String[] args) {
        Q_2_4_3_MaxPQWithUnorderedLinkedList<String> priorityQueue = new Q_2_4_3_MaxPQWithUnorderedLinkedList<>();
        priorityQueue.insert("P");
        priorityQueue.insert("Q");
        priorityQueue.insert("E");
        priorityQueue.insert("Y");
        StdOut.println(priorityQueue.delMax());
        StdOut.println(priorityQueue.delMax());
        StdOut.println(priorityQueue.delMax());
    }
}
