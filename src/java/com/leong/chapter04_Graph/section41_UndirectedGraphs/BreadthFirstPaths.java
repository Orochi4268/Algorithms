package com.leong.chapter04_Graph.section41_UndirectedGraphs;

import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Queue;
import com.leong.chapter04_Graph.BasePaths;
import com.leong.chapter04_Graph.BaseSearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

/**
 * 使用广度优先搜索查找图中的路径.
 *
 * @author leongfeng created on 2017/11/26.
 */
public class BreadthFirstPaths extends BasePaths {
    Queue<Integer> queue;
    public BreadthFirstPaths(Graph graph, int s) {
        super(graph, s);
        queue = new Queue<>();
        bfs(graph, s);
    }

    private void bfs(Graph graph, int s) {
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            // 删除顶点
            int v = queue.dequeue();
            StdOut.print(v + "->");
            for (int w : graph.adj(v)){
                // 没有被标记过的相邻顶点
                if (!marked[w]){
                    // 保存最短路径的最后一条边
                    edgeTo[w] = v;
                    // 标记它，因为最短路径已知
                    marked[w] = true;
                    // 并将它添加到队列中
                    queue.enqueue(w);
                }
            }
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In(new File(BaseSearch.class.getResource("").getPath()
                + File.separator + "dfs-bfs.txt")));
        StdOut.println("-----------graph---------------");
        StdOut.println(graph);
        StdOut.println("-----------path---------------");
        int s = 1;
        BasePaths bfs = new BreadthFirstPaths(graph, s);

        for (int v = 0; v < graph.V(); v ++){
            if (bfs.hasPathTo(v)){
                StdOut.printf("%d to %d: ", s, v);
                for (int x : bfs.pathTo(v)){
                    if (x == s){
                        StdOut.print(x);
                    } else {
                        StdOut.print("-" + x);
                    }
                }
                StdOut.println();
            } else {
                StdOut.printf("%d to %d (-):  not connected\n", s, v);
            }
        }

    }
}
