package com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.exercise;

import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Stack;

import java.util.Iterator;

/**
 * @author leongfeng created on 2017/10/24.
 */
public class Q1_3_12_StackWithCopy<Item> extends Stack<Item> {

    public static<Item> Stack<Item> copy(Stack<Item> stack){
        Iterator<Item> iterator = stack.iterator();
        Stack<Item> temp = new Stack<>();
        Stack<Item> result = new Stack<>();
        while (iterator.hasNext()){
            temp.push(iterator.next());
        }
        iterator = temp.iterator();
        while (iterator.hasNext()){
            result.push(iterator.next());
        }
        return result;
    }

    public static void main(String[] args) {
        Stack<String> stack1 = new Stack<>();
        stack1.push("a");
        stack1.push("b");
        stack1.push("c");
        Stack<String> stack2 = Q1_3_12_StackWithCopy.copy(stack1);
        stack2.forEach(System.out::println);
    }
}
