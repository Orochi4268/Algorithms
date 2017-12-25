package com.leong.chapter05_Strings.section53_SubstringSearch;

/**
 *  Boyer-Moore 字符串查找算法.
 * @author leongfeng created on 2017-12-22.
 */
public class BoyerMoore {
    private int[] right;
    private String pat;

    public BoyerMoore(String pat) {
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        right = new int[R];
        // 不包含在模式的字符串中的字符的值为 -1
        for (int c = 0; c < R; c++) {
            right[c] = -1;
        }
        // pat 字符串中的字符的值为它在其中出现的最右位置
        for (int j = 0; j < M; j++) {
            right[pat.charAt(j)] = j;
        }

    }

    public int search(String txt) {
        int N = txt.length();
        int M = pat.length();
        int skip;
        for (int i = 0; i <= N-M; i += skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(j)) {
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1) {
                        skip = 1;
                    }
                    break;
                }
            }
            if (skip == 0) {
                return i;
            }
        }
        return N;
    }


}
