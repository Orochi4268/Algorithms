package com.leong.chapter04_Graph.section41_UndirectedGraphs;

import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Bag;
import com.leong.chapter02_Sorting.BaseSort;
import com.leong.chapter04_Graph.BaseSearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

/**
 * 无向图（Undirected Graph）：使用邻接表实现.
 * @author leongfeng created on 2017/11/25.
 */
public class Graph {
    /**
     * 顶点数.
     */
    private final int V;
    /**
     * 边数.
     */
    private int E;
    /**
     * 邻接表.
     */
    private Bag<Integer>[] adj;
    /**
     * 创建一个含有V个顶点但不包含有边的图.
     * @param V 顶点个数
     */
    public Graph(int V){
        if (V < 0) {
            throw new RuntimeException("Number of vertices must be nonnegative");
        }
        this.V = V;
        this.E = 0;
        this.adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++){
            adj[i] = new Bag<>();
        }
    }

    /**
     * 从标准输入流 {@linkplain In} 读入一幅图.
     * @param in 输入流
     */
    public Graph(In in){
        this(in.readInt());
        int E = in.readInt();
        // 添加边
        for (int i = 0; i < E; i++){
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    /**
     * 顶点数.
     * @return int
     */
    public int V(){
        return V;
    }

    /**
     * 边数.
     * @return int
     */
    public int E(){
        return E;
    }

    /**
     * 向图中添加一条边 v-w(w-v).
     * @param v 点v
     * @param w 点w
     */
    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
        E ++;
    }

    /**
     * 和 v 相信的所有顶点.
     * @param v 点v
     * @return 所有相邻顶点, {@linkplain Bag}
     */
    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        sb.append(V).append(" vertexes, ").append(E).append(" Edges").append(NEWLINE);
        for (int v = 0; v < V; v++){
            sb.append(v).append(": ");
            for (int w : adj[v]){
                sb.append(w).append(" ");
            }
            sb.append(NEWLINE);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        In in = new In(new File(BaseSearch.class.getResource("").getPath() + File.separator + "tinyG.txt"));
        Graph g = new Graph(in);
        StdOut.println(g);
    }
}
