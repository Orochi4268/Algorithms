package com.leong.chapter04_Graph.section42_DirectedGraphs;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import java.util.Iterator;

/**
 * 有向图基于深度优先搜索的顶点排序.
 * 基本思想：DFS 正好只会访问每个顶点一次.
 * @author leongfeng created on 2017-11-29.
 */
public class DepthFirstOrder {

    private boolean[] marked;
    /**
     * 前序：在递归调用之前将顶点加入队列.
     * 就是dfs()的调用顺序.
     */
    private Queue<Integer> pre;
    /**
     * 后序：在递归调用之后将顶点加入队列.
     * 顶点遍历完成的顺序.
     */
    private Queue<Integer> post;
    /**
     * 逆后序：在递归调用之后将顶点压入栈.
     */
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G){
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++){
            if (!marked[v]){
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        pre.enqueue(v);
        marked[v] = true;
        for (int w : G.adj(v)){
            if (!marked[w]){
                dfs(G, w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public Iterable<Integer> reversePost(){
        return reversePost;
    }
}
