package com.leong.chapter02_Sorting.section21_Elementary;

import com.leong.chapter02_Sorting.BaseSort;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.io.File;
import java.util.stream.Stream;

import static edu.princeton.cs.algs4.StdOut.println;

/**
 * 选择排序（O(n^2)）
 * 思路：
 *  1. 找到数组中最小的元素；
 *  2. 将它和数组的第 i 个元素交换位置；
 *  3. 移动指针（i++），重复步骤 1 2
 * @author leongfeng created on 2017/4/8
 */
public class SelectionSort extends BaseSort {
    @Override
    public BaseSort sort(final Comparable[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++){
            int min = i;
            // 找出从 j 到 N 中最小值
            for (int j = i + 1; j < N; j ++){
                if (less(arr[j], arr[min])){
                    min = j;
                }
            }
            // i 不是自身时才需要交换元素
            if (i != min) {
                exchange(arr, i, min);
            }
            show(arr);
            assert isSorted(arr, 0, i);
        }
        assert isSorted(arr);
        return this;
    }

    public static void main(String[] args) {
        BaseSort sort = new SelectionSort();
        Integer[] arr = new Integer[] {3,44,38,5,47,15};
        println("排序前：");
        sort.show(arr);
        println("排序：");
        sort.sort(arr);
        println("排序后：");
        sort.show(arr);
    }
}
