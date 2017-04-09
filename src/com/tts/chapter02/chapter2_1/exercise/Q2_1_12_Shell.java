package com.tts.chapter02.chapter2_1.exercise;

import com.tts.chapter02.chapter2_1.BaseSort;

import java.util.stream.IntStream;

/**
 * @author: mike
 * @since: 2017/4/9
 */
public class Q2_1_12_Shell extends BaseSort {

    public static void main(String[] args) {
        BaseSort baseSort = new Q2_1_12_Shell();
        Double[] a100 = new Double[100];
        IntStream.range(0, a100.length).forEach( i -> a100[i] = Math.random());
        baseSort.sort(a100);

        Double[] a10000 = new Double[10000];
        IntStream.range(0, a10000.length).forEach( i -> a10000[i] = Math.random());
        baseSort.sort(a10000);

        Double[] a1000000 = new Double[1000000];
        IntStream.range(0, a1000000.length).forEach( i -> a1000000[i] = Math.random());
        baseSort.sort(a1000000);
    }

    @Override
    public BaseSort sort(Comparable[] a) {
        int N = a.length;
        int time = 0;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); time ++, j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
        System.out.println(time / N);
        return this;
    }
}
