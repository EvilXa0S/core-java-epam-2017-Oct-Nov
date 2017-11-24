package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask8;

import java.util.Stack;

public class Task8 implements ITestableTask8 {
    @Override
    public boolean isNormalBrackets(String string) {
        Stack<Character> stack=new Stack<>();
        for(int i=0; i<string.length();i++){
            switch (string.charAt(i)){
                case '{':
                case '(':
                case '[':
                    stack.push(string.charAt(i));
                    break;
                case '}':{
                    if(!stack.isEmpty() && stack.peek()=='{') {
                        stack.pop();
                        break;
                    }
                    else return false;
                }
                case ')': {
                    if (!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                        break;
                    }
                    else return false;
                }

                case ']': {
                    if (!stack.isEmpty()&& stack.peek() == '[') {
                        stack.pop();
                        break;
                    }
                    else return false;
                }
            }
        }
        if(stack.isEmpty())
            return true;
        else
        return false;
    }

    public static void main(String[] args) {
        Task8 d=new Task8();
        System.out.println(d.isNormalBrackets("{([])}"));
        System.out.println(d.isNormalBrackets("{([])})"));
        System.out.println(d.isNormalBrackets("{([)}"));
        System.out.println(d.isNormalBrackets("{([((()))])}"));

    }
}
