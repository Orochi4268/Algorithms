package com.leong.chapter05_Strings.section53_SubstringSearch;

/**
 * Knuth-Morris-Pratt 子字符串查找算法。
 * 基本思想：当出现不匹配时，就能知晓一部分文本的内容（因为在匹配失败之前它们已经和模式相匹配）。
 * @author leongfeng created on 2017-12-22.
 */
public class KMP {
    private String pat;
    private int[][] dfa;

    /**
     * 由模式字符串构造DFA.
     * @param pat
     */
    public KMP(String pat) {
        this.pat = pat;
        int R = 256;
        int M = pat.length();
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;

        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X];
            }
            dfa[pat.charAt(j)][j] = j + 1;
            X = dfa[pat.charAt(j)][X];
        }

    }

    public int search(String txt) {
        int i, j, N = txt.length(), M = pat.length();
        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == M) {
            return i - M;
        }
        return N;
    }
}
