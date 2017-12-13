package com.leong.chapter05_Strings;

import edu.princeton.cs.algs4.StdOut;

import static edu.princeton.cs.algs4.StdOut.println;

/**
 * @author leongfeng created on 2017/12/9.
 */
public class Alphabet {
    /**
     *  The binary alphabet { 0, 1 }.
     */
    public static final Alphabet BINARY = new Alphabet("01");

    /**
     *  The octal alphabet { 0, 1, 2, 3, 4, 5, 6, 7 }.
     */
    public static final Alphabet OCTAL = new Alphabet("01234567");

    /**
     *  The decimal alphabet { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }.
     */
    public static final Alphabet DECIMAL = new Alphabet("0123456789");

    /**
     *  The hexadecimal alphabet { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F }.
     */
    public static final Alphabet HEXADECIMAL = new Alphabet("0123456789ABCDEF");

    /**
     *  The DNA alphabet { A, C, T, G }.
     */
    public static final Alphabet DNA = new Alphabet("ACGT");

    /**
     *  The lowercase alphabet { a, b, c, ..., z }.
     */
    public static final Alphabet LOWERCASE = new Alphabet("abcdefghijklmnopqrstuvwxyz");

    /**
     *  The uppercase alphabet { A, B, C, ..., Z }.
     */

    public static final Alphabet UPPERCASE = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    /**
     *  The protein alphabet { A, C, D, E, F, G, H, I, K, L, M, N, P, Q, R, S, T, V, W, Y }.
     */
    public static final Alphabet PROTEIN = new Alphabet("ACDEFGHIKLMNPQRSTVWY");

    /**
     *  The base-64 alphabet (64 characters).
     */
    public static final Alphabet BASE64 = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");

    /**
     *  The ASCII alphabet (0-127).
     */
    public static final Alphabet ASCII = new Alphabet(128);

    /**
     *  The extended ASCII alphabet (0-255).
     */
    public static final Alphabet EXTENDED_ASCII = new Alphabet(256);

    /**
     *  The Unicode 16 alphabet (0-65,535).
     */
    public static final Alphabet UNICODE16      = new Alphabet(65536);

    /**
     * 字符表.
     */
    private char[] alphabet;

    /**
     * 字符索引数据.
     * 用字符作为索引来获取与之相关联的信息.
     */
    private int[] inverse;
    /**
     * 进制数.
     */
    private int R;
    /**
     * 根据 s 中的字符创建一张新的字母表.
     *
     * @param s 字符串
     */
    public Alphabet(final String s) {
        boolean[] unicode = new boolean[Character.MAX_VALUE];
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            if (unicode[c]){
                throw new RuntimeException("Illegal alphabet: character = '" + c + "'" );
            }
            unicode[c] = true;
        }

        R = s.length();
        alphabet = s.toCharArray();
        inverse = new int[Character.MAX_VALUE];
        for (int i = 0; i < inverse.length; i++){
            inverse[i] = -1;
        }
        // can't use char since R can be as big as 65536
        for (int c = 0; c < R; c++){
            inverse[alphabet[c]] = c;
        }
    }

    /**
     * Constructor.
     * @param R 基数
     */
    private Alphabet(final int R){
        alphabet = new char[R];
        inverse = new int[R];
        this.R = R;
        for (int i = 0; i < R; i++){
            alphabet[i] = (char) i;
        }
        for (int i = 0; i < R; i++){
            inverse[i] = i;
        }
    }

    /**
     * default R;
     */
    private static final int DEFAULT_R = 256;
    /**
     * 默认构造器
     */
    public Alphabet(){
        this(DEFAULT_R);
    }

    /**
     * 获取字母表中索引位置的字符.
     *
     * @param index 索引
     * @return char
     */
    public char toChar(final int index) {
        return alphabet[index];
    }

    /**
     * 字符c 在字母表中么？
     *
     * @param c 字符
     * @return boolean
     */
    public boolean contains(final char c) {
        return inverse[c] != -1;
    }

    /**
     * 基数（字母表中的字符数量）.
     *
     * @return int
     */
    public int R() {
        return R;
    }

    /**
     * 表示一个索引所需的比特数.
     *
     * @return int
     */
    public int lgR() {
        int lgR = 0;
        for (int t = R; t > 1; t /=2){
            lgR++;
        }
        return lgR;
    }

    /**
     *返回字符 c 的索引.
     * @param c char
     * @return int
     */
    public int toIndex(final char c) {
        if (c < 0 || c >= inverse.length || inverse[c] == -1) {
            throw new RuntimeException("Character " + c + " not in alphabet");
        }
        return inverse[c];
    }
    /**
     * 将字符串s 转换为 R 进制的整数.
     *
     * @param s 字符串
     * @return 整数数组
     */
    public int[] toIndices(final String s) {
        char[] source = s.toCharArray();
        int[] target = new int[source.length];
        for (int i =0, len = source.length; i < len; i++){
            target[i] = toIndex(source[i]);
        }
        return target;
    }

    /**
     * 将R进制的整数转换为基于该字母表的字符串.
     *
     * @param indices 整数
     * @return 字符串
     */
    public String toChars(final int[] indices) {
        StringBuilder s = new StringBuilder(indices.length);
        for (int i = 0, len = indices.length; i < len; i++){
            s.append(toChar(indices[i]));
        }
        return s.toString();
    }

    public static void main(String[] args) {
        /*int[] encode1 = Alphabet.BASE64.toIndices("NowIsTheTimeForAllGoodMen");
        String decode1 = Alphabet.BASE64.toChars(encode1);
        println(decode1);

        int[] encoded2  = Alphabet.DNA.toIndices("AACGAACGGTTTACCCCG");
        String decoded2 = Alphabet.DNA.toChars(encoded2);
        println(decoded2);

        int[] encoded3 = Alphabet.DECIMAL.toIndices("01234567890123456789");
        String decoded3 = Alphabet.DECIMAL.toChars(encoded3);
        println(decoded3);*/

        int[] encode4 = Alphabet.UNICODE16.toIndices("0123456789");
        String decode4 = Alphabet.UNICODE16.toChars(encode4);
        println(decode4);
    }
}
