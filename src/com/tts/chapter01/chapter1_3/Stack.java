package com.tts.chapter01.chapter1_3;

import std.lib.StdIn;
import std.lib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 下压堆栈（LIOF）
 * @author: mike
 * @since: 2017/3/26
 */
public class Stack<Item> implements Iterable<Item> {
    private Node<Item> first; // top of stack
    private int n; // size of the stack

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Stack(){
        first = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size(){return n;}

    public void push(Item item){
        // 向栈顶添加元素
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public Item pop(){
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        // 从栈顶删除元素
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        n--;
        return item;
    }

    public Item peek(){
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
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
        return new ListIterator<>(first);
    }

    private class ListIterator<Item> implements Iterator<Item>{

        private Node<Item> current;

        public ListIterator(Node<Item> first){
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

    public static void main(String[] args) {
        Stack<String> stack = new Stack();
        int i = 1;
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            stack.push(item);
            if (i++==2) break;
        }
        for (String s : stack)
            StdOut.println(s);
        StdOut.println("size of bag: " + stack.size());
    }
}
