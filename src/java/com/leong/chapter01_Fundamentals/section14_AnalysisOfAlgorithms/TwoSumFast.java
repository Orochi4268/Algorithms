package com.leong.chapter01_Fundamentals.section14_AnalysisOfAlgorithms;


import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @author: mike
 * @since: 2017/4/4
 */
public class TwoSumFast {
    public static int count(int[] a){
        Arrays.sort(a);
        int cnt = 0, N = a.length;
        for (int i=0; i<N; i++){
            if (BinarySearch.indexOf(a, -a[i]) > i){
                cnt ++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] a = In.readInts("D:\\workspace\\algorithms\\src\\com\\tts\\chapter01\\chapter1_4\\8Kints.txt");
        StdOut.println(count(a));
    }
}
