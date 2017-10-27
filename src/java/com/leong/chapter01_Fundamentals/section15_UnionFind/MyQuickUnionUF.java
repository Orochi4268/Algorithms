package com.leong.chapter01_Fundamentals.section15_UnionFind;

/**
 * Created by leong on 2017/10/15.
 */
public class MyQuickUnionUF extends UF {
    public MyQuickUnionUF(int N) {
        super(N);
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot){
            return;
        }
        id[pRoot] = qRoot;
        count --;
    }

    /**
     * 找出 p(id[id[id[p]...]]) 的根触点，根触点即链接指向自己的触点,与父类的思想不同
     * @param p
     * @return
     */
    @Override
    public int find(int p) {
        while (p != id[p]){
            p = id[p];
        }
        return p;
    }
}
