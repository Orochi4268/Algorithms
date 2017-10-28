package com.leong.chapter02_Sorting;

import com.leong.chapter02_Sorting.section21_Elementary.SelectionSort;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.Comparator;

import static edu.princeton.cs.algs4.StdOut.println;

/**
 * Sort 父类。
 * @author leongfeng created at 2017.04.06
 */
public abstract class BaseSort {
    public static void main(String[] args) {
        String[] a = new In(new File(BaseSort.class.getResource("").getPath() + File.separator + "words3.txt")).readAllStrings();
        BaseSort baseSort = new SelectionSort();
        baseSort.sort(a);
        println("Is sorted: " + baseSort.isSorted(a));
        baseSort.show(a);
    }

    /**
     * 实现排序的算法。
     * @param arr
     * @return
     */
    public abstract BaseSort sort(Comparable[] arr);
    /**
     * is v < w ?
     *
     * @param c
     * @param v
     * @param w
     * @return
     */
    public static boolean less(Comparator c, Object v, Object w) {
        return (c.compare(v, w) < 0);
    }
    /**
     * 比较 v 是否大于 w。
     *
     * @param v
     * @param w
     * @return
     */
    public boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * exchange these two elements.
     *
     * @param arr
     * @param i
     * @param j
     */
    public void exchange(Comparable[] arr, int i, int j) {
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /**
     * print all elements.
     *
     * @param arr
     */
    public void show(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            StdOut.print(arr[i] + " ");
        }
        println();
    }

    /**
     * is the array sorted.
     *
     * @param arr
     * @return
     */
    public boolean isSorted(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (less(arr[i], arr[i - 1])) {
                return false;
            }
        }
        return true;
    }

    /**
     * is the array sorted from arr[lo] to arr[hi]
     *
     * @param arr
     * @param lo
     * @param hi
     * @return
     */
    public boolean isSorted(Comparable[] arr, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(arr[i], arr[i - 1])) {
                return false;
            }

        }
        return true;
    }
}
