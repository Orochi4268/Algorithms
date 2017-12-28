package com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks;

import java.util.*;
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

    /**
     * 反转遍历节点.
     *
     * @return Iterable
     */
    public Iterable<Item> reverse() {
        final Stack<Item> stack = new Stack<>();
        reverse(first, stack);
        return stack;
    }

    private void reverse(Node<Item> x, Stack<Item> stack) {
        Iterator<Item> iterator = iterator();
        while (iterator.hasNext()) {
            stack.push(iterator.next());
        }
    }

    public void reverseNode() {
        first = reverse(first);
    }

    private Node reverse(Node<Item> first) {
        Node<Item> prev = null;
        while (first != null) {
            Node<Item> next = first.next;
            first.next = prev;
            prev = first;
            first = next;
        }
        return prev;
    }

    public void recursiveReverseNode() {
        first = recursiveReverse(first);
    }

    private Node recursiveReverse(Node<Item> x) {
        if (x == null || x.next == null) {
            return x;
        }
        Node<Item> next = x.next;
        Node<Item> newNode = recursiveReverse(next);
        next.next = x;
        x.next = null;
        return newNode;
    }

    private Node reverseDNode(Node<Item> first) {
        Node<Item> curr = null;
        while (first != null) {
            curr = first;
            first = curr.next;
            curr.next = curr.prev;
            curr.prev = first;
        }
        return curr;
    }

    public void remove(Item item) {
        first = remove(first, item);
    }

    /**
     * 单链表删除某个值的结点
     * @param first fist node
     * @param item 被删除的结点
     * @return
     */
    private Node<Item> remove(Node<Item> first, Item item) {
        if (first == null) {
            return first;
        }

        Node<Item> prev = first, next = first.next;
        while (next != null) {
            if (item.equals(next.item)) {
                prev.next = next.next;
            } else {
                prev = next;
            }
            next = next.next;
        }

        if (item.equals(first.item)) {
            first = first.next;
        }
        return first;
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
        Collections.reverse(linkedList);
        CustomLinkedList<Integer> cst = new CustomLinkedList<>();
        cst.append(3);
        cst.append(3);
        IntStream.range(1, 4).forEach(i -> {
            cst.append(i);
        });
        println("List: " + cst);
        cst.append(3);
        cst.remove(3);
        println("List: " + cst);

    }
}





