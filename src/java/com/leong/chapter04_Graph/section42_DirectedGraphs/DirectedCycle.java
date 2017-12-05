package com.leong.chapter04_Graph.section42_DirectedGraphs;

import com.leong.chapter04_Graph.TinyData;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 有向无环图（Directed Acyclic Graph）：一幅没有环的有向图。
 * 解决“一幅有向图是DAG吗”？只需要基于 DFS 寻找是否有向环即可。
 * 即如果我们找到一边边 v->w 而且栈 w 已经存在于栈中，就找到了一个环，
 * 因为栈表示的是一条 w->v 的有向路径，而 v->w 刚好补全了这个环。
 * 0 -> 5 -> 4 -> 3 -> 5
 * O
 * |  3
 * | / \
 * 5 -->4
 *
 * @author leongfeng created on 2017-11-29.
 */
public class DirectedCycle {

    private boolean[] marked;
    /**
     * 索引（入度点w）:值（出度点v）.
     * (edge[w]:v) 3:4; 4:5; 5:0.
     */
    private int[] edgeTo;
    /**
     * 有向环中所有的顶点.
     */
    private Stack<Integer> cycle;
    /**
     * 递归调用的栈上的所有顶点.
     */
    private boolean[] onStack;

    public DirectedCycle(Digraph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        onStack = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    /**
     * page: 372.
     * @param G
     * @param v
     */
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        /**
         * 递归为true.
         */
        onStack[v] = true;
        /**
         *                      adj(v)->w    marked[]        edgeTo[]       onStack[]
         *                                 0 1 2 3 4 5     0 1 2 3 4 5     0 1 2 3 4 5
         * dfs(0):2/1忽略       2 1 5
         *  dfs(5)              4          T 0 0 0 0 0     - - - - - 0     T 0 0 0 0 0
         *   dfs(4)             3          T 0 0 0 0 T     - - - - 5 0     T 0 0 0 0 T
         *    dfs(3)            5          T 0 0 T T T     - - - 4 5 0     T 0 0 0 T T
         *      检查点w:5                                                  true: 进行环检测
         *       cycle.push(x=v;x!=w;x=edgeTo[x]): 3 -> 4(edge[3]) -> 5(edgeTo[4])
         *       x == 5 exit loop: 5(w) -> 3(v)
         *       cycle 栈中： 3 -> 5 -> 4 -> 3 (top -> tail)
         */
        for (int w : G.adj(v)) {
            if (hasCycle()) {
                return;
            }

            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]){
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        /**
         * 递归完之后为false.
         */
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(TinyData.fromFilename("cycle.txt"));
        System.out.println(G);
        DirectedCycle finder = new DirectedCycle(G);
        if (finder.hasCycle()){
            StdOut.println("Cycle: ");
            for (int v : finder.cycle()){
                StdOut.print(v + " -> ");
            }
        } else {
            StdOut.println("No cycle.");
        }
    }
}
