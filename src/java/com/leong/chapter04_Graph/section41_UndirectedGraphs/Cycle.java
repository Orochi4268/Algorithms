package com.leong.chapter04_Graph.section41_UndirectedGraphs;

import com.leong.chapter04_Graph.TinyData;
import edu.princeton.cs.algs4.StdOut;

/**
 * 判断图是否是无环图.
 * @author leongfeng created on 2017/11/26.
 */
public class Cycle {

    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph graph){
        marked = new boolean[graph.V()];
        for (int s = 0; s < graph.V(); s++){
            if (!marked[s]){
                dfs(graph, s, s);
            }
        }
    }

    private void dfs(Graph graph, int v, int u) {
        marked[v] = true;
        for (int w : graph.adj(v)){
            if (!marked[w]){
                dfs(graph, w, v);
            } else if (w != u){
                hasCycle = true;
            }

        }
    }
    public boolean hasCycle(){
        return hasCycle;
    }
    public static void main(String[] args) {
//        Graph graph = new Graph(TinyData.tinyGG());
        Graph graph = new Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        StdOut.println(graph);
        Cycle cycle = new Cycle(graph);
        StdOut.println(graph + "has cycle ? ");
        StdOut.println(cycle.hasCycle());
    }
}
