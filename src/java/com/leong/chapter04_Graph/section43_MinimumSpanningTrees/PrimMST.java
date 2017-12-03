package com.leong.chapter04_Graph.section43_MinimumSpanningTrees;

import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Queue;
import com.leong.chapter04_Graph.TinyData;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Arrays;

import static edu.princeton.cs.algs4.StdOut.println;

/**
 * Prim 算法的即时实现.
 * @author leongfeng created on 2017/12/3.
 */
public class PrimMST  extends MST {
    /**
     * 距离树最近的边.
     */
    private Edge[] edgeTo;
    /**
     * 最近的边的权重：distTo = edge[W].weight().
     */
    private double[] distTo;
    /**
     * 如果v在树中则为true.
     */
    private boolean[] marked;
    /**
     * 有效的横切边.
     */
    private IndexMinPQ<Double> pq;
    private PrimMST(EdgeWeightedGraph G){
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(G.V());
        distTo[0] = 0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()){
            visit(G, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)){
            int w = e.other(v);
            if (marked[w]){
                continue;
            }
            // 更新 edgeTo 和 distTo
            if (e.weight() < distTo[w]){
                edgeTo[w] = e;
                distTo[w] = e.weight();
                // 更换为更小的权重.
                if (pq.contains(w)){
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        final Queue<Edge> mst = new Queue<>();
        Arrays.asList(edgeTo).forEach(e -> {
            if (e != null){
                mst.enqueue(e);
            }
        });
        return mst;
    }

    @Override
    public double weight() {
        return 0;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(TinyData.fromFilename(TinyData.FILENAME_TINYEWG));
        PrimMST mst = new PrimMST(G);
        mst.edges().forEach(e -> {
            println(e);
        });
    }
}
