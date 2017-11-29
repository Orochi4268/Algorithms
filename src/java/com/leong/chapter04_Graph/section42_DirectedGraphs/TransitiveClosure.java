package com.leong.chapter04_Graph.section42_DirectedGraphs;

/**
 * 顶点对的可达性.
 * @author leongfeng created on 2017-11-29.
 */
public class TransitiveClosure {
    private DirectedDFS[] all;
    TransitiveClosure(Digraph G){
        all = new DirectedDFS[G.V()];
        for (int v = 0; v < G.V(); v++){
            all[v] = new DirectedDFS(G, v);
        }
    }

    boolean reachable(int v, int w){
        return all[v].marked(w);
    }
}
