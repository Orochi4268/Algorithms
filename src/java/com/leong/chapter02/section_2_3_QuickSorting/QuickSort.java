package com.leong.chapter02.section_2_3_QuickSorting;

import com.leong.chapter02.BaseSort;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 快速排序（O(N lg N)）
 * 快速排序是一种分治的排序算法，它将一个数组分成两个子数组，将两部分独立的排序，与归并排序不同的是，快速排序是当月两个
 * 子数组都有序时整个数组也就自然有序了。
 *
 * 快速排序递归将a[lo...hi]排序，关键在于切分：
 * 1. 对于 j, a[j] 位置已经排定不变；
 * 2. a[lo...j-1] 的元素都不大于a[j]；
 * 3. a[j+1...hi] 的元素都不小于a[j]。
 * 切分过程总是能排定一个元素。
 *
 * 切分策略：
 * 1. 随意取a[lo]作为切分元素，此元素将会被排定；
 * 2. 从左端开始向右端扫描（指针为i=lo），直到a[++i]>=a[lo]时break；
 * 3. 然后从右端向左端扫描（指针为j=hi+1），直到a[--j]<=a[lo]时break；
 * 4. 显然a[i]和a[j]两个元素位置还没有排定，因此我们需要交换这两个元素；如此继续，保证左边元素<=切分元素，右边>=切分元素；
 * 5. 当 i 和 j 指针相遇时(i>=j)时，我们只需将a[lo]和左子数组的最右侧元素a[j]交换，然后返回j即可。
 * @author leongfeng created on 2017/4/13
 */
public class QuickSort extends BaseSort {

    @Override
    public BaseSort sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
        return this;
    }

    private void sort(Comparable[] a, int li, int hi) {

    }


   /*
    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1); // 将左半部分a[lo...j-1]排序
        sort(a, j + 1, hi); // 将右半部分a[j+1...hi]排序
    }

    *//**
     * 切分
     *
     * @param a
     * @param lo
     * @param hi
     * @return
     *//*
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
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j); // 将 v = a[j] 放入正确的位置
        return j;
    }*/
}
