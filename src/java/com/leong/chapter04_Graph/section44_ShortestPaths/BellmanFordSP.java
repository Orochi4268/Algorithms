package com.leong.chapter04_Graph.section44_ShortestPaths;

import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Queue;

/**
 * 基于队列的 Bellman-Ford 算法查找最短路径。
 * 可以解决“是否存在一条从s到给定的顶点v的有向路径？如果有，找出最短；如果没有，说明有负权重环。”等问题。
 * Bellman-Ford 算法：
 * 在任意含有V个顶点的加权有向图中给定起点s，从s无法到达任何负权重环，以下算法能够解决其中的单点最短路径问题：将distTo[s]初始化为0，
 * 其他distTo[]元素初始化为无穷大。以任意顺序放松有向图的所有边，重复V轮。
 * 步骤：
 * 1. 将起点s加入队列中，然后进入一个循环，其中每次都从队列中取出一个顶点并将其放松
 * @author leongfeng created on 2017/12/7.
 */
public class BellmanFordSP {
    /**
     * 起点s到相应顶点的距离.
     */
    private double[] distTo;
    /**
     * 起点s到某个顶点的最后一条边.
     */
    private DirectedEdge[] edgeTo;
    /**
     * 是否在队列中.
     */
    private boolean[] onQ;
    /**
     * 正在被放松的顶点.
     */
    private Queue<Integer> queue;
    /**
     * relax 的次数.
     */
    private int cost;
    /**
     * edgeTo[] 中是否有负权重.
     */
    private Iterable<DirectedEdge> cycle;

    /**
     * 查找路径路径.
     * @param G 图
     * @param s 起点
     */
    public BellmanFordSP(final EdgeWeightedDigraph G, final int s){
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new Queue<>();
        for (int v = 0; v < G.V(); v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        queue.enqueue(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()){
            int v = queue.dequeue();
            onQ[s] = false;
            relax(G, v);
        }
    }

    /**
     * 放松边.
     * @param G 图
     * @param v 点
     */
    private void relax(final EdgeWeightedDigraph G, final int v) {
        for (DirectedEdge e : G.adj(v)){
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]){
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
            if (cost++ % G.V() == 0){
                findNegativeCycle();
            }
        }

    }

    /**
     * 是否存在负权重环.
     */
    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++){
            if (edgeTo[v] != null){
                spt.addEdge(edgeTo[v]);
            }
        }
        EdgeWeightedDirectedCycle cf = new EdgeWeightedDirectedCycle(spt);
        cycle = cf.cycle();
    }

    /**
     * 图是否有负权重环.
     * @return boolean
     */
    private boolean hasNegativeCycle() {
        return cycle == null;
    }

    /**
     * 返回环.
     * @return 环
     */
    private Iterable<DirectedEdge> negativeCycle(){
        return cycle;
    }
    public static void main(final String[] args) {

    }
}
