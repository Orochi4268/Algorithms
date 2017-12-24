package com.leong.chapter05_Strings.section53_SubStringSearch;

/**
 * 暴力子字符查找算法.
 * 时间复杂度（O(NM)）
 * @author leongfeng created on 2017/12/14.
 */
public class BruteSearch {

    /**
     * @param pat 需要匹配的子字符串
     * @param txt 目标字符串
     * @return 如果没有找到返回 txt 的 length
     */
    public static int search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        for (int i = 0; i <= N - M; i++) {
            int j = 0;
            for (; j < M; j++) {
                int p = pat.charAt(j);
                int t = txt.charAt(i + j);
                // 不匹配跳出j，继续移动 i
                if (p != t) {
                    break;
                }
            }
            // 找到匹配的，即 pat 都遍历成功，返回txt的位置
            if (j == M) {
                return i;
            }

        }
        return N;
    }

    /** 显式回退.
     *
     此处 i 相当于上述方法的 i + j
     0 1 2 3 4 5
     A A A A B C
     A A B      (i = 0, j 遍历到 2 时不等, 不匹配回退 i(2-2) -> 0)
       A A B    (i(++之后) = 1, j 再次遍历到 2, 回退i(3-2) -> 1)
     * @param pat
     * @param txt
     * @return
     */
    public static int search2(String pat, String txt) {
        int i, j, M = pat.length(), N = txt.length();
        for (i = 0, j = 0; i < N && j < M; i++) {

            if (txt.charAt(i) == pat.charAt(j)) {
                j++;
            } else {
                i -= j;
                j = 0;
            }
        }
        // 匹配
        if (j == M) {
            return i - M;
        }
        // 不匹配
        return N;
    }
}
