package com.leong.chapter02_Sorting.section22_merge;

import com.leong.chapter02_Sorting.BaseSort;
import edu.princeton.cs.algs4.In;

import java.io.File;

/**
 * @author: mike
 * @since: 2017/4/11
 */
public class MergeBUSort extends BaseSort {

    public static void main(String[] args) {
        String[] a = new In(new File(BaseSort.class.getResource("").getPath() + File.separator + "words3.txt")).readAllStrings();
        BaseSort buSort = new MergeBUSort();
        buSort.sort(a).show(a);
    }

    private void merge(Comparable[] a, Comparable[] aux, int lo, int m, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = m+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > m)                a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

    }

    @Override
    public BaseSort sort(Comparable[] arr) {
        int N = arr.length;
        Comparable[] aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(arr, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
        return this;
    }


}
