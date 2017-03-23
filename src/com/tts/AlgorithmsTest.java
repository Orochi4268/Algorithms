package com.tts;

import org.junit.Test;

import java.util.List;

/**
 * @author: mike
 * @since: 2017/3/6
 */
public class AlgorithmsTest {

    @Test
    public void test1(){
        System.out.println(gcd(8,4));
    }

    public static int gcd(int p, int q){
        if(q==0) return p;
        int r = p % q;
        return gcd(q, r);
    }
}
