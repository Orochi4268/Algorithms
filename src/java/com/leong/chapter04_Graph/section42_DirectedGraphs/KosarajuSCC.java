package com.leong.chapter04_Graph.section42_DirectedGraphs;

import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Bag;
import com.leong.chapter04_Graph.TinyData;
import com.leong.chapter04_Graph.section41_UndirectedGraphs.CC;
import edu.princeton.cs.algs4.StdOut;

/**
 * 强连通： v -> w; w -> v.
 * 强连通分量（Strong connectivity in digraphs）：
 * 强连通性将所有顶点分为了一些造价类，每个等价类都是由相互均为强连通的顶点的最大子集组成，这些子集就称为强连通分量。
 * 解决“给定的两个顶点是连通的吧？这幅有向图中含有多少个连通分量？”
 * @author leongfeng created on 2017-11-29.
 */
public class KosarajuSCC {
    private boolean[] marked;
    /**
     * 强连通分量的标识符.
     */
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        // G 的逆后序排列
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)){
            if (!marked[w]){
                dfs(G, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(TinyData.fromFilename("tinyDG.txt"));
        StdOut.println(G);
        KosarajuSCC cc = new KosarajuSCC(G);
        int M = cc.count();
        StdOut.println(M + " components");

        Bag<Integer>[] components = (Bag<Integer>[]) new Bag[M];
        for (int i = 0; i < M; i++ ){
            components[i] = new Bag<>();
        }

        for (int v = 0; v < G.V(); v++){
            components[cc.id(v)].add(v);
        }

        for (int i = 0; i < M; i++){
            for (int v : components[i]){
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
