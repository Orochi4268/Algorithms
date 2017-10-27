package com.leong.chapter01_Fundamentals.chapter1_3.exercise;

import com.leong.chapter01_Fundamentals.section13_BagsQueuesStacks.Stack;

/**
 * 编写一个Stack的用例，[()]{}{[()()]()} 输出为 true,  [(]) 输出为 false
 * @author: mike
 * @since: 2017/3/27
 */
public class Q1_3_04 {
    public static void main(String[] args) {
        System.out.println(parentheses("[()]{}{[()()]()}"));
        System.out.println(parentheses("[(])"));
    }

    public static boolean parentheses(String brackets) {
        Stack<Character> bracketStack = new Stack<>();
        boolean compareResult = true;
        for (int i = 0; i < brackets.length(); i++) {
            char bracket = brackets.charAt(i);

            if (!bracketStack.isEmpty() && (bracket == ')' || bracket == ']' || bracket == '}')) {
                char prevBracket = bracketStack.pop();
                switch (bracket) {
                    case ')':
                        compareResult = prevBracket == '(';
                        break;
                    case ']':
                        compareResult = prevBracket == '[';
                        break;
                    default:
                        compareResult = prevBracket == '{';
                }
                if (!compareResult) break;
            } else {
                bracketStack.push(bracket);
            }
        }
        return compareResult;
    }
}
