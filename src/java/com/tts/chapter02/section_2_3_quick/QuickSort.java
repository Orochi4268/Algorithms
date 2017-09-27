package com.tts.chapter02.section_2_3_quick;

import com.tts.chapter02.BaseSort;
import std.lib.StdRandom;

/**
 * @author: mike
 * @since: 2017/4/13
 */
public class QuickSort extends BaseSort {

    @Override
    public BaseSort sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
        return this;
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1); // 将左半部分a[lo...j-1]排序
        sort(a, j + 1, hi); // 将右半部分a[j+1...hi]排序
    }

    /**
     * 切分
     *
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private int partition(Comparable[] a, int lo, int hi) {
        // 将数组切分为a[lo...i-1],a[i],a[i+1...hi]
        int i = lo, j = hi + 1; // 左右扫描指针
        Comparable v = a[lo];
        while (true) {
            // 扫描左右，检查扫描是否结束并交换元素
            while (less(a[++i], v)) {  // a[i] 小于 v 时，指针(i)继续向右移动
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j); // 将 v = a[j] 放入正确的位置
        return j;
    }
}
