package com.leong.chapter04_Graph;

import edu.princeton.cs.algs4.In;

import java.io.File;

/**
 * @author leongfeng created on 2017/11/26.
 */
public class TinyData {

    public static final String BASE_PATH = BaseSearch.class.getResource("").getPath();
    public static final String TINY_G_TXT = BASE_PATH + "tinyG.txt";
    public static final String TINY_GG_TXT = BASE_PATH + "tinyGG.txt";
    public static final String ROUTES_TXT = BASE_PATH + "routes.txt";
    public static final String MOVIES_TXT = BASE_PATH + "movies.txt";
    public static final String FILENAME_TINYDG = "tinyDG.txt";
    public static final String FILENAME_TINYEWG = "tinyEWG.txt";
    public static final String FILENAME_TINYEWD = "tinyEWD.txt";

    public static In tinyG(){
        return new In(new File(TINY_G_TXT));
    }

    public static In tinyGG(){
        return new In(new File(TINY_GG_TXT));
    }

    public static In routes(){
        return new In(new File(ROUTES_TXT));
    }

    public static In fromFilename(String filename){
        return new In(new File(BASE_PATH + filename));
    }
}
