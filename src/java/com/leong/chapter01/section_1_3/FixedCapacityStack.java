package com.leong.chapter01.section_1_3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 定容栈：栈的数组存储结构实现
 * Created by leong on 2017/9/30.
 */
public class FixedCapacityStack<Item> implements Iterable<Item>{
    public static void main(String[] args) {
        FixedCapacityStack<Integer> stack = new FixedCapacityStack<>(10);
        for (int i=0; i < 10; i++){
            stack.push(i);
        }
        System.out.println("size:" + stack.size());

//        stack.resize(20);
        stack.push(10);

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }

    }

    private Item[] data;
    private int N;// 栈容量

    public FixedCapacityStack(int cap) {
        if (cap > 0) {
            data = (Item[])new Object[cap];
        } else {
            throw  new RuntimeException("the initSize cannot less than ZERO!");
        }
    }

    /**
     * 栈是否已经满了
     * @return
     */
    public boolean isFull(){
        return N == data.length;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public void resize(int max){
        Item[] temp =  (Item[]) new Object[max];
        for (int i = 0; i < N; i++){
            temp[i] = data[i];
        }
        data = temp;
    }

    public void push(Item item){
        /*if (isFull()){
            throw new RuntimeException("栈已满，无法将" + item + "入栈！");
        }*/
        if (isFull()){
            resize(N * 2);
        }
        data[N++] = item;
    }

    public Item pop(){
        if (isEmpty()){
            throw new NoSuchElementException("栈已空！");
        }
        Item item = data[--N];
        data[N] = null; // 避免对象游离
        if (N > 0 && N == data.length / 4){
            resize(data.length / 2);
        }
        return item;
    }

    public Item peek(){
        if (isEmpty()){
            throw new NoSuchElementException("栈已空！");
        }
        return data[N];
    }

    public int size(){
        return N;
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
            return data[--i];
        }
    }

}
