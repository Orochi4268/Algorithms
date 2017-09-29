package com.leong.chapter01.section_1_2;

import edu.princeton.cs.algs4.Count;

/**
 * 计数器
 * Created by leong on 2017/9/28.
 */
public class Counter {
    private final String id;
    private int count;
    public Counter(String id){
        this.id = id;
        this.count = 0;
    }

    public void increment(){
        this.count++;
    }

    public int tally(){
        return count;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "id='" + id + '\'' +
                ", count=" + count +
                '}';
    }
}
