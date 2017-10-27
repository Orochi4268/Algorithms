package com.leong.chapter01_Fundamentals.section12_Data_Abstraction;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 可视化累加器
 * Created by leong on 2017/9/29.
 */
public class VisualAccumulator {
    private double total;
    private int count;
    public VisualAccumulator(int trials, double max){
        StdDraw.setXscale(0, trials);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(0.005);
    }

    public void addDataValue(double val){
        count ++;
        total += val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(count, val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(count, total / count);
    }

    public double mean(){
        return this.total / count;
    }

    @Override
    public String toString() {
        return "Mean (" + this.total + " values): " + this.mean();
    }

    public static void main(String[] args) {
        int T = StdIn.readInt();
        double max = StdIn.readDouble();
        VisualAccumulator a = new VisualAccumulator(T, max);
        for (int t = 0; t < T; t++){
            a.addDataValue(StdRandom.uniform());
        }
        System.out.println(a);
    }
}
