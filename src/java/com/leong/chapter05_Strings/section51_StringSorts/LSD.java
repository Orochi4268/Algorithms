package com.leong.chapter05_Strings.section51_StringSorts;

import com.leong.chapter05_Strings.StringData;
import edu.princeton.cs.algs4.In;

import static edu.princeton.cs.algs4.StdOut.println;

/**
 * 低位优先（LSD）的字符串排序.
 * 低位优先：从右到左检查键中的字符。
 * @author leongfeng created on 2017/12/10.
 */
public class LSD {

    /**
     * 通过前 W 个字符串将 a[] 排序.
     * @param a 字符串数组
     * @param W 前 W 个字符
     */
    public static void sort(final String[] a, final int W){
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];
        for (int d = W - 1; d >= 0; d--){
            int[] count = new int[R+1];
            /* 计算出现频率
            * 此处count可以统计所有字母范围：会将 char + int -> 强转为 int.
            * */
            for (int i = 0; i < N; i++){
                count[a[i].charAt(d) + 1]++;
                println((a[i].charAt(d)+1)+ " " + count[a[i].charAt(d)+1]);
            }
            // 将频率转换为索引
            for (int r = 0; r < R; r++){
                count[r+1] += count[r];
            }
            // 将元素分类
            for (int i = 0; i < N; i++){
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            // 回写
            for (int i = 0; i < N; i++){
                a[i] = aux[i];
            }
        }
    }

    public static void main(String[] args) {
        String[] a = StringData.fromFilename("dedup.txt").readAllStrings();
        int N = a.length;
        int W = a[0].length();
        for (int i = 0; i < N; i++){
            assert a[i].length() == W : "Strings must have fixed length";
        }
        sort(a, W);
        for (int i = 0; i < N; i++){
            println(a[i]);
        }
    }
}
