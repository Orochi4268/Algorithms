package com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.exercise;

import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.ResizingArrayStack;
import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

/**
 * 输出数组的内定和大小
 * @author leongfeng created on 2017/10/23.
 */
public class Q1_3_08_DoublingStackOfStrings extends ResizingArrayStack<String> {
    private String[] data;
    private int size;

    public Q1_3_08_DoublingStackOfStrings(){
        data = new String[1];
        size = 0;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public int arraySize(){
        return data.length;
    }

    @Override
    public String pop() {
        if (isEmpty()){
            throw new NoSuchElementException("Stack underflow");
        }
        String item = data[--size];
        data[size] = null;
        if (size > 0 && size == data.length / 4){
            resize(size * 2);
        }
        return item;
    }

    @Override
    public void push(String item) {
        if (size == data.length){
            resize(size * 2);
        }
        data[size++] = item;
    }

    @Override
    public void resize(int max) {
        String[] temp = new String[max];
        for(int i = 0; i < size; i++){
            temp[i] = data[i];
        }
        data = temp;
    }

    @Override
    public Iterator<String> iterator() {
        return new ReverseIterator();
    }

    private class ReverseIterator implements Iterator{
        private int i = size - 1;

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public Object next() {
            return data[i--];
        }
    }

    public static void main(String[] args) {
        String[] inputs = "it was - the best - of times - - - it was - the - -".split(" ");
        Q1_3_08_DoublingStackOfStrings stack = new Q1_3_08_DoublingStackOfStrings();
        Arrays.asList(inputs).forEach(item -> {
            if ("-".equals(item)){
                stack.pop();
                stack.forEach(i -> StdOut.print(i + " "));
                StdOut.println();
                StdOut.println("ArraySize:" + stack.arraySize());
            } else {
                stack.push(item);
            }
        });

    }
}
