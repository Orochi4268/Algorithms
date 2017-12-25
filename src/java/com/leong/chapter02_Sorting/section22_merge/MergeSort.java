package com.leong.chapter02_Sorting.section22_merge;

import com.leong.chapter02_Sorting.BaseSort;

import static edu.princeton.cs.algs4.StdOut.println;

/**
 * 自顶向下归并排序（O(NlogN)）：需要 1/2NlgN 至 NlgN 次比较
 * 归并思路：
 * 1. 创建一个辅助数组 aux[]；
 * 2. 创建3个指针， k 指向原数组 a[]； i(i=lo) 用于指向 aux 左边子数组；j(j=mid+1) 指向 aux 右边数组；
 * 3. 首先取 aux[i] 和 aux[j] 的值，将较小的值放入原数组 a[k]，然后移动指针(i j)；继续比较，直到所有元素遍历完毕；
 *
 * @author leongfeng created on 2017/4/10
 */
public class MergeSort extends BaseSort {
    /**
     * 辅助数组.
     */
    private Comparable[] aux;

    public static void main(String[] args) {
        BaseSort sort = new MergeSort();
        Integer[] arr = new Integer[]{90, 42, 25, 74, 53};
        println("排序前：");
        sort.show(arr);
        println("排序：");
        sort.sort(arr);
        println("排序后：");
        sort.show(arr);
    }

    @Override
    public BaseSort sort(final Comparable[] arr) {
        aux = new Comparable[arr.length];
        sort(arr, 0, arr.length - 1);
        return this;
    }

    /**
     * 自顶向下归并排序操作。
     * CUTOFF 约等于 10。
     *
     * @param arr 排序数组
     * @param lo  lower boundary
     * @param hi  higher boundary
     */
    private void sort(final Comparable[] arr, final int lo, final int hi) {
        // 改进1：对小规模数组使用插入排序
        /**
         if (hi <= lo + CUTOFF -1){
         插入排序
         }
         */
        // 结束递归的条件
        if (lo >= hi) {
            return;
        }
        int mid = (lo + hi) / 2;
        // 将左半边排序
        sort(arr, lo, mid);
        // 将右半边排序
        sort(arr, mid + 1, hi);
        // 改进2：测试数组是否已经有序，如果有序就不用merge
        if (less(arr[mid], arr[mid + 1])) {
            return;
        }
        merge(arr, lo, mid, hi);
        show(arr);
    }

    /**
     * 归并排序具体实现方法.
     *
     * @param arr 需要归并的目标数组
     * @param lo  low boundary
     * @param mid middle index
     * @param hi  high boundary
     */
    public void merge(final Comparable[] arr, final int lo, final int mid, final int hi) {
        // 1. 从arr[]拷贝需要操作的元素到aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }

        // 2. i指向左半边元素，j指向右半边元素，指向的是aux[]
        int i = lo, j = mid + 1;
        // 3. 归并加a[lo, hi]
        for (int k = lo; k <= hi; k++) {
            // 3.1 aux[]左半部分已经用尽，需要归并右半部分元素
            if (i > mid) {
                arr[k] = aux[j++];
                // 3.2 aux[]右半部分已经用尽，需要操作左半部分元素
            } else if (j > hi) {
                arr[k] = aux[i++];
                // 3.3 如果a[j] < a[i]，即右边大于左边，那么取右边的当前元素
            } else if (less(aux[j], aux[i])) {
                arr[k] = aux[j++];
                // 3.4 a[j] > a[i]，取左边的元素
            } else {
                arr[k] = aux[i++];
            }
        }
        assert isSorted(arr, lo, hi);
    }
}
