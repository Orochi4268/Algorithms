package com.leong.chapter01.section_1_3;

import std.lib.StdIn;
import std.lib.StdOut;

/**
 * Dijkstra 的双栈算术表达式求值算法
 * @author: mike
 * @since: 2017/3/27
 */
public class Evaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (s.equals("=")) break;
            switch (s){
                case "(":break;
                case "+": ops.push(s);break;
                case "-": ops.push(s);break;
                case "*": ops.push(s);break;
                case "/": ops.push(s);break;
                case "sqrt": ops.push(s);break;
                case ")": {
                    String op = ops.pop();
                    double val = vals.pop();
                    switch (op){
                        case "+": val = vals.pop() + val;break;
                        case "-": val = vals.pop() - val;break;
                        case "*": val = vals.pop() * val;break;
                        case "/": val = vals.pop() / val;break;
                        case "sqrt": val = Math.sqrt(val);break;
                    }
                    vals.push(val);
                    break;
                }
                default: vals.push(Double.parseDouble(s));
            }
        }
        StdOut.println(vals.pop());
    }
}
