package com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 下压栈（LIFO）：链式存储结构的实现
 * 4   ->3->2->1
 * top        bottom
 * push:
 * 5 ->  4   ->3->2->1
 * top oldTop
 * 应用：算术表达式的求值
 *
 * @author: mike
 * @since: 2017/3/26
 */
public class Stack<Item> implements Iterable<Item> {

    /**
     * top of stack
     */
    private Node<Item> top;
    /**
     * size of the stack
     */
    private int N;


    public void push(Item item) {
        Node<Item> oldTop = top;
        top = new Node<>();
        top.item = item;
        top.next = oldTop;
        N++;
    }

    public  Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        Item topItem = top.item;
        top = top.next;
        N--;
        return topItem;
    }

    public Item peek(){
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return top.item;
    }

    public  boolean isEmpty() {
        return N == 0;
    }

    public  int size() {
        return N;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<>(top);
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    private class ListIterator<Item> implements Iterator<Item>{
        private Node<Item> current;

        public ListIterator(Node<Item> top) {
            current = top;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()){
                throw new NoSuchElementException("Stack underflow");
            }
            Item currentItem = current.item;
            current = current.next;
            return currentItem;
        }
    }


    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack();
        int i = 1;
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            stack.push(item);
            if (i++ == 2) {
                break;
            }
        }
        for (String s : stack){
            StdOut.println(s);
        }
        StdOut.println("size of stack: " + stack.size());
    }
}
