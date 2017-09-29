package com.leong.chapter01.section_1_2.ex;

import edu.princeton.cs.algs4.StdDraw;

/**
 *
 * Created by leong on 2017/9/29.
 */
public class Ex_10 {
    public static void main(String[] args) {
        VisualCounter vc = new VisualCounter(10, 5);
        for (int i = 0; i < 10; i++){
            vc.increment();
        }
    }

}

class VisualCounter{
    private int max;
    private int N;
    private int count;
    private int n;
    public VisualCounter(int N, int max){
        this.N = N;
        this.max = max;
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(-max, max);
        StdDraw.setPenRadius(0.005);
    }

    public void increment(){
        if (n + 1> N ){
            System.out.println("操作次数已达最大值");
            return;
        }
        if (count + 1 > max){
            System.out.println("超过max值");
            return;
        }
        count ++;
        n++;
        StdDraw.point(n, count);
    }

    public void decrement(){
        if (n + 1> N ){
            System.out.println("操作次数已达最大值");
            return;
        }
        if (count - 1 > - max){
            System.out.println("超过max值");
            return;
        }
        count --;
        n++;
        StdDraw.point(n, count);
    }

    public int count(){
        return count;
    }

    @Override
    public String toString() {
        return "Mean (" + this.count + " values): " + this.count();
    }
}