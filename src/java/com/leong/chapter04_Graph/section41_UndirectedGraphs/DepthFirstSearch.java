package com.leong.chapter04_Graph.section41_UndirectedGraphs;

import com.leong.chapter04_Graph.BaseSearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

/**
 * 深度优先搜索(DFS).
 * @author leongfeng created on 2017/11/25.
 */
public class DepthFirstSearch extends BaseSearch {
    private boolean[] marked;
    private int count;
    public DepthFirstSearch(Graph G, int s){
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        count ++;
        for (int w : g.adj(v)){
            if (!marked[w]){
                dfs(g, w);
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
        Graph graph = new Graph(new In(new File(BaseSearch.class.getResource("").getPath() + File.separator + "tinyGG.txt")));
        StdOut.println(graph);
        DepthFirstSearch dfs = new DepthFirstSearch(graph, 0);
        for (int v = 0; v < graph.V(); v++) {
            if (dfs.marked(v)){
                StdOut.print(v + " ");
            }
        }
    }
}
