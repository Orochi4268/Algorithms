package com.leong.chapter04_Graph.section42_DirectedGraphs;

import com.leong.chapter04_Graph.TinyData;
import com.leong.chapter04_Graph.section44_ShortestPaths.EdgeWeightedDigraph;
import com.leong.chapter04_Graph.section44_ShortestPaths.EdgeWeightedDirectedCycle;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * 顶点的深度优先次序与拓扑排序.
 * 拓扑顺序：给定一幅有向图，将所有的顶点排序，使得所有的有向边均从排在前面的元素指向排在后面的元素。
 * 当且仅当一幅有向图是DAG时它才能进行拓扑排序。
 * @author leongfeng created on 2017-11-29.
 */
public class Topological {
    /**
     * 顶点的拓扑顺序：有向图的逆后序顺序.
     */
    private Iterable<Integer> order;

    /**
     * 需要是DAG.
     * @param G DAG 图
     */
    public Topological(Digraph G){
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if (!cycleFinder.hasCycle()){
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Topological(EdgeWeightedDigraph G){
        EdgeWeightedDirectedCycle cycleFinder = new EdgeWeightedDirectedCycle(G);
        if (!cycleFinder.hasCycle()){
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public boolean isDAG(){
        return order != null;
    }

    public Iterable<Integer> order(){
        return order;
    }

    public static void main(String[] args) {
        SymbolDigraph symbolDigraph = new SymbolDigraph(TinyData.BASE_PATH + "tinyDAG.txt", "\\s");
        Topological topo = new Topological(symbolDigraph.G());
        for (int v : topo.order()){
            StdOut.print(symbolDigraph.name(v) + " ");
        }
    }
}
