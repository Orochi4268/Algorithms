package com.leong.chapter04_Graph.section43_MinimumSpanningTrees;

import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Queue;
import com.leong.chapter04_Graph.TinyData;
import edu.princeton.cs.algs4.MinPQ;

import static edu.princeton.cs.algs4.StdOut.println;


/**
 * 最小生成树的 Prim 算法的延时实现。
 * 首要问题：如何找出权重最小的边？
 * @author leongfeng created on 2017/12/2.
 */
public class LazyPrimMST extends MST {
    /**
     * 最小生成树的顶点.
     */
    private boolean[] marked;
    /**
     * 最小生成树的边.
     */
    private Queue<Edge> mst;
    /**
     * 横切边（包括失效的边）.
     */
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G){
        marked = new boolean[G.V()];
        mst = new Queue<>();
        pq = new MinPQ<>();

        visit(G, 0);
        while (!pq.isEmpty()){
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            // 跳过失效的边
            if (marked[v] && marked[w]){
                continue;
            }
            mst.enqueue(e);
            // 将顶点 v 或 w 的横切边
            if (!marked[v]){
                visit(G, v);
            }
            if (!marked[w]){
                visit(G, w);
            }
        }
    }

    /**
     * 为树添加一个顶点，将它标记为“已访问”并将它关联的所有未失效的边加入优先队列。
     * @param G
     * @param v
     */
    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)){
            if (!marked[e.other(v)]){
                pq.insert(e);
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }

    @Override
    public double weight() {
        double weight = 0;
        for (Edge e : edges()){
            weight += e.weight();
        }
        return weight;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(TinyData.fromFilename(TinyData.FILENAME_TINYEWG));
        LazyPrimMST mst = new LazyPrimMST(G);
        mst.edges().forEach(e -> {
            println(e);
        });
    }
}
