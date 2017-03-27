package com.tts.chapter01.chapter1_3.exercise;

import com.tts.chapter01.chapter1_3.Stack;

/**
 * @author: mike
 * @since: 2017/3/27
 */
public class Q1_3_04 {
    public static void main(String[] args) {
        System.out.println(parenthese("[()]{}{[()()]()}"));
        System.out.println(parenthese("[(])"));
    }

    public static boolean parenthese(String brackets) {
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
