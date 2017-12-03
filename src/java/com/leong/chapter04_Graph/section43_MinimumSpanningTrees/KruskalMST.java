package com.leong.chapter04_Graph.section43_MinimumSpanningTrees;

import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Queue;
import com.leong.chapter04_Graph.TinyData;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.UF;

import static edu.princeton.cs.algs4.StdOut.println;

/**
 * Kruskal 算法实现最小生成树。
 * <ul>
 * <li>用一条优先队列来将边按照权重排序；</li>
 * <li>用一个union-find数据结构来识别弄成的环的边；</li>
 * <li>一条队列保存最小生成树的边。</li>
 * </ul>
 *
 * @author leongfeng created on 2017/12/3.
 */
public class KruskalMST extends MST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }
        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            // 1. 最小权重的边
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            // 2. 判断失效的边
            if (uf.connected(v, w)) {
                continue;
            }
            // 3. 合并分量
            uf.union(v, w);
            // 4. 将最小权重的边加入最小生成树中
            mst.enqueue(e);
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }

    @Override
    public double weight() {
        return 0;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(TinyData.fromFilename(TinyData.FILENAME_TINYEWG));
        KruskalMST mst = new KruskalMST(G);
        mst.edges().forEach(e -> {
            println(e);
        });
    }

}
