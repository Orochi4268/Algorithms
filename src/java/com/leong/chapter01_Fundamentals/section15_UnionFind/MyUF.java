package com.leong.chapter01_Fundamentals.section15_UnionFind;

/**
 * Created by leong on 2017/10/15.
 */
public class MyUF {
    private int[] id; //分量id
    private int count; // 分量数量

    public MyUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = N;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (pID == qID){
            return;
        }

        for (int i = 0, len = id.length; i < len; i++){
            if ( pID == id[i]){
                id[i] = qID;
            }
        }
        count --;
    }
}
