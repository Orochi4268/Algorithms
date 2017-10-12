package com.leong.chapter01.section_1_3;

import java.util.Iterator;

/**
 * Created by leong on 2017/10/11.
 */
public class ResizingArrayStack<Item> implements Iterable<Item>{

    private Item[] a = (Item[])new Object[1]; // 存放元素数组
    private int N = 0; //元素数量

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void resize(int max) {
        Item[] tempArray = (Item[]) new Object[max];
        for (int i = 0; i < N; i++){
            tempArray[i] = a[i];
        }
        a = tempArray;
    }
    public void push(Item item){
        if (N == a.length){
            resize( 2 * N);
        }
        a[N++] = item;
    }

    public Item pop(){
        Item topItem = a[--N];
        a[N] = null; //避免对象游离
        if (N > 0 && N == a.length / 4){
            resize(a.length / 2);
        }
        return topItem;
    }


    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{
        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
    }

    public static void main(String[] args) {
        ResizingArrayStack<Integer> stack = new ResizingArrayStack<>();
        stack.push(1);
        stack.push(2);
        for (Integer i : stack){
            System.out.println(i);
        }
    }
}
