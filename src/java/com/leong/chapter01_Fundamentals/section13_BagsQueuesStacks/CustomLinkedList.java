package com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static edu.princeton.cs.algs4.StdOut.print;
import static edu.princeton.cs.algs4.StdOut.println;

/**
 * 需要完成链表
 *
 * @author leongfeng created on 2017/10/12.
 */
public class CustomLinkedList<Item> implements Iterable<Item> {

    private int N;
    private Node<Item> first;
    private Node<Item> last;

    private class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;

        Node(Node<Item> prev, Item element, Node<Item> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public CustomLinkedList() {
    }

    public CustomLinkedList(Item[] items) {
        for (Item item : items) {
            append(item);
        }
    }

    public CustomLinkedList(Iterable<Item> coll) {
        for (Item item : coll) {
            append(item);
        }
    }

    /**
     * Returns true if this collection contains no elements.
     *
     * @return true if this collection contains no elements.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return N;
    }

    /**
     * the first element in this list.
     *
     * @return the first element in this list
     */
    public Item first() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.item;
    }

    /**
     * the first element in this list.
     *
     * @return the first element in this list
     */
    public Item last() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return last.item;
    }

    /**
     * Remove the first element from this list.
     *
     * @return the element at the front of this list
     */
    public Item removeFist() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        final Node<Item> f = first;
        final Item item = f.item;
        final Node<Item> next = f.next;
        f.item = null;
        f.next = null;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        N--;
        return item;
    }

    public void prepend(Item item) {
        final Node<Item> f = first;
        final Node<Item> newNode = new Node<>(null, item, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        N++;
    }


    public void append(Item item) {
        final Node<Item> l = last;
        final Node<Item> newNode = new Node<>(l, item, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        N++;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item).append(" ");
        }
        return s.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new CustomListIterator();
    }

    private class CustomListIterator implements Iterator<Item> {
        private Node<Item> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            final Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        java.util.LinkedList linkedList = new java.util.LinkedList();
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        IntStream.range(0, 10).forEach(i -> {
            list.append(i);
        });
        println(list);
        list.append(10);
        println(list);
        list.prepend(-1);
        println(list);
    }
}





