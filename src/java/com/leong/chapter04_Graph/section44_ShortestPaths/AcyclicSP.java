package com.leong.chapter04_Graph.section44_ShortestPaths;

import com.leong.chapter04_Graph.TinyData;
import com.leong.chapter04_Graph.section42_DirectedGraphs.Topological;

/**
 * 无环加权有向图的最短路径算法（O(E+V)）。
 * 1. 先将distTo[s]初始化为0，其它元素为无穷大；
 * 2. 然后一个一个地按照拓扑顺序放松所有的顶点。
 *  使用DFS 搜索 tinyEWDAG.txt 得到的图的顶点的拓扑顺序 5 1 3 6 4 7 0 2
 *
 * @author leongfeng created on 2017/12/4.
 */
public class AcyclicSP {

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(TinyData.fromFilename("tinyEWDAG.txt"));

    }
}
