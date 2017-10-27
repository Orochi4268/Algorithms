package com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks;

import std.lib.StdIn;
import std.lib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 队列（FIFO）
 * 0    ->1->2->  3
 * head         tail
 * Enqueue:
 * 0    ->1->2->   3->  4
 * head        oldTail  tail
 *
 * Dequeue:
 * 0(deleted)  1 ->2-> 3
 * oldHead    head    tail
 *
 * @author leongfeng created on 2017/9/26
 */
public class Queue<Item> implements Iterable<Item> {
    /**
     * /指向最早添加的结点的链接
     */
    private Node<Item> head;
    /**
     * 指向最近添加的结点的链接
     */
    private Node<Item> tail;
    private int N;

    private class Node<Item> {
        Node<Item> next;
        Item item;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return N;
    }

    /**
     * 入列
     * @param item
     */
    public void enqueue(Item item){
        Node<Item> oldTail = tail;
        tail = new Node<>();
        tail.item = item;
        tail.next = null;
        if (isEmpty()){
            head = tail;
        } else{
            // 不止1个节点情况
            oldTail.next = tail;
        }
        N++;
    }

    /**
     * 出列
     * @return
     */
    public Item dequeue(){
        if (isEmpty()){
            tail = null;
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = head.item;
        head = head.next;
        N--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<>(head);
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        System.out.println("N:" + queue.N);
        int i = 1;
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            queue.enqueue(item);
            if (i++ == 2) {
                break;
            }
        }

        for (String s : queue) {
            StdOut.println(s);
        }

        while (!queue.isEmpty()){
            StdOut.println(queue.dequeue());
        }

        StdOut.println("N of queue: " + queue.size());
    }
}
