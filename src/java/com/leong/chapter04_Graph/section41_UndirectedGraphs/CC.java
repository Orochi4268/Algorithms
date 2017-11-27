package com.leong.chapter04_Graph.section41_UndirectedGraphs;

import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Bag;
import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Queue;
import com.leong.chapter04_Graph.BaseSearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

/**
 * 连通分量（Connected Components）.
 * 使用DFS找出图中的所有连通分量.
 * @author leongfeng created on 2017/11/26.
 */
public class CC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph graph){
        marked = new boolean[graph.V()];
        id = new int[graph.V()];
        for (int s = 0; s < graph.V(); s++){
            if (!marked[s]){
                dfs(graph, s);
                count ++;
            }
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : graph.adj(v)){
            if (!marked[w]){
                dfs(graph, w);
            }
        }
    }

    /**
     * v 和 w 是否连通?
     * @param v 点
     * @param w 点
     * @return boolean
     */
    public boolean connected(int v, int w){
        return id[v] == id[w];
    }

    /**
     * 连通分量数.
     * @return int
     */
    public int count(){
        return count;
    }

    /**
     * v 所在的连通分量的标识符（0~ count() -1）.
     * @param v 点
     * @return int
     */
    public int id(int v){
        return id[v];
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In(new File(BaseSearch.class.getResource("").getPath() + File.separator + "tinyG.txt")));
        CC cc = new CC(graph);
        int M = cc.count();
        StdOut.println(M + " components");

        Bag<Integer>[] components = (Bag<Integer>[]) new Bag[M];
        for (int i = 0; i < M; i++ ){
            components[i] = new Bag<>();
        }

        for (int v = 0; v < graph.V(); v++){
            components[cc.id(v)].add(v);
        }

        for (int i = 0; i < M; i++){
            for (int v : components[i]){
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
