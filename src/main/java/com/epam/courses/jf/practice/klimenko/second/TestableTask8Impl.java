package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.ITestableTask8;

import java.util.Stack;

public class TestableTask8Impl implements ITestableTask8 {
    private static boolean isOpening(Character c) {
        return c == '(' || c == '[' || c == '{';
    }

    private static boolean isClosing(Character c) {
        return c == ')' || c == ']' || c == '}';
    }

    private static Character flip(Character c) {
        switch (c) {
            case '(':
                return ')';
            case ')':
                return '(';
            case '[':
                return ']';
            case ']':
                return '[';
            case '{':
                return '}';
            case '}':
                return '{';
        }
        return '\0';
    }

    @Override
    public boolean isNormalBrackets(String string) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < string.length(); ++i) {
            Character c = string.charAt(i);
            if (isOpening(c)) {
                stack.add(c);
            }
            if (isClosing(c)) {
                if (stack.empty() || stack.lastElement() != flip(c)) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.empty();
    }
}
