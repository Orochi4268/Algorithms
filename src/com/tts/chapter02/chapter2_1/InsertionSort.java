package com.tts.chapter02.chapter2_1;

import std.lib.In;

import java.io.File;

/**
 * @author: mike
 * @since: 2017/4/8
 */
public class InsertionSort extends BaseSort {
    @Override
    public BaseSort sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++){
            for (int j = i; j > 0 && less(a[j], a[j-1]); j --){
                exch(a, j, j-1);
            }
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
        return this;
    }

    public static void main(String[] args) {
        BaseSort insertionSort = new InsertionSort();
        String[] a = new In(new File(BaseSort.class.getResource("").getPath() + File.separator + "words3.txt")).readAllStrings();
        insertionSort.sort(a).show(a);
    }
}
