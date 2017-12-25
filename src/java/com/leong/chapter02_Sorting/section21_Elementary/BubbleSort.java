package com.leong.chapter02_Sorting.section21_Elementary;

import com.leong.chapter02_Sorting.BaseSort;
import edu.princeton.cs.algs4.StdRandom;

import java.util.stream.IntStream;

import static edu.princeton.cs.algs4.StdOut.print;
import static edu.princeton.cs.algs4.StdOut.println;

/**
 * 冒泡排序.
 *
 * @author leongfeng created on 2017-12-25.
 */
public class BubbleSort extends BaseSort {

    @Override
    public BaseSort sort(Comparable[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            // j 指针随着 i 的移动而减小
            for (int j = 1; j < len - i; j ++) {
                if (less(arr[j], arr[j-1])) {
                    exchange(arr, j, j-1);
                }
            }
            show(arr);
        }
        return this;
    }

    public static void main(String[] args) {
        BaseSort sort = new BubbleSort();
        Integer[] arr = new Integer[] {42,74,90,25,53};
        println("排序前：");
        sort.show(arr);
        println("排序：");
        sort.sort(arr);
        println("排序后：");
        sort.show(arr);
    }
}
