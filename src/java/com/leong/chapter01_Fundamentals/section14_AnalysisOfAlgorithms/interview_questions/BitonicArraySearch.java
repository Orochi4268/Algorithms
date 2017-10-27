package com.leong.chapter01_Fundamentals.section14_AnalysisOfAlgorithms.interview_questions;

import std.lib.StdIn;
import std.lib.StdOut;

/**
 * 问题：Search in a bitonic array. An array is bitonic if it is comprised of an increasing sequence of integers followed
 * immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of n distinct integer
 * values, determines whether a given integer is in the array.
 * 双调数组查找
 * 双调数组：元素先增加后减小的数组。
 * 思路：
 * 1. 时间复杂度的主要因素就是找到数组的切分点，即找到数组的最值。我们可以把双调数组的最值看成抛物线的顶点，利用二分查找的
 * 思想，看数组的中间的值a[n/2]，若a[n/2 + 1] > a[n/2]，说明此时抛物线的单调递增一边，最大值在{n/2+1, n}这段区域，为此只需
 * 在这段区域重复上述操作即可；反之基 a[n/2 + 1] < a[n]，则为递减的一边，最值在{0, n/2}之间。时间复杂度 O(log N)；
 * 2. 找到最大值之后，可将数组分为左右两部分，因此我们需要找的 key 值只需与最值比较之后就可只在左或右查找即可。
 * <a href="http://blog.csdn.net/shaya118/article/details/40786379">参考1</a>
 *
 * @author leong created on 2017/10/21.
 */
public class BitonicArraySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 10, 9, 8, 7, 6, 5, 3};
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            StdOut.print(rank(key, arr));
        }
    }

    /**
     * 一次最值查找，两次二分查找，时间复杂度为 O(3logN)
     *
     * @param key
     * @param bitonicArray
     * @return
     */
    static int rank(int key, int[] bitonicArray) {
        int peak = findThePeak(bitonicArray);
        // search left
        int index = rankIncreasing(key, bitonicArray, 0, peak);
        if (index != -1) {
            return index;
        }
        // search right
        return rankDecreasing(key, bitonicArray, peak, bitonicArray.length - 1);
    }

    /**
     * 查找顶点下标，二分查找时间复杂度为 O(log N)
     *
     * @param a
     * @return
     */
    static int findThePeak(int[] a) {
        int low = 0;
        int high = a.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (a[mid] > a[mid + 1]) {
                high = mid;
            } else if (a[mid] < a[mid + 1]) {
                low = mid + 1;
            }
        }
        return high;
    }

    /**
     * 递归查找递增部分
     *
     * @param key
     * @param a
     * @param low
     * @param high
     * @return
     */
    static int rankIncreasing(int key, int[] a, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (key > a[mid]) {
            // 查找mid右边
            return rankIncreasing(key, a, mid + 1, high);
        } else if (key < a[mid]) {
            // 查找mid左边
            return rankIncreasing(key, a, low, mid - 1);
        } else {
            return mid;
        }
    }

    /**
     * 二分查找递减数组部分
     *
     * @param key
     * @param a
     * @param low
     * @param high
     * @return
     */
    static int rankDecreasing(int key, int[] a, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;

        if (key > a[mid]) {
            // 查找mid左边
            return rankDecreasing(key, a, low, mid - 1);
        } else if (key < a[mid]) {
            // 查找mid右边
            return rankDecreasing(key, a, mid + 1, high);
        } else {
            return mid;
        }
    }
}
