package com.leong.chapter04_Graph.section41_UndirectedGraphs;

import com.leong.chapter04_Graph.TinyData;
import edu.princeton.cs.algs4.StdOut;

/**
 * G 是二分图吗？（双色问题）.
 * @author leongfeng created on 2017/11/26.
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;
    public TwoColor(Graph graph){
        marked = new boolean[graph.V()];
        color = new boolean[graph.V()];
        for (int s = 0; s < graph.V(); s++){
            if (!marked[s]){
                dfs(graph, s);
            }
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        for (int w : graph.adj(v)){
            if (!marked[w]){
                color[w] = !color[v];
                dfs(graph, w);
            } else if (color[w] == color[v]){
                isTwoColorable = false;
            }
        }
    }
    public boolean isBipartite(){
        return isTwoColorable;
    }

    public static void main(String[] args) {
//        Graph graph = new Graph(TinyData.tinyGG());
        Graph graph = new Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
//        graph.addEdge(1, 2);
        StdOut.println(graph);
        TwoColor twoColor = new TwoColor(graph);
        StdOut.println("is tow colorable?");
        StdOut.println(twoColor.isBipartite());
    }
}
