package com.tts.chapter01.chapter1_3.exercise;

import com.tts.chapter01.chapter1_3.Stack;
import std.lib.StdIn;

import static std.lib.StdOut.print;

/**
 * 将算术表达式由中序表达式转为后序表达式。
 * 中序表达式：2*3/(2-1)+3*(4-1)
 * 前序表达式：+/*23-21*3-41
 * 后序表达式：23*21-/341-*+
 * 2 * 3 / ( 2 - 1 ) + 3 * ( 4 - 1 ) =
 * http://blog.csdn.net/zsuguangh/article/details/6280863
 * @author: mike
 * @since: 2017/3/29
 */
public class Q1_3_10 {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<String> vals = new Stack<>();
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (s.equals("=")) break;
            if(s.equals("(")) ops.push(s);
            else if(s.equals("+") || s.equals("-") || s.equals("*") ||
                    s.equals("/") || s.equals("sqrt"))
                ops.push(s);
            else if(s.equals(")")){
                String op = ops.pop();
                String v = vals.pop();

                if(op.equals("+") || op.equals("-") || op.equals("*") ||
                        op.equals("/"))
                    v = String.format("%s %s %s", vals.pop(), v, op);
                else if(op.equals("sqrt"))
                    v = String.format("%s %s", v, op);
                vals.push(v);
            }
            else{
                vals.push(s);
            }
        }
        StringBuilder expression = new StringBuilder();
        for (String val : vals)
            print(val);
    }

}
