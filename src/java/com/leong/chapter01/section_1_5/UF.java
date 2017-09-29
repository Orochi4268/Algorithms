package com.leong.chapter01.section_1_5;

import std.lib.StdIn;
import std.lib.StdOut;

/**
 * @author: mike
 * @since: 2017/4/4
 */
public class UF {
    /**
     * 分量id（以触点作为索引）
     *
     */
    private int[] id;
    private int count;

    /**
     * 初始化（0到N-1）N个触点
     * @param N
     */
    public UF(int N){
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
        }
    }

    /**
     * 在p和q之间添加一条连接
     * @param p
     * @param q
     */
    public void union(int p, int q){
        int pID = find(p);
        int qID = find(q);
        if (pID == qID){
            return;
        }
        //将q的值赋给p
        for (int i=0; i < id.length; i++){
            if (id[i] == pID){
                id[i] = qID;
            }
        }
        count --;
    }

    /**
     * p（0到N-1）所在分量的标识符
     * @param p
     * @return
     */
    public int find(int p){
        return id[p];
    }

    /**
     * 如果p和q存在于同一个分量中则返回true
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    /**
     * 连通分量的数量
     * @return
     */
    public int count(){
        return count;
    }

    public static void main(String[] args) {

        int N = StdIn.readInt();
        UF uf = new UF(N);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)){
                continue;
            }
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
    }
}
