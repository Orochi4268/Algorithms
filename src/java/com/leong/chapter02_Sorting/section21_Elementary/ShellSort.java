package com.leong.chapter02_Sorting.section21_Elementary;

import com.leong.chapter02_Sorting.BaseSort;
import edu.princeton.cs.algs4.In;

import java.io.File;

import static edu.princeton.cs.algs4.StdOut.println;

/**
 * 希尔排序：基于插入排序的快速排序算法
 * 希尔排序的思想是使数组中任意间隔为 h 的元素都是有序的（h有序数组）。一个 h有序数组 就是 h 个互相独立的有序数组纺织在一起 组成的一个数组。
 * 最坏：比较（N^3/2）
 * h 是任意以 1 结尾的 h序列， 从 N/3 递减到 1，所以最大的 h 为 h = 3*h + 1。
 * 思路：
 * 1. 找到最大的 h 值（h = 3*h + 1），h  < N/3 ；
 * 2. h 递减到 1（h = h/3），使用插入排序对 h 长的数组排序，交换元素的间隔为 h，即将原来插入排序的 1 改为 h。
 *
 * @author leongfeng created on 2017/4/8
 */
public class ShellSort extends BaseSort {

    /**
     * 是否已经按照间隔 h 排序
     *
     * @param a
     * @param h
     * @return
     */
    private boolean isHsorted(Comparable[] a, int h) {
        for (int i = h; i < a.length; i++) {
            if (less(a[i], a[i - h])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public BaseSort sort(final Comparable[] arr) {
        int N = arr.length;
        int h = 1;
        // 1. 查询最大的 h
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        println("max h: " + h);
        while (h >= 1) {
            // 插入排序
            for (int i = 1; i < N; i++) {
                for (int j = i; j >= h && less(arr[j], arr[j - h]); j -= h) {
                    exchange(arr, j, j - h);
                }
                show(arr);
            }
            assert isHsorted(arr, h);
            h = h / 3;
        }
        assert isSorted(arr);
        return this;
    }

    public static void main(String[] args) {
        BaseSort sort = new ShellSort();
        Integer[] arr = new Integer[] {42,74,90,25,53,60,1};
        println("排序前：");
        sort.show(arr);
        println("排序：");
        sort.sort(arr);
        println("排序后：");
        sort.show(arr);
    }

}
