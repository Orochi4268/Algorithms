package com.leong.chapter02_Sorting.section24_PriorityQueue.interview;

/**
 * @author leongfeng created on 2017/11/11.
 */
public class dijkstras {

    public static void main(String[] args) {


    }

    void dijkstras() {
        int n = 10;
        //初始化路径，都为最大值。
        int[][] path = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++){
                path[i][j] = Integer.MAX_VALUE;
            }
        }
        //这里需要输入path[i][j]的具体内容，如果有重复数据的话，需要更新路径为最小值。
        int minLen[] = new int[n + 1];
        //visit初始为0，防止回溯
        int visit[] = new int[n + 1];
        //初始化1到其他点的距离。
        for (int i = 1; i < n + 1; i++) {
            minLen[i] = path[1][i];
        }

        minLen[1] = 0;
        visit[1] = 1;
        int minj = 1;
        for (int i = 1; i < n + 1; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j < n + 1; j++) {
                if (visit[j] == 0 && minLen[j] < min) {
                    min = minLen[j];
                    minj = j;
                }
            }
            visit[minj] = 1;
            for (int j = 1; j < n + 1; j++) {
                if (visit[j] == 0 && minLen[minj] != Integer.MAX_VALUE && path[minj][j] !=
                        Integer.MAX_VALUE && minLen[j] > (minLen[minj] + path[minj][j])) {
                    minLen[j] = minLen[minj] + path[minj][j];
                }
            }
        }
    }
}
