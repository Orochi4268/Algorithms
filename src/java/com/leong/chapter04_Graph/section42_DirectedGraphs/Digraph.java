package com.leong.chapter04_Graph.section42_DirectedGraphs;

import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Bag;
import com.leong.chapter04_Graph.TinyData;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 有向图.
 * 使用邻接表(Bag<Integer>[] adj)表示：
 * tinyDG.txt
 *  0: 5 1
 *  1:
 *  2: 0 3
 *  3: 5 2
 *  4: 3 2
 *  5: 4
 *  6: 9 4 0
 *  7: 6 8
 *  8: 7 9
 *  9: 11 10
 *  10: 12
 *  11: 4 12
 *  12: 9
 * @author leongfeng created on 2017-11-28.
 */
public class Digraph {
    /**
     * 顶点总数.
     */
    private final int V;
    /**
     * 边数.
     */
    private int E;
    /**
     * 邻接表构造有向图.
     */
    private Bag<Integer>[] adj;
    /**
     * 创建一幅含有v个顶点但没有边的有向图.
     * @param V V 个顶点
     */
    public Digraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++){
            adj[v] = new Bag<>();
        }
    }

    /**
     * 从 {@linkplain In} 中读取一幅有向图.
     *  第 1 个整数为边总数， 第 2 个整数为点数.
     * @param in @linkplain In}
     */
    public Digraph(In in){
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
     * 顶点的总数.
     * @return int
     */
    public int V(){
        return V;
    }
    /**
     * 边的总数.
     * @return int
     */
    public int E(){
        return E;
    }

    /**
     * 向有向图中添加一条边 v->w 的边.
     * @param v 点v
     * @param w 点w
     */
    public void addEdge(int v, int w){
        adj[v].add(w);
        E ++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    /**
     * 该图的反向图.
     * @return 返回图
     */
    public Digraph reverse(){
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++){
            for (int w : adj(v)){
                R.addEdge(w, v);
            }
        }
        return R;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        sb.append(V).append(" vertexes, ").append(E).append(" Edges").append(NEWLINE);
        for (int v = 0; v < V; v++){
            sb.append(v).append(": ");
            for (int w : adj[v]){
                sb.append(" -> ").append(w);
            }
            sb.append(NEWLINE);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(TinyData.fromFilename("tinyDG.txt"));
        StdOut.println(G);
    }
}
