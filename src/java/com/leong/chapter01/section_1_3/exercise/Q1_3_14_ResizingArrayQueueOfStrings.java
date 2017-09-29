package com.leong.chapter01.chapter1_3.exercise;

/**
 * 编写一个类 ResizingArrayQueueOfStrings，使用定长数组实现队列的抽象，然后扩展实现，使用调整数组的方法突破大小的限制。
 *
 * @author: mike
 * @since: 2017/4/3
 */
public class Q1_3_14_ResizingArrayQueueOfStrings {
    public static void main(String[] args) {
        ResizingArrayQueueOfStrings queue = new ResizingArrayQueueOfStrings();
        queue.enqueue("a");
        System.out.println("size(): " + queue.size() + " | count: " + queue.count);
        queue.enqueue("b");
        System.out.println("size(): " + queue.size() + " | count: " + queue.count);
        queue.enqueue("c");
        System.out.println("size(): " + queue.size() + " | count: " + queue.count);
        queue.enqueue("d");
        System.out.println("size(): " + queue.size() + " | count: " + queue.count);
        queue.enqueue("e");
        System.out.println("size(): " + queue.size() + " | count: " + queue.count);
        queue.enqueue("f");
        System.out.println("size(): " + queue.size() + " | count: " + queue.count);
        queue.enqueue("g");
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue() + " | size(): " + queue.size() + " | count: " + queue.count);
        }
    }
}

class ResizingArrayQueueOfStrings {
    int first;
    int last;
    int count;
    String[] queue;

    public ResizingArrayQueueOfStrings() {
        this.first = 0;
        this.last = 0;
        this.count = 1;
        this.queue = new String[count];
    }

    public boolean isEmpty() {
        return first == last;
    }

    public int size() {
        return last - first;
    }

    public void enqueue(String s) {
        if (last == count) {
            resizing(2 * size());
        }
        queue[last++] = s;
    }

    public String dequeue() {
        if (size() < count / 4) {
            resizing(2 * size());
        }
        return queue[first++];
    }

    private void resizing(int n) {
        String[] temp = new String[n];
        int j = 0;
        for (int i = first; i < last; i++) {
            temp[j++] = queue[i];
        }
        queue = temp;
        count = n;
        // dequeue时起作用
        last = last - first;
        first = 0;
    }

    @Override
    public String toString() {
        return "ResizingArrayQueueOfStrings{" +
                "count=" + count +
                ", first=" + first +
                ", last=" + last +
                '}';
    }
}
