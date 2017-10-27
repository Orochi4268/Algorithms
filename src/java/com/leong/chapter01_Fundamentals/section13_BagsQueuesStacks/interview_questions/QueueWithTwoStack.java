package com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.interview_questions;


import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Queue;
import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author leongfeng created on 2017/10/23.
 */
public class QueueWithTwoStack<Item> extends Queue<Item> {

    Stack<Item> input = new Stack<>();
    Stack<Item> output = new Stack<>();

    @Override
    public void enqueue(Item item) {
        input.push(item);
    }

    @Override
    public Item dequeue() {
        if (output.isEmpty()){
            while (!input.isEmpty()){
                output.push(input.pop());
            }
        }
        return output.pop();
    }

    public static void main(String[] args) {
        QueueWithTwoStack<Integer> queueWithTwoStack = new QueueWithTwoStack<>();
        queueWithTwoStack.enqueue(1);
        queueWithTwoStack.enqueue(2);
        // 1
        StdOut.println(queueWithTwoStack.dequeue());
        queueWithTwoStack.enqueue(3);
        // 2
        StdOut.println(queueWithTwoStack.dequeue());
        // 3
        StdOut.println(queueWithTwoStack.dequeue());
    }
}
