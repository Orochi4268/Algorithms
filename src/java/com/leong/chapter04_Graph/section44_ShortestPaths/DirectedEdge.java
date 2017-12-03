package com.leong.chapter04_Graph.section44_ShortestPaths;

/**
 * 加权有向边.
 * @author leongfeng created on 2017/12/3.
 */
public class DirectedEdge {
    private int v;
    private int w;
    private double weight;
    public DirectedEdge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight(){
        return weight;
    }

    /**
     * 指出这条边的顶点。
     * @return v
     */
    public int from(){
        return v;
    }

    /**
     * 这条边指向的顶点。
     * @return w
     */
    public int to(){
        return w;
    }

    @Override
    public String toString(){
        return String.format("%d->%d %.2f", v, w, weight);
    }
}
