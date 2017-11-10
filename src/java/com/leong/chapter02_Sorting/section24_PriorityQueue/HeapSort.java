package com.leong.chapter02_Sorting.section24_PriorityQueue;

import edu.princeton.cs.algs4.Heap;
import edu.princeton.cs.algs4.StdOut;

/**
 * 堆排序：
 * 1. 在堆的构造阶段中，将原数组重新组织安排进一个堆中；
 * 2. 然后在下沉阶段，从堆中按递减顺序取出所有元素并得到排序结果。
 * <p>
 * 使用面向最大元素的优先队列并重复删除最大元素。
 * 构造堆：
 *
 * @author leongfeng created on 2017/11/9.
 */
public class HeapSort {

    public static void sort(Comparable[] pq) {
        int N = pq.length;
        // 1. 使用sink()从右到左构造子堆
        for (int k = N / 2; k >= 1; k--) {
            sink(pq, k, N);
        }

        // 2.
        while (N > 1) {
            exch(pq, 1, N--);
            sink(pq, 1, N);
        }
    }

    private static void sink(Comparable[] pq, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            // exch(1,5) sink(1,4) ，所以如果 j = 4 时，不应当再比较 4,5 的位置了
            if (j < N && less(pq, j, j + 1)) {
                j++;
            }
            if (!less(pq, k, j)) {
                break;
            }
            exch(pq, k, j);
            k = j;
        }
    }

    private static void display(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Comparable[] pq, int i, int j) {
        Comparable swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }

    public static void main(String[] args) {
        String[] a = new String[]{"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        HeapSort.sort(a);
        display(a);
    }
}
