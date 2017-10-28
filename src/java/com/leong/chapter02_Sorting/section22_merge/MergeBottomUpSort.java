package com.leong.chapter02_Sorting.section22_merge;

import com.leong.chapter02_Sorting.BaseSort;
import edu.princeton.cs.algs4.In;

import java.io.File;

/**
 * 自底向上归并排序：最多访问数组 6NlgN 次
 * 实现思想是先归并那些微型数组，然后再万对归并得到的子数组，一起递归到将整个数组归并在一起。
 *
 * @author leongfeng created on 2017/4/11
 */
public class MergeBottomUpSort extends BaseSort {

    Comparable[] aux;

    public static void main(String[] args) {
        String[] a = new In(new File(BaseSort.class.getResource("").getPath() + File.separator + "words3.txt")).readAllStrings();
        BaseSort buSort = new MergeBottomUpSort();
        buSort.sort(a).show(a);
    }

    @Override
    public BaseSort sort(Comparable[] arr) {
        int N = arr.length;
        aux = new Comparable[N];
        // sz 子数组大小
        for (int sz = 1; sz < N; sz = sz + sz) {
            // lo 子数组索引
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(arr, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
        return this;
    }

    private void merge(Comparable[] a, int lo, int m, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = m + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > m) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }

    }


}
