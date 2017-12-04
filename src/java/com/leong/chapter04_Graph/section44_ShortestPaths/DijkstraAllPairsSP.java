package com.leong.chapter04_Graph.section44_ShortestPaths;

/**
 * 给点两点的最短（权重最小）路径。
 * 时间和空间与 EVlogV 成正比。
 * @author leongfeng created on 2017/12/4.
 */
public class DijkstraAllPairsSP {
    private DijkstraSP[] all;
    public DijkstraAllPairsSP(EdgeWeightedDigraph G){
        all = new DijkstraSP[G.V()];
        for (int v = 0; v < G.V(); v++){
            all[v] = new DijkstraSP(G, v);
        }
    }

    /**
     * 起点 s 到终点 t 之间的最短路径树。
     * @param s 起点
     * @param t 终点
     * @return 路径树
     */
    public Iterable<DirectedEdge> path(int s, int t){
        return all[s].pathTo(t);
    }

    /**
     * s 到 t 之间的最短路径（最小权重）。
     * @param s 起点
     * @param t 终点
     * @return double
     */
    public double dist(int s, int t){
        return all[s].distTo(t);
    }
}
