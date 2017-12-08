package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask8;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Task8 implements ITestableTask8 {
    @Override
    public boolean isNormalBrackets(String string) {
        Deque<String> squareBracketStack = new ArrayDeque<>();
        Deque<String> parenthesisStack = new ArrayDeque<>();
        Deque<String> braceStack = new ArrayDeque<>();
        for (String bracket : string.split("")) {
            switch (bracket) {
                case "{":
                    braceStack.push(bracket);
                    break;
                case "}":
                    if (braceStack.contains("{")) {
                        braceStack.pop();
                    } else {
                        return false;
                    }
                    break;
                case "(":
                    parenthesisStack.push(bracket);
                    break;
                case ")":
                    if (parenthesisStack.contains("(")) {
                        parenthesisStack.pop();
                    } else {
                        return false;
                    }
                    break;
                case "[":
                    squareBracketStack.push(bracket);
                    break;
                case "]":
                    if (squareBracketStack.contains("[")) {
                        squareBracketStack.pop();
                    } else {
                        return false;
                    }
                    break;
            }

        }
        return braceStack.isEmpty() && squareBracketStack.isEmpty() && parenthesisStack.isEmpty();
    }
}
