package com.tts.chapter01.chapter1_3.exercise;

import com.tts.chapter01.chapter1_3.Stack;
import std.lib.StdIn;

import static std.lib.StdOut.print;

/**
 * 左括号补全，输入：1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )， 输出：( (1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
 * 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) ) =
 *
 * @author: mike
 * @since: 2017/3/29
 */
public class Q1_3_09 {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<String> vals = new Stack<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.equals("=")) break;
            if (item.equals("("))
                print(item);
            else if (item.equals("+") || item.equals("-") || item.equals("*") || item.equals("/") || item.equals("sqrt"))
                ops.push(item);
            else if (")".equals(item)) {
                String val = vals.pop();
                String op = ops.pop();
                if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/"))
                    val = String.format("( %s %s %s )", vals.pop(), op, val);
                else if (op.equals("sqrt"))
                    val = String.format("( %s, %s )", op, val);
                vals.push(val);
            } else
                vals.push(item);
        }
        print(vals.pop());
    }


}
