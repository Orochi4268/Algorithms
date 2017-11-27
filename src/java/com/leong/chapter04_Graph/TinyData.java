package com.leong.chapter04_Graph;

import edu.princeton.cs.algs4.In;

import java.io.File;

/**
 * @author leongfeng created on 2017/11/26.
 */
public class TinyData {

    private static final String BASE_PATH = BaseSearch.class.getResource("").getPath() + File.separator;

    public static In tinyG(){
        return new In(new File(BASE_PATH + "tinyG.txt"));
    }

    public static In tinyGG(){
        return new In(new File(BASE_PATH + "tinyGG.txt"));
    }
    public static In routes(){
        return new In(new File(BASE_PATH + "routes.txt"));
    }
}
