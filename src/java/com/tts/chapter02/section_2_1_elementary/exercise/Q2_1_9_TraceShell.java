package com.tts.chapter02.chapter2_1.exercise;

import com.tts.chapter02.BaseSort;
import std.lib.StdDraw;

import java.awt.*;

/**
 * @author: mike
 * @since: 2017/4/9
 */
public class Q2_1_9_TraceShell extends BaseSort{

    private static int line = 0;
    @Override
    public BaseSort sort(Comparable[] a){
        int n = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n/3)
            h = 3*h + 1;

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                int j;
                for (j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
                draw(a, h, i, j);
                line++;
            }
            h /= 3;
            footer(a);
            line++;
        }
        return this;
    }
    private static void draw(Comparable[] a, int h, int ith, int jth) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(-3.75, line, h + "");
        StdDraw.text(-2.50, line, ith + "");
        StdDraw.text(-1.25, line, jth + "");
        for (int i = 0; i < a.length; i++) {
            if (i == jth)                StdDraw.setPenColor(StdDraw.BOOK_RED);
            else if (i > ith)            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            else if (i < jth)            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            else if ((i % h) == (jth % h)) StdDraw.setPenColor(StdDraw.BLACK);
            else                         StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.text(i, line, String.valueOf(a[i]));
        }
    }
    // display header
    public void header(Comparable[] a) {
        int n = a.length;

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(n/2.0, -3, "a[ ]");
        for (int i = 0; i < n; i++)
            StdDraw.text(i, -2, i + "");
        StdDraw.text(-3.75, -2, "h");
        StdDraw.text(-2.50, -2, "i");
        StdDraw.text(-1.25, -2, "j");
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(-4, -1.65, n - 0.5, -1.65);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < a.length; i++)
            StdDraw.text(i, -1, String.valueOf(a[i]));
    }
    public void footer(Comparable[] a) {
        int n = a.length;
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < n; i++)
            StdDraw.text(i, line, String.valueOf(a[i]));
    }

    public static void main(String[] args) {
        String[] a = new String[]{"E","A","S","Y","S","H","E","L","L","S","O","R","T","Q","U","E","S","T","I","O","N"};

        int n = a.length;
        int rows = 0;
        int h = 1;
        while (h < n/3){
            h = 3*h + 1;
            rows += (n - h +1);
        }
        rows += (n - h +1);

        StdDraw.setCanvasSize(30*(n+3), 30*(rows+3));
        StdDraw.setXscale(-4, n+1);
        StdDraw.setYscale(rows, -4);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 13));

        Q2_1_9_TraceShell traceShell = new Q2_1_9_TraceShell();
        // draw the header
        traceShell.header(a);
        // sort the array
        traceShell.sort(a);
    }
}
