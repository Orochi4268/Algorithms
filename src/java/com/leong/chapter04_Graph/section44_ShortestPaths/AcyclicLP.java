package com.leong.chapter04_Graph.section44_ShortestPaths;

import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Stack;
import com.leong.chapter04_Graph.TinyData;
import com.leong.chapter04_Graph.section42_DirectedGraphs.Topological;

import static edu.princeton.cs.algs4.StdOut.print;
import static edu.princeton.cs.algs4.StdOut.printf;
import static edu.princeton.cs.algs4.StdOut.println;

/**
 * 无环加权有向图中的单点最长路径（O(E+V)）。
 * 解决“是否存在一条从s到给定的顶点v的路径？如果有，找出最长（总权重最大）的那条路径？”
 *
 * @author leongfeng created on 2017/12/5.
 */
public class AcyclicLP {

    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public AcyclicLP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.NEGATIVE_INFINITY;
        }
        distTo[s] = 0;
        Topological top = new Topological(G);
        for (int v : top.order()){
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)){
            int w = e.to();
            if (distTo[w] < distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }
    public boolean hasPathTo(int v){
        return distTo[v] > Double.NEGATIVE_INFINITY;
    }
    public Iterable<DirectedEdge> pathTo(int v){
        if (!hasPathTo(v)){
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()] ){
            path.push(e);
        }
        return path;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(TinyData.fromFilename("tinyEWDAG.txt"));
        println(G);
        println("---------------------------");
        int s  = 5;
        AcyclicLP lp = new AcyclicLP(G, s);
        for (int v = 0; v < G.V(); v ++){
            if (lp.hasPathTo(v)){
                printf("%d to %d: (%.2f) ", s, v, lp.distTo(v));
                for (DirectedEdge e : lp.pathTo(v)){
                    print(e + " ");
                }
                println();
            } else {
                printf("%d to %d       no path", s, v);
                println();
            }
        }
    }
}
