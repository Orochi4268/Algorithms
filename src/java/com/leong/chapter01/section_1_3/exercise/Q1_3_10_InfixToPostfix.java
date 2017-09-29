package com.leong.chapter01.section_1_3.exercise;

import com.leong.chapter01.section_1_3.Stack;

import static std.lib.StdIn.isEmpty;
import static std.lib.StdIn.readString;
import static std.lib.StdOut.print;
import static std.lib.StdOut.println;

/**
 * 将算术表达式由中序表达式转为后序表达式。
 * 中序表达式：2*3/(2-1)+3*(4-1)
 * 前序表达式：+/*23-21*3-41
 * 后序表达式：23*21-/341-*+
 * 测试：2 * 3 / ( 2 - 1 ) + 3 * ( 4 - 1 ) =
 *
 * 中序转后序：
 * 1、当输入的是操作数时候，直接输出
 * 2、当输入开括号时候，把它压栈
 * 3、当输入的是闭括号时候，先判断栈是否为空，若为空，则发生错误并进行相关处理；
 *      若非空，把栈中元素依次出栈输出，直到遇到第一个开括号，若没有遇到开括号，也发生错误，进行相关处理
 * 4、当输入是运算符op（+、- 、×、/）时候
 *   a)循环，当（栈非空and栈顶不是开括号and栈顶运算符的优先级不低于输入的运算符的优先级）时，反复操作：将栈顶元素出栈输出
 *   b)把输入的运算符op压栈
 * 5、当中序表达式的符号序列全部读入后，若栈内仍有元素，把他们依次出栈输出。若弹出的元素遇到空括号，则说明不匹配，发生错误，并进行相关处理
 *
 * http://www.jianshu.com/p/a052eb2806a1
 * https://zh.wikipedia.org/wiki/%E9%80%86%E6%B3%A2%E5%85%B0%E8%A1%A8%E7%A4%BA%E6%B3%95
 * http://blog.csdn.net/dengjianqiang2011/article/details/8728202
 * //todo 还要完善
 * @author: mike
 * @since: 2017/3/29
 */
public class Q1_3_10_InfixToPostfix {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        while (!isEmpty()){
            String str = readString();
            if ("=".equals(str)){
                break;
            }
            if ("(".equals(str)){
                ops.push(str);
            } else if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")){
                boolean isEmpty = ops.isEmpty(), isLeftParen = false, isLowerOperator = false;
                if ( !isEmpty ){
                    String itemTop = ops.peek();
                    isLeftParen = ops.peek().equals("(");
                    if ((itemTop.equals("+") || itemTop.equals("-")) && (str.equals("*") || str.equals("/"))) {
                        isLowerOperator = true;
                    }
                }
                if (!(isEmpty || isLeftParen || isLowerOperator)) {
                    for (int i = 0; i < ops.size(); i++) {
                        print(ops.pop());
                    }
                }
                ops.push(str);
            } else if(str.equals(")")){
                String item = ops.pop();
                while (!item.equals("(")){
                    print(item);
                    item = ops.pop();
                }
            } else {
                print(str);
            }

        }
        for (String str : ops) {
            print(str);
        }
        println();
    }

}
