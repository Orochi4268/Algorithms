package com.leong.chapter01.section_1_1.exercise;

import com.leong.chapter01.section_1_1.BinarySearch;
import org.junit.Test;
import std.lib.In;
import std.lib.StdIn;
import std.lib.StdOut;

import java.util.Arrays;

/**
 * Created by leong on 2017/9/28.
 */
public class Exercise {


    @Test
    public void test14() {
        int N = 15;
        System.out.println(lg(N));
        System.out.println(Math.log(N) / Math.log(2));
    }
    // 1.1.14
    public static int lg(int N) {
        // log2(8) = log(2**3) = 3; log2(10) = log2(2**3 + 2) -> 最大整数为 3，所以最大整数应该是 N / 2 >= 2 的次数；
        // 其中：** python中n次方的符号
        int count = 0;
        for (int n = N; n >= 2; n /= 2){
            count ++;
        }
        return count;
    }

    @Test
    public void testLn(){
        System.out.println(productN(5));
        System.out.println(ln(5));
    }

    // 1.1.20
    public static double ln(int N){
        // 递归计算 N!
        return Math.log(productN(N)) / Math.log(Math.E);
    }
    static long productN(int N){
        if (N == 1){
            return 1;
        }
        return N * productN(N - 1);
    }

    @Test
    public void testRecursiveRank(){
        int[] whiteList = new In("D:\\workspace\\algs4-data\\tinyW.txt").readAllInts();
        Arrays.sort(whiteList);
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (BinarySearch.recursiveRank(key, whiteList) < 0) {
                StdOut.println("一般：" + key);
            }
        }
    }


}

