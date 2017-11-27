package com.epam.courses.jf.practice.vkostin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask8;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Task8 implements ITestableTask8 {

    @Override
    public boolean isNormalBrackets(String string) {

        if (!string.matches(".*[\\[\\]].*")) {
            return true;
        }

        char[] brackets = string.toCharArray();
        // + use non blocking Queue impl
        Deque<Character> stack = new ArrayDeque<>();

        char lastLiteral = brackets[0];
        stack.push(lastLiteral);

        char currentLiteral;

        for (int i = 1; i < brackets.length; ++i) {
            currentLiteral = brackets[i];
            stack.push(currentLiteral);

            // (int) '[' = (int) ']' + 2 -- the same for "{}"
            // (int) '(' = (int) ')' + 1
            if ((((int) currentLiteral - 2) == lastLiteral)
                    || (((int) currentLiteral - 1) == lastLiteral)) {
                stack.pop();
                stack.pop();
                if (!stack.isEmpty()) {
                    lastLiteral = stack.peek();
                }
            } else {
                lastLiteral = currentLiteral;
            }
        }

        return stack.isEmpty();
    }
}