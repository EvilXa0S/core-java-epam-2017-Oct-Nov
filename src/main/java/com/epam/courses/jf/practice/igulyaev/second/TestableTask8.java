package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask8;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class TestableTask8 implements ITestableTask8 {
    @Override
    public boolean isNormalBrackets(String string) {
        Deque<Character> stack = new ArrayDeque<>();
        Matcher matcher = Pattern.compile("([\\[{(])|([\\]})])").matcher(string);
        while (matcher.find()){
            Stream.of(matcher.group(1))
                    .filter(Objects::nonNull)
                    .forEach(s -> stack.push(s.charAt(0)));
            String second = matcher.group(2);
            if(second != null && (stack.isEmpty() || (!stack.isEmpty() && stack.pop() != pairBracket(second.charAt(0))))){
                return false;
            }
        }
        return stack.isEmpty();
    }
    private char pairBracket(char ch){
        switch (ch){
            case '}':
                return '{';
            case ')':
                return '(';
            case ']':
                return '[';
        }
        return 0;
    }
}
