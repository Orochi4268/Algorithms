package com.tts.chapter02;

import std.lib.In;
import std.lib.StdOut;

import java.io.File;
import java.util.Comparator;

import static std.lib.StdOut.println;

/**
 * @author: mike
 * @since: 2017/4/6
 */
public class BaseSort {
    public BaseSort sort(Comparable[] a) {
        return this;
    }

    /**
     * 对元素进行比较
     *
     * @param v
     * @param w
     * @return
     */
    public boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    // is v < w ?
    private static boolean less(Comparator c, Object v, Object w) {
        return (c.compare(v, w) < 0);
    }

    public void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        println();
    }

    public boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
    // is the array sorted from a[lo] to a[hi]
    public boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    public static void main(String[] args) {
        String[] a = new In(new File(BaseSort.class.getResource("").getPath() + File.separator + "words3.txt")).readAllStrings();
        BaseSort baseSort = new BaseSort();
        baseSort.sort(a);
        println("Is sorted: " + baseSort.isSorted(a));
        baseSort.show(a);
    }
}
