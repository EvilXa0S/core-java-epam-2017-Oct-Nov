package com.epam.courses.jf.practice.nzenkova.second;


import com.epam.courses.jf.practice.common.second.ITestableTask8;

import java.util.HashMap;
import java.util.Stack;

public class TestableTask8 implements ITestableTask8 {
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
