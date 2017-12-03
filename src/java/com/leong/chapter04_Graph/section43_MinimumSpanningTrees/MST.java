package com.leong.chapter04_Graph.section43_MinimumSpanningTrees;

/**
 * 最小生成树(Minimum Spanning Trees).
 * 图的生成树是它的一棵含有其所有顶点的无环连通子图。
 * 一幅加权图的最小生成树是它的一棵权值最小的生成树。
 * 最小生成树的贪心算法：
 * 将含有 V 个顶点的任意加权连通图中属于最小生成树的边标记为黑色：
 * 1. 初始状态下所有边均为灰色，找到一种切分，它产生的横切边均不为黑色；
 * 2. 将它权重最小的横切边标记为黑色；
 * 3. 反复，直到标记了 V-1 条黑色边为止。
 * @author leongfeng created on 2017/11/29.
 */
public abstract class MST {
    /**
     * 最小生成树的所有边。
     * @return 迭代对象
     */
    public abstract Iterable<Edge> edges();

    /**
     * 最小生成树的权重。
     * @return double
     */
    public abstract double weight();
}
