package com.leong.chapter03_Searching.section34_HashTables.interview;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.*;

/**
 * 4-SUM. Given an array a[ \; ] of n integers, the 4-SUM problem is to determine if there exist distinct indices
 * i, j, k, and l such that a[i] + a[j] = a[k] + a[l]. Design an algorithm for the 4-SUM problem that takes time
 * proportional to n^2 (under suitable technical assumptions).
 *
 * @author leongfeng created on 2017-12-01.
 */
public class FourSum {
    public static void main(String[] args) {
        int count = 20;
        int[] a = new int[count];
        for (int i = 0; i < count; i++){
            a[i] = StdRandom.uniform(30);
        }
        int len = a.length;

        for (int i = 0; i < len - 3; i++) {
            int ai = a[i];
            for (int j = len - 1; j >= i + 3; j--) {
                int aj = a[j];
                if (ai + aj == a[i + 1] + a[j - 1]) {
                    StdOut.printf("a[%d] + a[%d] = a[%d] + a[%d] : ", i, j, i + 1, j - 1);
                    StdOut.printf("%d + %d = %d + %d", a[i], a[j], a[i + 1], a[j - 1]);
                    StdOut.println();
                }
            }
        }
    }
}
