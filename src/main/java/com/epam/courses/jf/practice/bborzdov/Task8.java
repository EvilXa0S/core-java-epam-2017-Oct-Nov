package com.epam.courses.jf.practice.bborzdov;

import com.epam.courses.jf.practice.common.second.ITestableTask8;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by bogdan on 17.11.17.
 */
public class Task8 implements ITestableTask8 {
    @Override
    public boolean isNormalBrackets(String string) {
        String str[] = string.split("");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals("(") || str[i].equals("[") || str[i].equals("{")) {
                stack.push(str[i]);
            } else if (str[i].equals(")") || str[i].equals("]") || str[i].equals("}")) {
                if (stack.size() == 0) {
                    return false;
                }
                if (stack.peek().equals("(") && str[i].equals(")") ||
                        stack.peek().equals("[") && str[i].equals("]") ||
                        stack.peek().equals("{") && str[i].equals("}")) {
                    stack.pop();
                }else{
                    return false;
                }
            }
        }
        return stack.size() <= 0;
    }
}
