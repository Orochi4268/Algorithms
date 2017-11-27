package com.leong.chapter04_Graph.section41_UndirectedGraphs;

import com.leong.chapter04_Graph.TinyData;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 符号图.
 * @author leongfeng created on 2017/11/26.
 */
public class SymbolGraph {
    /**
     * 符号名（key） -> 索引
     */
    ST<String, Integer> st;
    /**
     * 索引 -> 符号名(key).
     */
    String[] keys;
    Graph G;

    /**
     *
     * @param filename
     * @param delim
     */
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
        G = new Graph(st.size());
        while (in.hasNextLine()){
            String[] a = in.readLine().split(delim);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++){
                G.addEdge(v, st.get(a[i]));
            }
        }
    }

    public boolean contains(String s){
        return st.contains(s);
    }

    /**
     * key 索引.
     * @param key key
     * @return 索引
     */
    public int index(String key){
        return st.get(key);
    }

    /**
     * 索引v的符号名.
     * @param v 索引
     * @return 符号名
     */
    public String name(int v){
        return keys[v];
    }

    public Graph G(){
        return G;
    }

    public static void main(String[] args) {
//        SymbolGraph symbolGraph = new SymbolGraph(TinyData.MOVIES_TXT, "/");
        SymbolGraph symbolGraph = new SymbolGraph(TinyData.ROUTES_TXT, "\\s");
        int index = symbolGraph.index("DFW");
        StdOut.println(index);
    }
}
