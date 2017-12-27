package com.leong.chapter02_Sorting;

import edu.princeton.cs.algs4.StdRandom;

import java.util.stream.IntStream;

import static edu.princeton.cs.algs4.StdOut.print;
import static edu.princeton.cs.algs4.StdOut.printf;
import static edu.princeton.cs.algs4.StdOut.println;

/**
 * 基数排序
 * @author leongfeng created on 2017-12-27.
 */
public class RadixSort {

    private static final int radix = 10;

    public static void sort(int[] arr) {
        int digit = getDigit(arr), N = arr.length;
        println("最大位数：" + digit);
        int[][] bucket = new int[radix][N];
        for (int d = 0; d < digit; d++) {
            // 表示该桶位置的元素数量
            int[] counter = new int[radix];
            for (int i = 0; i < N; i++) {
                int pow = (int) Math.pow(10, d);
                int which = (arr[i] / pow) % radix;
                bucket[which][counter[which]] = arr[i];
                counter[which]++;
            }

            // 取出桶中的元素，放加原数组
            int index = 0;
            for (int r = 0; r < radix; r++) {
                if (counter[r] != 0) {
                    for (int i = 0; i < counter[r]; i++) {
                        arr[index++] = bucket[r][i];
                    }
                    counter[r] = 0;
                }
            }
            printf("第%d次桶操作后 ", d+1);
            show(arr);
        }
    }

    /**
     * 找出数组中最大数值的位数，如999的位数是 3 .
     *
     * @param arr 数组
     * @return 最大的数的位数
     */
    private static int getDigit(int[] arr) {
        int maxIndex = getMaxIndex(arr), t = arr[maxIndex] / radix;
        int digit = 1;
        while (t != 0) {
            digit++;
            t = t / radix;
        }
        return digit;
    }

    private static int getMaxIndex(int[] arr) {
        int maxIndex = 0, N = arr.length;
        for (int i = 1; i < N; i++) {
            if (arr[maxIndex] < arr[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private static void show(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            print(arr[i] + " ");
        }
        println();
    }

    public static void main(String[] args) {
        //int[] arr = {789, 78};
        /*int len = 10;
        int[] arr = new int[len];
        IntStream.range(0, len).forEach(i -> {
            arr[i] = StdRandom.uniform(10000);
        });*/
        int[] arr = {1199, 7969, 9683, 1149, 5572, 8919, 826, 5099, 2140, 4663};
        println("排序前：");
        show(arr);
        sort(arr);
        println("排序后：");
        show(arr);
    }

}
