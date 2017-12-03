package com.leong.chapter04_Graph.section43_MinimumSpanningTrees;

import java.util.Iterator;

/**
 * 加权边.
 * <ul>
 *     <li>{@linkplain #v} 点v</li>
 *     <li>{@linkplain #w} 点w</li>
 *     <li>{@linkplain #weight} 边的权重</li>
 * </ul>
 * @author leongfeng created on 2017/11/29.
 */
public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    /**
     * 权重.
     */
    private final double weight;
    /**
     * 加权边.
     * @param v 点v
     * @param w 点w
     * @param weight 权重
     */
    public Edge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * 边的权重.
     * @return 权重
     */
    public double weight(){
        return weight;
    }

    /**
     * 边两端的顶点之一，此处返回{@linkplain #v}.
     * @return 点v
     */
    public int either(){
        return v;
    }

    /**
     * 如果 v 的值等于返回 {@linkplain #v} 返回 {@linkplain #w}，如果相反则返回 {@linkplain #v}，否则招聘异常.
     * @return 点
     */
    public int other(int v){
        if (v == this.v){
            return this.w;
        } else if (v == this.w){
            return this.v;
        } else {
            throw new RuntimeException("Illegal endpoint");
        }
    }

    /**
     * 根据{@linkplain #weight} 来返回比较值.
     * @param that 另一条边
     * @return 比较结果
     */
    @Override
    public int compareTo(Edge that){
        if (this.weight() < that.weight()){
            return -1;
        } else if (this.weight() > that.weight()){
            return  +1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString(){
        return String.format("%d-%d %.2f", v, w, weight);
    }

}
