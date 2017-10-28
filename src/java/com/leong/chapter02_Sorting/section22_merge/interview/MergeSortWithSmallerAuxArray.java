package com.leong.chapter02_Sorting.section22_merge.interview;

import com.leong.chapter02_Sorting.BaseSort;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.io.File;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Merging with smaller auxiliary array. Suppose that the subarray a[0] to a[n−1] is sorted and the subarray a[n] to
 * a[2∗n−1] is sorted. How can you merge the two subarrays so that a[0] to a[2∗n−1] is sorted using an auxiliary
 * array of length n (instead of 2n)?
 *
 * @author leongfeng created on 2017/10/28.
 */
public class MergeSortWithSmallerAuxArray extends BaseSort{
    public static void main(String[] args) {
        Integer[] a = new Integer[5];
        Integer[] b = new Integer[5];
        IntStream.range(0, 5).forEach(i -> a[i] = StdRandom.uniform(100));
        IntStream.range(0, 5).forEach(i -> b[i] = StdRandom.uniform(100));
        Arrays.sort(a);
        Arrays.sort(b);
        Integer[] c = new Integer[10];
        IntStream.range(0, 10).forEach(i -> {
            if(i < a.length){
                c[i] = a[i];
            } else {
                c[i] = b[i-b.length];
            }
        });
        StdOut.println("before sorting:");
        Arrays.asList(c).forEach(e -> StdOut.print( " " + e));
        StdOut.println();
        StdOut.println("after sorting:");
        BaseSort buSort = new MergeSortWithSmallerAuxArray();
        buSort.sort(c).show(c);
    }

    @Override
    public BaseSort sort(Comparable[] arr) {
        int N = arr.length;
        int n = N / 2;

        Comparable[] aux = new Comparable[n];
        // copy left subarray
        for (int i = 0; i < n; i++){
            aux[i] = arr[i];
        }
        // 左指针
        int left = 0;
        // 右指针
        int right = n;
        // 遍历原数组
        for (int k = 0; k < N; k++){
            // 辅助数组已经用完
            if (left >= n){
                break;
            } else if ( right >= N){
                // 右侧元素已经放置完毕，所以只需放置左边元素
                arr[k] = aux[left++];
            } else if (less(arr[right], aux[left])){
                arr[k] = arr[right++];
            } else {
                arr[k] = aux[left++];
            }

        }
        return this;
    }
}
