package com.leong.chapter04_Graph.section43_MinimumSpanningTrees;

import com.leong.chapter04_Graph.TinyData;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 加权无向图.
 * @author leongfeng created on 2017/11/29.
 */
public class EdgeWeightedGraph {
    private final int V;
    private int E;
    /**
     * 加权边.
     */
    private Bag<Edge>[] adj;
    public EdgeWeightedGraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++){
            adj[v] = new Bag<>();
        }
    }

    public EdgeWeightedGraph(In in){
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++){
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E ++;
    }

    /**
     * 返回点 v 的加权边.
     * @param v
     * @return
     */
    public Iterable<Edge> adj(int v){
        return adj[v];
    }

    /**
     * 图的所有的边.
     * @return
     */
    public Iterable<Edge> edges(){
        Bag<Edge> edges = new Bag<>();
        for (int v = 0; v < V; v++){
            int selfLoops = 0;
            for (Edge e : adj(v)){
                if (e.other(v) > v){
                    edges.add(e);
                } else if (e.other(v) == v){
                    // only add one copy of each self loop
                    /*if (selfLoops % 2 == 0){
                        edges.add(e);
                        selfLoops ++;
                    }*/
                }
            }
        }
        return edges;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        String newline = System.getProperty("line.separator");
        sb.append(V).append(" edges; ").append(E).append(" vertexes:").append(newline);
        for (int v = 0; v < V; v++){
            sb.append(v).append(": ");
            for (Edge e : adj(v)){
                sb.append(e).append("; ");
            }
            sb.append(newline);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(TinyData.fromFilename("tinyEWG.txt"));
        StdOut.println(G);
    }
}
