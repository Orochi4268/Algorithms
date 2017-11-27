package com.leong.chapter04_Graph.section41_UndirectedGraphs;

import com.leong.chapter04_Graph.TinyData;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 间隔的度数.
 * @author leongfeng created on 2017/11/27.
 */
public class DegreesOfSeparation {
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(TinyData.ROUTES_TXT, " ");
        Graph graph = sg.G();
        String source = "JFK";
        if (!sg.contains(source)){
            StdOut.println(source + " not in database.");
            return;
        }
        int s = sg.index(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(graph, s);
        int count = 0;
        while (!StdIn.isEmpty()){
            String sink = StdIn.readLine();
            if (sg.contains(sink)){
                int t = sg.index(sink);
                if (bfs.hasPathTo(t)){
                    for (int v : bfs.pathTo(t)){
                        StdOut.println(" " + sg.name(v));
                    }
                } else {
                    StdOut.println("Not Connected!");
                }
            }else {
                StdOut.println("Not in database!");
            }
            count ++;
            if (count == 2){
                break;
            }
        }
    }
}
