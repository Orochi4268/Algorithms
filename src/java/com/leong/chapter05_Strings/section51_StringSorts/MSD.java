package com.leong.chapter05_Strings.section51_StringSorts;

import com.leong.chapter05_Strings.StringData;
import edu.princeton.cs.algs4.Insertion;

import static edu.princeton.cs.algs4.StdOut.println;

/**
 * 高位优先的字符串排序.
 * 步骤：
 * 1. 首先用键索引计数法将所有字符串按照首字母排序；
 * 2. 然后（递归地）再将每个首字母所对应的子数组排序（忽略首字母，因为每一个类中的所有字符串的首字母都是相同的）；
 * 3. 和快速排序一样，高位优先的字符串排序会将数组切分为能够独立排序的子数组来完成排序任务，但它的切分会为每个首字母5得到一个子数组。
 *
 * @author leongfeng created on 2017/12/10.
 */
public class MSD {

    /**
     * 基数
     */
    private static final int R = 256;
    /**
     * 小数组的切换阈值
     */
    private static final int M = 15;
    /**
     * 数组分类的辅助数组.
     */
    private static String[] aux;

    /**
     * 返回字符串 s 位置 d 的字符。
     *
     * @param s 字符串
     * @param d 索引位置
     * @return int
     */
    private static int charAt(final String s, final int d) {
        if (d < s.length()) {
            return s.charAt(d);
        }
        return -1;
    }

    /**
     * 排序.
     *
     * @param a 数组
     */
    public static void sort(final String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0, aux);
    }

    /**
     * 以第 d 个字符为键将 a[lo] 至 a[hi] 排序.
     * @param a  数组
     * @param lo lower boundary
     * @param hi higher boundary
     * @param d  索引
     */
    private static void sort(final String[] a, final int lo, final int hi, final int d, final String[] aux) {
        // 切分数组
        if (hi <= lo + M){
            insertion(a, lo, hi, d);
            return;
        }

        int[] count = new int[R+2];
        // 计算频率
        for (int i = lo; i <= hi; i++){
            int c = charAt(a[i], d);
            count[c+2]++;
        }
        // 将频率转换为索引
        for (int r = 0; r < R+1; r++){
            count[r+1] = count[r];
        }
        // 将元素分类
        for (int i = lo; i <= hi; i++){
            int c = charAt(a[i], d);
            aux[count[c+1]++] = a[i];
        }
        // 回写
        for (int i = lo; i <= hi; i++){
            a[i] = aux[i - lo];
        }
        // 递归
        for (int r = 0; r < R; r++){
            sort(a, lo + count[r], lo + count[r+1] - 1, d + 1, aux);
        }
    }

    /**
     * @param a
     * @param lo
     * @param hi
     * @param d
     */
    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++){
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--){
                exch(a, j, j-1);
            }
        }
    }

    private static boolean less(String v, String w, int d) {
        assert v.substring(0, d).equals(w.substring(0, d));
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        String[] s = StringData.fromFilename("shells.txt").readAllStrings();
        int N = s.length;
        sort(s);
        for (int i = 0; i < N; i++){
            println(s[i]);
        }
    }


}
