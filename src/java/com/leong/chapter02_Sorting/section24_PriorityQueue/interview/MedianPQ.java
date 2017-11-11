package com.leong.chapter02_Sorting.section24_PriorityQueue.interview;

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * 动态获取中位数。
 * 思路：
 * 1. 将数组分为左右两个子数组，一个MaxPQ，一个MinPQ，然后分别取最顶端元素，再返回平均值；
 * 2. 此处直接使用JDK的自带的{@link PriorityQueue}。
 * @author leongfeng created on 2017-11-09.
 */
public class MedianPQ {
    /**
     * 从数组中获取中位数
     * @param array
     * @return
     */
    public static double[] getMedians(int[] array){
        PriorityQueue<Integer> minPQ = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return -1 * a.compareTo(b);
            }
        });

        PriorityQueue<Integer> maxPQ = new PriorityQueue<>();
        double[] medians = new double[array.length];
        // 遍历数组
        for (int i = 0; i < array.length; i++){
            int number = array[i];
            addNumber(number, minPQ, maxPQ);
            rebalance(minPQ, maxPQ);
            medians[i] = getMedian(minPQ, maxPQ);
        }
        return medians;
    }

    /**
     * 如果两个PQ的size相等，那么返回两个PQ的根节点的平均值；否则只需返回较多元素的根节点。
     * @param minPQ
     * @param maxPQ
     * @return
     */
    private static double getMedian(PriorityQueue<Integer> minPQ, PriorityQueue<Integer> maxPQ) {
        PriorityQueue<Integer> biggerHeap = minPQ.size() > maxPQ.size() ? minPQ : maxPQ;
        PriorityQueue<Integer> smallerHeap = minPQ.size() > maxPQ.size() ? maxPQ : minPQ;
        if (biggerHeap.size() == smallerHeap.size()){
            return (biggerHeap.peek() + smallerHeap.peek()) / 2.0;
        }
        return biggerHeap.peek();
    }

    /**
     *  平衡两个PQ的size，如果较多元素的 size 多于 较少的那个超过2，那么需要将多余的一个放入较少的PQ中。使得两个PQ之间的
     *  size之差不大于1。
     * @param minPQ
     * @param maxPQ
     */
    private static void rebalance(PriorityQueue<Integer> minPQ, PriorityQueue<Integer> maxPQ) {
        PriorityQueue<Integer> biggerHeap = minPQ.size() > maxPQ.size() ? minPQ : maxPQ;
        PriorityQueue<Integer> smallerHeap = minPQ.size() > maxPQ.size() ? maxPQ : minPQ;
        if (biggerHeap.size() - smallerHeap.size() >= 2){
            smallerHeap.add(biggerHeap.poll());
        }
    }

    /**
     *  如果{@code minPQ} 为空或者 number 小于 minPQ 的根节点，那么放入 minPQ;否则放入 maxPQ.
     * @param number
     * @param minPQ
     * @param maxPQ
     */
    private static void addNumber(int number, PriorityQueue<Integer> minPQ, PriorityQueue<Integer> maxPQ) {
        if (minPQ.isEmpty() || number < minPQ.peek()){
            minPQ.add(number);
        } else {
            maxPQ.add(number);
        }
    }

    public static void main(String[] args) {
        double[] meidans = MedianPQ.getMedians(new int[]{1,2,3,4,5,6,7,8,9,10});
        for (int i = 0; i < meidans.length; i++){
            StdOut.println(meidans[i]);
        }
    }
}
