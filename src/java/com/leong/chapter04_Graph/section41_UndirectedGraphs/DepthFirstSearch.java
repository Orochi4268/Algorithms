package com.leong.chapter04_Graph.section41_UndirectedGraphs;

import com.leong.chapter04_Graph.BaseSearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

/**
 * 深度优先搜索(DFS).
 * 解决了单点连通性的问题，使得用例可以判官其他顶点和给定的起点是否连通.
 *
 * @author leongfeng created on 2017/11/25.
 */
public class DepthFirstSearch extends BaseSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        // 从起点 s 进行dfs
        dfs(G, s);
    }

    /**
     * 从点v 进行深度优先搜索(DFS).
     *
     * @param g 图g
     * @param v 点v
     */
    private void dfs(Graph g, int v) {
        StdOut.print(v + "->");
        marked[v] = true;
        count++;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    /**
     * 点 v 和起点 s 是否连通?
     *
     * @param v 点v
     * @return boolean
     */
    @Override
    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * 和起点s 连通总数.
     *
     * @return int
     */
    @Override
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In(new File(BaseSearch.class.getResource("").getPath()
                + File.separator + "dfs-bfs.txt")));
        StdOut.println(graph);
        StdOut.println("---path------");
        DepthFirstSearch dfs = new DepthFirstSearch(graph, 1);
        StdOut.println();
        for (int v = 0; v < graph.V(); v++) {
            if (dfs.marked(v)) {
                StdOut.print(v + " ");
            }
        }
    }
}
