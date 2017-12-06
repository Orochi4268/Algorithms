package com.leong.chapter04_Graph.section44_ShortestPaths;

import com.leong.chapter04_Graph.TinyData;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

import static edu.princeton.cs.algs4.StdOut.printf;
import static edu.princeton.cs.algs4.StdOut.println;

/**
 * 优先级限制下的并行任务调度。
 * 在满足限制条件的前提下应该如何在若干相同的处理器上（数量不限）安排任务并在最短的时间内完成所有任务？
 * 如果默认只有单个处理器：将任务按照拓扑顺序排序，完成任务的总耗时就是所有任务所需的总时间。
 * 如果是N个处理器时，就需要一种线性时间的算法叫做“关键路径”，它和“无环加权有向图中的最长路径问题”是等价的。
 * 步骤：
 * 1. 创建一幅无环加权有向图，其中包含一个起点s和一个终点t，且每个任务都对应着两个顶点（一个起始顶点，一个结束顶点）；
 * 2. 对于每个任务都有一条从它的起始顶点指向结束顶点的边，边的权重为任务所需的时间；
 * 3. 对于每条优先级限制 v->w，添加一条从v的结束顶点指向 w 的起始顶点的权重为 0 的边；
 * 4. 我们还需要为每个任务添加一条从起点 s 指向该任务的起始顶点的权重为 0 的边以及一条从该任务的结束顶点到终点的权重为 0 的边；
 * 这样每个任务预计的开始时间即为从起点 s 到它的起始顶点的最长距离。
 *
 * 每个任务对应着三条边：从起点到起始顶点、从结束顶点到终点的权重为 0 的边，以一条从起始顶点到结束顶点的边
 * s ->(权重为 0)-> v -> w ->(权重为 0)-> t
 * @author leongfeng created on 2017/12/6.
 */
public class CPM {

    public static void main(String[] args) {
        In in = TinyData.fromFilename("jobsPC.txt");
        int N = 5;
        in.readInt();
        in.readLine();
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2 * N + 2);
        int s = 2 * N;
        int t = 2 * N + 1;
        for (int i = 0; i < N; i++){
            String[] a = in.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            G.addEdge(new DirectedEdge(i, i + N, duration));
            G.addEdge(new DirectedEdge(s, i, 0.0));
            G.addEdge(new DirectedEdge(i + N, i, 0.0));
            for (int j = 1; j < a.length; j++){
                int successor = Integer.parseInt(a[j]);
                G.addEdge(new DirectedEdge(i + N, successor, 0.0));
            }
        }

        AcyclicLP lp = new AcyclicLP(G, s);
        println("Start times:");
        for (int i = 0; i < N; i++){
            printf("%4d: %5.1f\n", i, lp.distTo(i));
        }
        printf("Finish time: %5.1f\n", lp.distTo(t));
    }
}
