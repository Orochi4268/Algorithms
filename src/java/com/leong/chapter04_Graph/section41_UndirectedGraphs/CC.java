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
 *
 * @author leongfeng created on 2017/11/26.
 */
public class CC {
    /**
     * 标记已经访问过的顶点.
     */
    private boolean[] marked;
    /**
     * 连通分量 id.
     */
    private int[] id;
    /**
     * 连通分量总数.
     */
    private int count;

    /**
     * 查找连通分量.
     *
     * @param graph 图
     */
    public CC(final Graph graph) {
        marked = new boolean[graph.V()];
        id = new int[graph.V()];
        for (int s = 0; s < graph.V(); s++) {
            if (!marked[s]) {
                dfs(graph, s);
                // 只有一个连通分量（环）递归完毕count才会加1
                count++;
            }
        }
    }

    /**
     * 递归一个连通分量（环）.
     *
     * @param graph 图
     * @param v     点
     */
    private void dfs(final Graph graph, final int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }

    /**
     * v 和 w 是否连通?
     *
     * @param v 点
     * @param w 点
     * @return boolean
     */
    public boolean connected(final int v, final int w) {
        return id[v] == id[w];
    }

    /**
     * 连通分量数.
     *
     * @return int
     */
    public int count() {
        return count;
    }

    /**
     * v 所在的连通分量的标识符（0~ count() -1）.
     *
     * @param v 点
     * @return int
     */
    public int id(final int v) {
        return id[v];
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In(new File(BaseSearch.class.getResource("").getPath() + File.separator + "tinyG.txt")));
        CC cc = new CC(graph);
        int M = cc.count();
        StdOut.println(M + " components");

        Bag<Integer>[] components = (Bag<Integer>[]) new Bag[M];
        for (int i = 0; i < M; i++) {
            components[i] = new Bag<>();
        }

        for (int v = 0; v < graph.V(); v++) {
            components[cc.id(v)].add(v);
        }

        for (int i = 0; i < M; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
