package com.leong.chapter01.section_1_3;

import std.lib.StdIn;
import std.lib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 下压堆栈（LIOF）：链式存储结构的实现
 * 4   ->3->2->1
 * top        bottom
 * push:
 * 5 ->  4   ->3->2->1
 * top oldTop
 *
 * @author: mike
 * @since: 2017/3/26
 */
public class Stack<Item> implements Iterable<Item> {
    private Node<Item> top; // top of stack
    private int n; // size of the stack

    public static void main(String[] args) {
        Stack<String> stack = new Stack();
        int i = 1;
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            stack.push(item);
            if (i++ == 2) break;
        }
        for (String s : stack)
            StdOut.println(s);
        StdOut.println("size of bag: " + stack.size());
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        Node<Item> oldTop = top;
        top = new Node<>();
        top.item = item;
        top.next = oldTop;
        n++;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        Item item = top.item; // 先取出 top 的值
        top = top.next; // 将 top 删，前1位变成 top
        n--;
        return item;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return top.item;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<>(top);
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
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("Stack underflow");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
