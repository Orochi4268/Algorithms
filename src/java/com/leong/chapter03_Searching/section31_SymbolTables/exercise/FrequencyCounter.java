package com.leong.chapter03_Searching.section31_SymbolTables.exercise;

import com.leong.chapter03_Searching.section31_SymbolTables.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author leongfeng created on 2017-11-13.
 */
public class FrequencyCounter {
    public static void main(String[] args) {
        int minLen = Integer.parseInt(args[0]);
        ST<String, Integer> st = new ST<>();
        while (!StdIn.isEmpty()){
            String word = StdIn.readString();
            if (word.length() < minLen){
                continue;
            }

            if (!st.contains(word)){
                st.put(word, 1);
            } else {
                st.put(word, st.get(word) + 1);
            }
        }

        String max = "";
        st.put(max, 0);
        for (String word : st.keys()){
            if (st.get(word) > st.get(max)){
                max = word;
            }
        }
        StdOut.println(max + " " + st.get(max));
    }
}
