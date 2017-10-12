package com.leong.chapter01.section_1_3;

import std.lib.StdIn;
import std.lib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
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
 * @author: mike
 * @since: 2017/9/26
 */
public class Queue<Item> implements Iterable<Item> {
    private Node<Item> head; // 指向最早添加的结点的链接
    private Node<Item> tail; // 指向最近添加的结点的链接
    private int size;

    private class Node<Item> {
        Node<Item> next;
        Item item;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item){
        Node<Item> oldTail = tail; // 存起 old tial
        tail = new Node<>(); // 创建 tail 节点
        tail.item = item;
        tail.next = null; // tail 没有 next 节点
        if (isEmpty()){ // head 为 null
            head = tail; // 只存在1个节点
        } else{
            // 不止1个节点情况
            oldTail.next = tail; // old tail 指向 tail;
        }
        size ++;
    }

    public Item dequeue(){
        if (isEmpty()){
            tail = null;
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = head.item;
        head = head.next;
        size --;
        return item;
    }

    /*public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        Item item = head.item;
        head = head.next;
        n--;
        if (isEmpty()) tail = null;
        return item;
    }*/

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

        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        System.out.println("size:" + queue.size);
        int i = 1;
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            queue.enqueue(item);
            if (i++ == 2) break;
        }

        for (String s : queue) {
            StdOut.println(s);
        }

        while (!queue.isEmpty()){
            StdOut.println(queue.dequeue());
        }

        StdOut.println("size of queue: " + queue.size());
    }
}
