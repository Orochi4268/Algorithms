package com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks;

import std.lib.StdIn;
import std.lib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 背包
 *
 * @author leongfeng created on 2017/3/25
 */
public class Bag<Item> implements Iterable<Item> {

    private Node<Item> first;
    private int n;

    public Bag() {
        first = null;
        n = 0;
    }

    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();
        int i = 1;
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            bag.add(item);
            if (i++ == 2) {
                break;
            }
        }

        Iterator iterator = bag.iterator();
        while (iterator.hasNext()) System.out.println(iterator.next());

        for (String s : bag) {
            StdOut.println(s);
        }
        StdOut.println("size of bag: " + bag.size());
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    /**
     * Adds the item to this bag
     *
     * @param item
     */
    public void add(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<>(first);
    }

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
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
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
