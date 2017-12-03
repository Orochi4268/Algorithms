package com.leong.chapter04_Graph.section44_ShortestPaths;

import com.leong.chapter04_Graph.TinyData;
import com.leong.chapter04_Graph.section43_MinimumSpanningTrees.Edge;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import static edu.princeton.cs.algs4.StdOut.println;

/**
 * 加权有向图.
 * @author leongfeng created on 2017/12/3.
 */
public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;
    public EdgeWeightedDigraph(int V){
        this.V = V;
        this.E = 0;
        this.adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++){
            this.adj[v] = new Bag<>();
        }
    }

    public EdgeWeightedDigraph(In in){
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++){
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }
    public void addEdge(DirectedEdge edge){
        adj[edge.from()].add(edge);
        E ++;
    }

    public Iterable<DirectedEdge> adj(int v){
        return adj[v];
    }

    public Iterable<DirectedEdge> edges(){
        Bag<DirectedEdge> bag = new Bag<>();
        for (int v = 0; v < V; v++){
            for(DirectedEdge e : adj[v]){
                bag.add(e);
            }
        }
        return bag;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        sb.append(V).append(" edges ").append(E).append(" vertexes").append(NEWLINE);
        for (int v = 0; v < V; v++){
            sb.append(v).append(": ");
            for (DirectedEdge e : adj(v)){
                sb.append(e).append(" | ");
            }
            sb.append(NEWLINE);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(TinyData.fromFilename(TinyData.FILENAME_TINYEWD));
        println(G);
    }
}
