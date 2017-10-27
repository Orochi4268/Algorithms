package com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks;

import java.util.Iterator;

/**
 * @author leongfeng created on 2017/10/12.
 */
public class CustomLinkedList<Item> implements Iterable {
    public static void main(String[] args) {
        java.util.LinkedList linkedList = new java.util.LinkedList();
    }

    @Override
    public Iterator iterator() {
        return null;
    }

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
}
