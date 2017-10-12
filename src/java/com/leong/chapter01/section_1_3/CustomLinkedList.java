package com.leong.chapter01.section_1_3;

import java.util.Iterator;

/**
 * Created by leong on 2017/10/12.
 */
public class CustomLinkedList<Item> implements Iterable {



    @Override
    public Iterator iterator() {
        return null;
    }

    private class Node<Item>{
        Item item;
        Node<Item> next;
        Node<Item> prev;

        Node(Node<Item> prev, Item element, Node<Item> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public static void main(String[] args) {
        java.util.LinkedList linkedList = new java.util.LinkedList();
    }
}
