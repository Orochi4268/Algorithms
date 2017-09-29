package com.leong.chapter01.section_1_3;

import std.lib.StdIn;
import std.lib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 下压堆栈（LIOF）
 * 4   ->3->2->1
 * head        tail
 * push:
 * 5 ->  4   ->3->2->1
 * head oldHead
 *
 * @author: mike
 * @since: 2017/3/26
 */
public class Stack<Item> implements Iterable<Item> {
    private Node<Item> head; // top of stack
    private int n; // size of the stack

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size(){return n;}

    public void push(Item item){
        Node<Item> oldHead = head;
        head = new Node<>();
        head.item = item;
        head.next = oldHead;
        n++;
    }

    public Item pop(){
        if (isEmpty()){
            throw new NoSuchElementException("Stack underflow");
        }
        Item item = head.item; // 先取出 head 的值
        head = head.next; // 将 head 删，前1位变成 head
        n --;
        return item;
    }

    public Item peek(){
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return head.item;
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
        return new ListIterator<>(head);
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
