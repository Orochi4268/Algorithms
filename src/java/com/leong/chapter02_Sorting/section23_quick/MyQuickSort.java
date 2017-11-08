package com.leong.chapter02_Sorting.section23_quick;


import com.leong.chapter02_Sorting.BaseSort;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @author leongfeng created on 2017-11-03.
 */
public class MyQuickSort extends BaseSort {

    @Override
    public BaseSort sort(Comparable[] a){
        sort(a, 0, a.length - 1);
        return this;
    }

    private void sort(Comparable[] a, int left, int right) {
        if (left >= right){
            return;
        }
        Comparable pivot = a[(left + right) / 2];
        /*每次分区可以确定一个元素的位置*/
        int index = partition(a, left, right, pivot);
        /*递归排序左半部分*/
        sort(a, left, index - 1);
        sort(a, index, right);
    }

    /**
     *
     * @param a
     * @param left
     * @param right
     * @param pivot
     * @return
     */
    private int partition(Comparable[] a, int left, int right, Comparable pivot) {
        while (left <= right){
            // 1.如果左边元素比pivot要小，那么left指针向右移动
            while (less(a[left], pivot)){
                left ++;
            }
            // 2.右边元素比pivot要大，那么right指针向左移动
            while (less(pivot, a[right])){
                right --;
            }
            /* 3.只要left和right指针没有相遇，经过上述指针移动之后，那么 a[left] >= a[right]
            * , 那么就需要交换两边元素，使得右边元素不小于pivot，左边元素不大于pivot
            * */
            if (left <= right){
                exchange(a, left, right);
                left ++;
                right --;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        BaseSort quickSort = new MyQuickSort();
        Integer[] a = new Integer[]{9,2,6,4,3,5,1};
        Arrays.asList(a).forEach(StdOut::print);
        StdOut.println();
        quickSort.sort(a);
        Arrays.asList(a).forEach(StdOut::print);
        StdOut.println();
    }
}
