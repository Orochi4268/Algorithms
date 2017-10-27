package com.leong.chapter01_Fundamentals.section15_UnionFind;

/**
 * 加权 quick-union 算法：记录下每一根树的大小并问题将较小的树连接到较大的树上。
 * O(logN)
 * Created by leong on 2017/10/15.
 */
public class MyWeightedQuickUnionUF extends UF {

    private int[] sz; // 记录树的大小：（由触点索引的）各个根节点所对应的分量的大小

    public MyWeightedQuickUnionUF(int N) {
        super(N);
        sz = new int[N];
        for (int i = 0; i < N; i ++){
            sz[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot){
            return;
        }

        // 将小树的根节点连接到大树的根节点
        if (sz[pRoot] < sz[qRoot]){
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        count --;
    }
}
