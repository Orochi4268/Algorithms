package com.leong.chapter05_Strings;

import edu.princeton.cs.algs4.In;

import java.io.File;

/**
 * @author leongfeng created on 2017/12/10.
 */
public class StringData {

    public static final String BASE_PATH = StringData.class.getResource("").getPath();
    public static In fromFilename(final String filename){
        return new In(new File(BASE_PATH + filename));
    }
}
