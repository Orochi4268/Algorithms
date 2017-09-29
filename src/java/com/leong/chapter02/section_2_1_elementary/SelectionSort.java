package com.leong.chapter02.section_2_1_elementary;

import com.leong.chapter02.BaseSort;
import std.lib.In;

import java.io.File;

/**
 * @author: mike
 * @since: 2017/4/8
 */
public class SelectionSort extends BaseSort {
    @Override
    public BaseSort sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i+1; j < N; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
        return this;
    }

    public BaseSort sort(Object[] a, Comparable[] c){

        return this;
    }

    public static void main(String[] args) {
        BaseSort selectionSortSort = new SelectionSort();
        String[] a = new In(new File(BaseSort.class.getResource("").getPath() + File.separator + "words3.txt")).readAllStrings();
        selectionSortSort.sort(a).show(a);

    }
}
