package com.leong.chapter02_Sorting.section21_Elementary;

import com.leong.chapter02_Sorting.BaseSort;
import edu.princeton.cs.algs4.StdOut;
import java.io.File;
import java.util.stream.Stream;

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
    public BaseSort sort(Comparable[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++){
            int min = i;
            // 找出从 j 到 N 中最小值
            for (int j = i; j < N; j ++){
                if (less(arr[j], arr[min])){
                    min = j;
                }
            }
            // 如果没有比自己更小的值，那么自己跟自己交换
            exchange(arr, i, min);
            assert isSorted(arr, 0, i);
        }
        assert isSorted(arr);
        return this;
    }

    public static void main(String[] args) {
        BaseSort selectionSortSort = new SelectionSort();
        String[] arr = new In(new File(BaseSort.class.getResource("").getPath() + File.separator + "words3.txt")).readAllStrings();
        StdOut.println("before sorting:");
        Stream.of(arr).forEach( a -> StdOut.print(a + " "));
        StdOut.println();
        StdOut.println("after sorting:");
        selectionSortSort.sort(arr).show(arr);
    }
}
