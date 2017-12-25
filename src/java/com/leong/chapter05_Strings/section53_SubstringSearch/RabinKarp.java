package com.leong.chapter05_Strings.section53_SubstringSearch;

import java.math.BigInteger;
import java.util.Random;

/**
 * RabinKarp 指纹字符串查找算法.
 * @author leongfeng created on 2017-12-22.
 */
public class RabinKarp {
    private String pat;
    private long patHash;
    private int M;
    private int R = 256;
    private long Q;
    /**
     * R^(M-1)%Q
     */
    private long RM;


    public RabinKarp(String pat) {
        this.pat = pat;
        M = pat.length();
        Q = longRandomPrime();
        RM = 1;
        for (int i = 1; i <= M-1; i++) {
            RM = (R* RM) % Q;
        }
        patHash = hash(pat, M);
    }

    public boolean check(int i) {
        return true;
    }

    public int search(String txt) {
        int N = txt.length();
        if (N < M) {
            return N;
        }
        long txtHash = hash(txt, M);
        if (patHash == txtHash && check(0)) {
            return 0;
        }
        for (int i = M; i < N; i++) {
            txtHash = (txtHash + Q - RM*txt.charAt(i-M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if (patHash == txtHash) {
                if (check(i - M + 1)) {
                    return i - M + 1;
                }
            }
        }
        return N;
    }

    private static long longRandomPrime() {
        BigInteger prime = new BigInteger(31, new Random());
        return prime.longValue();
    }

    private long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = (R * h + key.charAt(j)) % Q;
        }
        return h;
    }
}
