package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.ITestableTask8;

import java.util.Stack;

public class Task8 implements ITestableTask8 {
    @Override
    public boolean isNormalBrackets(String string) {

        Stack<Character> stack = new Stack<>();
        Character temp;
        for (Character ch : string.toCharArray()){

            if (ch.equals('{') || ch.equals('(') || ch.equals('[')){
                stack.push(ch);
            }
            else if (ch.equals('}') || ch.equals(')') || ch.equals(']')){

                if (stack.empty()){
                    return false;
                }

                temp = stack.pop();
                if (!temp.equals('{') && ch.equals('}') ||
                        !temp.equals('(') && ch.equals(')') ||
                        !temp.equals('[') && ch.equals(']')){
                    return false;
                }
            }
        }

        return stack.empty();
    }
}
