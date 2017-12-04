package com.leong.chapter04_Graph.section44_ShortestPaths;

import com.leong.chapter04_Graph.TinyData;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

import static edu.princeton.cs.algs4.StdOut.*;

/**
 * Dijkstra 算法找出最短路径（Shortest Paths）。
 * 算法和 Prim 算法类似：构造最小生成树的每一步都向这这棵树添加一条新的边。
 * 实现的图。
 * 0: 0->2 0.26 | 0->4 0.38 |
 * 1: 1->3 0.29 |
 * 2: 2->7 0.34 |
 * 3: 3->6 0.52 |
 * 4: 4->7 0.37 | 4->5 0.35 |
 * 5: 5->1 0.32 | 5->7 0.28 | 5->4 0.35 |
 * 6: 6->4 0.93 | 6->0 0.58 | 6->2 0.40 |
 * 7: 7->3 0.39 | 7->5 0.28 |
 *
 * @author leongfeng created on 2017/12/3.
 */
public class DijkstraSP {
    /**
     * 元素对应的可达顶点构成一棵最短路径树。
     * edgeTo[v] = last edge on shortest s->v path．
     */
    private DirectedEdge[] edgeTo;
    /**
     * s 到达 v　的最小距离。
     * distTo[v] = distance  of shortest s->v path．
     */
    private double[] distTo;
    /**
     * 保存需要被放松的顶点。
     */
    private IndexMinPQ<Double> pq;

    /**
     * 查找最短路径。
     * @param G 加权有向图
     * @param s 起点
     */
    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<>(G.V());
        for (int i = 0; i < G.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        // 起点 s 设置为 0，其它的点为无穷大
        distTo[s] = 0;
        pq.insert(s, 0.0);
        // 从起点开始 relax
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
    }


    /**
     * 顶点的松弛。
     *
     * @param G 加权有向图
     * @param v 顶点
     */
    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[v] + e.weight() < distTo[w]) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    /**
     * 边的松弛。
     *
     * @param e 边
     */
    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        if (distTo[v] + e.weight() < distTo[w]) {
            distTo[w] = distTo[v] = e.weight();
            edgeTo[w] = e;
        }
    }

    /**
     * s 到 v 的距离，如果不存在则路径为无穷大。
     *
     * @param v
     * @return
     */
    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * 顶点 s 到 v 的路径。
     *
     * @param v
     * @return
     */
    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(TinyData.fromFilename(TinyData.FILENAME_TINYEWD));
        println(G);
        int s = 0;
        DijkstraSP sp = new DijkstraSP(G, s);
        for (int v = 0; v < G.V(); v++) {
            printf("%d to %d (%.2f): ", s, v, sp.distTo(v));
            if (sp.hasPathTo(v)) {
                for (DirectedEdge e : sp.pathTo(v)) {
                    print(e + " | ");
                }
                println();
            } else {
                printf("%d to %d         no path\n", s, v);
            }
        }
    }
}
