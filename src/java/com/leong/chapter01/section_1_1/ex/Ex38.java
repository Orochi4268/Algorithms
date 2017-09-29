package com.leong.chapter01.section_1_1.ex;

import com.leong.chapter01.section_1_1.BinarySearch;
import com.leong.chapter01.section_1_1.BruteForceSearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Created by leong on 2017/9/28.
 */
public class Ex38 {
    public static void main(String[] args) {
        In in = new In("D:\\workspace\\algs4-data\\largeW.txt");
        int[] whiteList = in.readAllInts();
        long startTime = System.currentTimeMillis();
        long endTime;
        int key = StdIn.readInt();
        if (BruteForceSearch.rank(key, whiteList) == -1) {
            StdOut.println(key);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Brute force search time: " + (endTime - startTime));

        key = StdIn.readInt();
        startTime = System.currentTimeMillis();
        Arrays.sort(whiteList);
        if (BinarySearch.rank(key, whiteList) == -1) {
            StdOut.println(key);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Binary search time: " + (endTime - startTime));

        in.close();
    }
}
