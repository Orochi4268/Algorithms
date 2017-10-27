package com.leong.chapter02_Sorting.section21_Elementary;

import com.leong.chapter02_Sorting.BaseSort;
import java.io.File;

/**
 * 插入排序
 * 平均：插入/交换（N^2/4）； 最坏：插入/交换（N^2/2）；最好：交换（N-1），交换（0）
 * 思路（升序）：
 *  1. 从 i = 1 开始(j = i），然后比较 j(0) 和 j-1(1) 的值，如果 a[j] < a[j-1]，那么交换这两个值；
 *  2. 移动 j(--) 指针，重复步骤 1，直到 j = 0；
 *  3. 此时 i 左边的数组已经是有序的，然后移动 i（++）指针，直到最后一项。
 * @author leongfeng created on 2017/4/8
 */
public class InsertionSort extends BaseSort {
    @Override
    public BaseSort sort(Comparable[] arr) {
        int N = arr.length;
        for (int i = 1; i < N; i++){
            // 如果 j < j - 1，那么交换这两值
            for (int j = i; j > 0 && less(arr[j], arr[j-1]); j--){
                exchange(arr, j, j-1);
            }
            assert isSorted(arr, 0, i);
        }
        assert isSorted(arr);
        return this;
    }

    public static void main(String[] args) {
        BaseSort insertionSort = new InsertionSort();
        String[] a = new In(new File(BaseSort.class.getResource("").getPath() + File.separator + "words3.txt")).readAllStrings();
        insertionSort.sort(a).show(a);
    }
}
