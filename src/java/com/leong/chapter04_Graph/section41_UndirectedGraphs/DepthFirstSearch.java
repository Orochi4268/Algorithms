package com.leong.chapter04_Graph.section41_UndirectedGraphs;

import com.leong.chapter04_Graph.BaseSearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.Iterator;

import static edu.princeton.cs.algs4.StdOut.print;
import static edu.princeton.cs.algs4.StdOut.println;

/**
 * 深度优先搜索(DFS).
 * 解决了单点连通性的问题，使得用例可以判断其他顶点和给定的起点是否连通.
 *
 * @author leongfeng created on 2017/11/25.
 */
public class DepthFirstSearch extends BaseSearch {
    /**
     * 被访问到的顶点.
     */
    private boolean[] marked;
    /**
     * 与s连通的顶点总数.
     */
    private int count;

    /**
     * 非使用递归遍历.
     * 1进栈->2进栈->4进栈->8进栈->5进栈->5的相邻结点2/8已经被访问过，出栈(842同理)->
     * 栈里只有1->3入栈->6入栈->7入栈->和5一样，7出栈->631出栈
     * @param G 图
     */
    public DepthFirstSearch(final Graph G) {
        marked = new boolean[G.V()];
        int s = 1;
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++) {
            adj[v] = G.adj(v).iterator();
        }

        println("Non recursive....");
        print(s + "->");
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        marked[s] = true;
        while (!stack.isEmpty()) {
            int v = stack.peek();

            if (adj[v].hasNext()) {
                int w = adj[v].next();
                if (!marked[w]) {
                    print(w + "->");
                    marked[w] = true;
                    stack.push(w);
                }
            } else {
                // 需要遍历完毕v的相邻顶点才能将v弹出。
                stack.pop();
            }
        }
        println();
        println("Non recursive....");
    }

    /**
     * 深度优先搜索递归标记与s连通的其它顶点.
     *
     * @param G 图
     * @param s 起点
     */
    public DepthFirstSearch(final Graph G, final int s) {
        marked = new boolean[G.V()];
        // 从起点 s 进行dfs
        dfs(G, s);
    }

    /**
     * 从点v 进行深度优先搜索(DFS).
     *
     * @param g 图g
     * @param v 点v
     */
    private void dfs(final Graph g, final int v) {
        StdOut.print(v + "->");
        marked[v] = true;
        count++;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    /**
     * 点 v 和起点 s 是否连通?
     *
     * @param v 点v
     * @return boolean
     */
    @Override
    public boolean marked(final int v) {
        return marked[v];
    }

    /**
     * 和起点s 连通总数.
     *
     * @return int
     */
    @Override
    public int count() {
        return count;
    }

    public static void main(final String[] args) {
        Graph graph = new Graph(new In(new File(BaseSearch.class.getResource("").getPath()
                + File.separator + "dfs-bfs.txt")));
        println(graph);
        println("---path------");
        DepthFirstSearch dfs = new DepthFirstSearch(graph, 1);
        println();
        for (int v = 0; v < graph.V(); v++) {
            if (dfs.marked(v)) {
                StdOut.print(v + " ");
            }
        }
        println();
        println(dfs.count());
        dfs = new DepthFirstSearch(graph);

    }
}
