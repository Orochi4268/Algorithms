package com.tts.chapter01.section_1_4;

import std.lib.StdDraw;
import std.lib.StdOut;
import std.lib.StdRandom;
import std.lib.Stopwatch;

/*************************************************************************
 * Compilation:  javac DoublingTest.java
 * Execution:    java DoublingTest
 * Dependencies: ThreeSum.java Stopwatch.java StdRandom.java StdOut.java
 * <p>
 * % java DoublingTest
 * 512 6.48
 * 1024 8.30
 * 2048 7.75
 * 4096 8.00
 * 8192 8.05
 * ...
 *************************************************************************/

public class DoublingTest {

    public static double timeTrial(int N) {
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch s = new Stopwatch();
        int cnt = ThreeSum.count(a);
        return s.elapsedTime();
    }


    public static void main(String[] args) {
        /*for (int N = 250; true; N += N) {
            double time = timeTrial(N);
            StdOut.printf("%7d %5.1f\n", N, time);
        }*/

        // 练习 1.4.3
        int MAX = 8000;
        int i = 0;
        int len = (int) (Math.log(MAX / 250) / Math.log(2)) + 1;
        int[] arrN = new int[len];
        double[] timeN = new double[len];
        for (int N = 250; N <= MAX; N += N) {
            double time = timeTrial(N);
            StdOut.printf("%7d %5.1f\n", N, time);
            arrN[i] = N;
            timeN[i++] = time;
        }
//        drawStandard(arrN,timeN);
        drawLog(arrN, timeN);
    }

    //绘制标准图形
    public static void drawStandard(int[] n, double[] time) {
        StdDraw.setXscale(-0.5, 1.2 * n[n.length - 1] / 1000.0);
        StdDraw.setYscale(-0.5, time[n.length - 1] * 1.2);
        //建立坐标系
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.001);
        StdDraw.line(0, 0, 1.1 * n[n.length - 1] / 1000, 0);
        StdDraw.line(0, 0, 0, 1.1 * time[n.length - 1]);
        for (int i = 1; i < 1.1 * n[n.length - 1] / 1000.0; i++)
            StdDraw.text(i, -0.2, i + "K");
        for (double t = 2; t < time[n.length - 1] * 1.1; t += 2)
            StdDraw.text(-0.2, t, t + "");
        //绘制图像
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.setPenRadius(0.02);
        for (int i = 0; i < n.length; i++)
            StdDraw.point(n[i] / 1000.0, time[i]);
    }

    //绘制对数图形
    public static void drawLog(int[] arrN, double[] timeN) {
        //将时间转换为其对数
        double[] timelog = new double[timeN.length];
        for (int i = 0; i < timeN.length; i++)
            timelog[i] = Math.log(timeN[i]);
        StdDraw.setXscale(-0.5, 1.2 * arrN[arrN.length - 1] / 1000.0);
        StdDraw.setYscale(-0.5, timelog[arrN.length - 1] * 1.2);
        //建立坐标系
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.001);
        StdDraw.line(0, 0, 1.1 * arrN[arrN.length - 1] / 1000, 0);
        StdDraw.line(0, 0, 0, 1.1 * timelog[arrN.length - 1]);
        for (int i = 1; i < 1.1 * arrN[arrN.length - 1] / 1000.0; i++)
            StdDraw.text(i, -0.2, i + "K");
        for (double t = 0; t < timelog[arrN.length - 1] * 1.1; t += timelog[arrN.length - 1] / 5)
            StdDraw.text(-0.2, t, String.format("%.2f", t));
        //绘制图像
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.setPenRadius(0.02);
        for (int i = 0; i < arrN.length; i++)
            StdDraw.point(arrN[i] / 1000.0, timelog[i]);
    }
} 

