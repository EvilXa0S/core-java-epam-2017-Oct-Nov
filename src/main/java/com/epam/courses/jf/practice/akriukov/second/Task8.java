package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask8;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Task8 implements ITestableTask8 {
    @Override
    public boolean isNormalBrackets(String string) {

        List<Character> openBrackets = Arrays.asList('{', '[', '(');
        List<Character> closeBrackets = Arrays.asList('}', ']', ')');
        Stack<Character> stack = new Stack<>();

        for(int i=0; i < string.length(); i++) {
            char currentSymbol = string.charAt(i);
            if(openBrackets.contains(currentSymbol))
                stack.push(currentSymbol);
            else if(closeBrackets.contains(currentSymbol)) {
                if(stack.isEmpty())
                    return false;
                char lastElem = stack.pop();
                if(openBrackets.indexOf(lastElem) != closeBrackets.indexOf(currentSymbol))
                    return false;
            }
        }

        return stack.isEmpty();
    }
}
