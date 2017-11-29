package com.leong.chapter04_Graph.section42_DirectedGraphs;

import com.leong.chapter04_Graph.BaseSearch;
import com.leong.chapter04_Graph.TinyData;
import edu.princeton.cs.algs4.StdOut;

/**
 * 有向图的深度优先搜索.
 * 可以解决“是否存在一条从s到达给定顶点v的有向路径？”等类似问题。
 *
 * @author leongfeng created on 2017-11-28.
 */
public class DirectedDFS extends BaseSearch {
    private boolean[] marked;
    private int count;

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        // 从顶点s开始进行dfs
        dfs(G, s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int s : sources) {
            if (!marked[s]) {
                dfs(G, s);
            }
        }
    }

    /**
     * 进行深度排行搜索.
     *
     * @param G 有向图G
     * @param v 点
     */
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    @Override
    public boolean marked(int v) {
        return marked[v];
    }

    @Override
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(TinyData.fromFilename(TinyData.FILENAME_TINYDG));
        DirectedDFS dfs = new DirectedDFS(G, 2);
        for (int v = 0; v < G.V(); v++){
            if (dfs.marked(v)){
                StdOut.print(v + " ");
            }
        }
    }
}
