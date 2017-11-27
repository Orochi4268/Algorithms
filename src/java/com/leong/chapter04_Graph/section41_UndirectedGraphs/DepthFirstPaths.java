package com.leong.chapter04_Graph.section41_UndirectedGraphs;

import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Stack;
import com.leong.chapter04_Graph.BasePaths;
import com.leong.chapter04_Graph.BaseSearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

/**
 * @author leongfeng created on 2017/11/25.
 */
public class DepthFirstPaths extends BasePaths {


    public DepthFirstPaths(Graph graph, int s){
        super(graph, s);
        dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        for (int w : graph.adj(v)){
            if (!marked[w]){
                edgeTo[w] = v;
                dfs(graph, w);
            }
        }
    }


    public static void main(String[] args) {
        Graph graph = new Graph(new In(new File(BaseSearch.class.getResource("").getPath() + File.separator + "tinyGG.txt")));
        StdOut.println(graph);
        int s = 0;
        DepthFirstPaths dfp = new DepthFirstPaths(graph, s);
        for (int v = 0; v < graph.V(); v++){
            if (dfp.hasPathTo(v)){
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : dfp.pathTo(v)){
                    if (x == s){
                        StdOut.print(x);
                    } else {
                        StdOut.print("-" + x);
                    }
                }
                StdOut.println();
            }else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }
        }
    }
}
