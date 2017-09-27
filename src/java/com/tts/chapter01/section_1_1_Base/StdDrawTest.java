package com.tts.chapter01.section_1_1_Base;

import std.lib.StdRandom;

import static std.lib.StdDraw.*;

/**
 * @author: mike
 * @since: 2017/4/9
 */
public class StdDrawTest {
    public static void main(String[] args) {
        line(0, 0, 4, 4);
//        point(3, 2);
//        circle(5, 5, 2);

        int N = 50;
        double[] a = new double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.random();
        }
        for (int i = 0; i< N; i++){
            double x = 1.0*i/N;
            double y = a[i]/2.0;
            double rw = 0.5/N;
            double rh = a[i]/2.0;
            filledRectangle(x, y, rw, rh);
        }
    }
}