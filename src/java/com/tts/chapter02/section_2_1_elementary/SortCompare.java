package com.tts.chapter02.chapter2_1;

import std.lib.StdOut;
import std.lib.StdRandom;
import std.lib.Stopwatch;

import static std.lib.StdIn.readInt;
import static std.lib.StdIn.readString;

/**
 * @author: mike
 * @since: 2017/4/9
 */
public class SortCompare {

    public static double time(String alg, Comparable[] a){
        Stopwatch timer = new Stopwatch();
        switch (alg){
            case "Insertion":  new InsertionSort().sort(a); break;
            case "Selection": new SelectionSort().sort(a); break;
            case "Shell": new ShellSort().sort(a);break;
            default:
        }
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int n, int trials){
        double total = 0.0;
        Double[] a = new Double[n];
        for (int t = 0; t < trials; t++) {
            for (int i = 0; i < n; i++)
                a[i] = StdRandom.uniform(0.0, 1.0);
//            IntStream.range(0, n).forEach(i -> a[i] = 1.0 * i);
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = readString();
        String alg2 = readString();
        int n = readInt();
        int trials = readInt();

        double time1 = timeRandomInput(alg1, n, trials);
        double time2 = timeRandomInput(alg2, n, trials);

        StdOut.printf("%s: %f; %s: %f\n", alg1, time1, alg2, time2);
        StdOut.printf("For %d random Doubles\n    %s is", n, alg1);
        StdOut.printf(" %.1f times faster than %s\n", time2/time1, alg2);
    }
}
