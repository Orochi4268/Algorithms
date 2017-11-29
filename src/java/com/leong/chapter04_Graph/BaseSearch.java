package com.leong.chapter04_Graph;

import com.leong.chapter04_Graph.section41_UndirectedGraphs.Graph;

/**
 * 找到图中与起点（source）连通的其他顶点.
 * @author leongfeng created on 2017/11/25.
 */
public abstract class BaseSearch {
    public Graph graph;
    public int s;
    public BaseSearch(){

    }
    /**
     * 找到和起点s连通的所有顶点.
     * @param graph 图
     * @param s 点s
     */
    public BaseSearch(Graph graph, int s){
        this.graph = graph;
        this.s = s;
    }

    /**
     * 点 v 和 s 是否连通.
     * @param v 点v
     * @return boolean
     */
    public abstract boolean marked(int v);

    /**
     * 与点s 连通的顶点总数.
     * @return int
     */
    public abstract int count();
}
