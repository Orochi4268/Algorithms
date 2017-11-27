package com.leong.chapter04_Graph.section41_UndirectedGraphs;

import com.leong.chapter03_Searching.section31_SymbolTables.ST;
import edu.princeton.cs.algs4.In;

/**
 * 符号图.
 * @author leongfeng created on 2017/11/26.
 */
public class SymbolGraph {
    /**
     * 符号名 -> 索引
     */
    ST<String, Integer> st;
    /**
     * 反向索引 -> 符号名.
     */
    String[] keys;
    Graph G;
    public SymbolGraph(String filename, String delim){
        st = new ST<>();
        In in = new In(filename);
        while (in.hasNextLine()){
            String[] a= in.readLine().split(delim);
            for (int i = 0; i < a.length; i++){
                if (!st.contains(a[i])){
                    st.put(a[i], st.size());
                }
            }
        }
        keys = new String[st.size()];
        for (String name : st.keys()){
            keys[st.get(name)] = name;
        }
        in = new In(filename);
        G = new Graph(in);
        while (in.hasNextLine()){
            String[] a = in.readLine().split(delim);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++){
                G.addEdge(v, st.get(a[i]));
            }
        }
    }

    public boolean contains(String key){

        return false;
    }

    public int index(String key){
        return 0;
    }

    public String name(int v){
        return "";
    }

    public Graph G(){
        return null;
    }
}
