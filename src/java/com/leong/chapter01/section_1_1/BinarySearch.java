package com.leong.chapter01.section_1_1;

import std.lib.In;
import std.lib.StdIn;
import std.lib.StdOut;

import java.util.Arrays;

/**
 * 二分查找
 * Created by leong on 2017/9/28.
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] whiteList = new In("D:\\workspace\\algs4-data\\largeW.txt").readAllInts();
        Arrays.sort(whiteList);
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(key, whiteList) < 0) {
                StdOut.println("一般：" + key);
            }
        }

    }

    /**
     * 二分查找必须是有序数组
     *
     * @param key 需要查找的值
     * @param arr 查询的数组
     * @return 如果 key 不在 arr 中，返回 -1
     */
    public static int rank(int key, int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) { //被查找的数要么不存在，要么就在 a[lo...hi] 中
            int mid = lo + (hi - lo) / 2;
            if (key < arr[mid]) { // key 小于中间值，只需在左边查找
                hi = mid - 1;
            } else if (key > arr[mid]) { // key 小于中间值，只需在右边查找
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        //找不到返回 -1
        return -1;
    }

    /**
     * 1.1.22 二分查找递归实现
     * @param key
     * @param arr
     * @return
     */
    public static int recursiveRank(int key, int[] arr) {
        return rank(key, arr, 0, arr.length - 1);
    }

    private static int rank(int key, int[] arr, int lo, int hi) {
        // 1. 第一条语句问题包含 return 的条件语句。
        if (hi < lo) {
            return -1;
        }
        // 2. 递归问题去深度解决一个规模更小的子问题，这样递归才能收敛到最简单的情况，即收敛到 条件1
        // 3. 递归调用的父问题和尝试解决的子问题之间不应该有交集
        int mid = lo +  (hi - lo) / 2;
        if (key < arr[mid]) {
            // 左半部分
            return rank(key, arr, lo, mid - 1);
        } else if (key > arr[mid]){
            // 右半部分
            return rank(key, arr, mid + 1, hi);
        } else {
            return mid;
        }
    }

}
