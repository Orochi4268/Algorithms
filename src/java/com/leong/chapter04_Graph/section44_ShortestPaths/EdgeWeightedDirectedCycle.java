package com.leong.chapter04_Graph.section44_ShortestPaths;

import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Stack;

import static edu.princeton.cs.algs4.StdOut.print;
import static edu.princeton.cs.algs4.StdOut.printf;
import static edu.princeton.cs.algs4.StdOut.println;

/**
 * 判断是否是加权有向无环图。
 * @author leongfeng created on 2017/12/4.
 */
public class EdgeWeightedDirectedCycle {

    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private boolean[] onStack;
    /**
     * 加权有向环中的所有顶点。
     */
    private Stack<DirectedEdge> cycle;

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
        marked = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onStack = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++){
            if (!marked[v]){
                dfs(G, v);
            }
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (DirectedEdge e : G.adj(v)){
            if (hasCycle()) {
                return;
            }
            int w = e.to();
            if (!marked[w]){
                edgeTo[w] = e;
                dfs(G, w);
            } else if (onStack[w]){
                cycle = new Stack<>();
                while (e.from() != w){
                    cycle.push(e);
                    e = edgeTo[e.from()];
                }
                cycle.push(e);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle(){
        return cycle;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(4);
        G.addEdge(new DirectedEdge(0, 1, .5));
        G.addEdge(new DirectedEdge(1, 2, .4));
        G.addEdge(new DirectedEdge(2, 0, .3));
        G.addEdge(new DirectedEdge(2, 3, .2));
        println(G);
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
        println("has a cycle? " + finder.hasCycle());
        for (DirectedEdge e : finder.cycle()){
            print(e+" | ");
        }
    }
}

