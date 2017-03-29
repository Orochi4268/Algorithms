package com.tts.chapter1;

import org.junit.Test;

import java.util.Random;
import java.util.Scanner;

import static std.lib.StdOut.printf;
import static std.lib.StdOut.println;

/**
 * @author phoenix
 * @since 2017/3/10
 */
public class ExerciseTest {

    @Test
    public void test1() {
        println((0 + 15) / 2);
        println(2.0 / 1000000 * 100000000.1);
        println(1 + 2 + "3");

        double t = 9.0;
        while (Math.abs(t - 9.0 / t) > .001)
            t = (9.0 / t + t) / 2.0;
        printf("%.5f\n", t);

        int sum = 0;
        for (int i = 1; i < 1000; i++)
            for (int j = 0; j < i; j++)
                sum++;
        println(sum);
        println((char) ('a' + 4));

        int N = 10;
        String s = "";
        for (int n = N; n > 0; n /= 2)
            s = (n % 2) + s;
        println(s);
        println(Integer.toBinaryString(N));


    }

    @Test
    public void test2() {
        Boolean[][] boo = new Boolean[10][10];
        boo = random(boo);
        for (int i = 0; i < boo.length; i++) {
            for (int j = 0; j < boo[0].length; j++) {
                if (boo[i][j])
                   println((j + i * 10) + ":" + "*");
                else
                    println((j + i * 10) + ":" + " ");
            }
        }
    }

    public static Boolean[][] random(Boolean[][] boo) {
        Random ran = new Random();
        for (int i = 0; i < boo.length; i++) {
            for (int j = 0; j < boo[0].length; j++) {
                boo[i][j] = ran.nextBoolean();
            }
        }
        return boo;
    }

    @Test
    public void test3(){
        println(exR1(6));
    }
    static int log(int N){
        int count = 0;
        while ( N>0 ){

        }
        return 0;
    }

    public static String exR1(int n)
    {
        if (n <= 0) return "";
        return exR1(n-3) + n + exR1(n-2) + n;
    }
}

class equalTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt(), c = scanner.nextInt();
        if (a == b && b == c) {
            println("equal");
        }
    }
}