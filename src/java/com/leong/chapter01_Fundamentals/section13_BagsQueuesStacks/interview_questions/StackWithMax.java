package com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.interview_questions;

import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Stack;
import std.lib.StdIn;
import std.lib.StdOut;

/**
 *
 * https://www.coursera.org/learn/algorithms-part1/quiz/qk8vT/interview-questions-optional
 * 问题：Stack with max. Create a data structure that efficiently supports the stack operations (push and pop) and also
 * a return-the-maximum operation. Assume the elements are reals numbers so that you can compare them.
 *
 * 思路：
 *  一个 Stack 储存所有元素，一个 Stack 储存最大值
 *
 * @author leong created on 2017/10/23.
 */
public class StackWithMax {
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> maxStack = new Stack<>();
    private int max = Integer.MIN_VALUE;
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 8, 8, 5};
        StackWithMax stackWithMax = new StackWithMax();
        for (int item : arr){
            stackWithMax.push(item);
        }
        for (int i =0 ; i < arr.length; i++){
            StdOut.println("max: " + stackWithMax.getMax() + "; item:" + stackWithMax.pop());
        }
    }

    public void push(Integer item){
        if (item > max){
            max = item;
        }
        dataStack.push(item);;
        maxStack.push(max);
    }

    public Integer pop(){
        maxStack.pop();
        return dataStack.pop();
    }
    public Integer getMax(){
        return maxStack.peek();
    }

}
