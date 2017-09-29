package com.leong.chapter01.section_1_3;

import std.lib.StdIn;
import std.lib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author: mike
 * @since: 2017/3/26
 */
public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first; // 指向最早添加的结点的链接
    private Node<Item> last; // 指向最近添加的结点的链接
    private int n;

    private class Node<Item> {
        Node<Item> next;
        Item item;
    }

    public Queue() {
        first = last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item){
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last; // 如果链表为空时需要将first和last指向同一个节点
        else oldLast.next = last;
        n++;
    }

    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last=null;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<>(first);
    }

    private class ListIterator<Item> implements Iterator<Item>{
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        public void remove()      { throw new UnsupportedOperationException();  }

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
        int i = 1;
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            queue.enqueue(item);
            if (i++==2) break;
        }
        for (String s : queue) {
            StdOut.println(s);
        }
        StdOut.println("size of bag: " + queue.size());
    }
}
