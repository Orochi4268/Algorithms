package com.leong.chapter01.section_1_1;

/**
 * Created by leong on 2017/9/28.
 */
public class BruteForceSearch {

    public static int rank(int key, int[] arr){
        for (int i = 0, length = arr.length; i < length; i++){
            if (arr[i] == key){
                return i;
            }
        }
        return -1;
    }
}
