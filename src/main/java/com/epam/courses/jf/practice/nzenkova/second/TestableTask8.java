package com.epam.courses.jf.practice.nzenkova.second;


import com.epam.courses.jf.practice.common.second.ITestableTask8;

import java.util.HashMap;
import java.util.Stack;


/**
 * Specify a string. The string can contain '(', ')', '[', ']', '{', '}'.
 * Check balanced brackets.
 * Use a stack.
 */
public class TestableTask8 implements ITestableTask8 {
    /**
     * Check balanced brackets.
     * Correct an arrangement:
     *                          1) Each opening parenthesis corresponds to a closing of the same type.
     *                          2) There is no intersection of bracketed areas.
     * @param string The string being analyzed.
     * @return true -- Brackets are balanced, false -- otherwise.
     */
    @Override
    public boolean isNormalBrackets(String string) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < string.length(); i++) {
            char current = string.charAt(i);

            if (map.keySet().contains(current)) {
                stack.push(current);
            } else if (map.values().contains(current)) {
                if (!stack.empty() && map.get(stack.peek()) == current) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.empty();
    }
}
