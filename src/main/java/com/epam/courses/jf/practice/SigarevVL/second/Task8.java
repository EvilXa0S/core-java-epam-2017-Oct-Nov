package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask8;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * A string is specified and possibly contained characters
 * '(', ')', '[', ']', '{', '}'.
 * Check that the brackets are correctly aligned.
 * Need to use Stack.
 */
public class Task8 implements ITestableTask8 {

    /**
     * Verifies that the parentheses are correctly positioned.
     * <p>
     * Correct arrangement:
     * 1) Each opening brackets corresponds to a closing
     * of the same type.
     * 2) Brackets do not intersect.
     *
     * @param string The analyzed string.
     * @return true - all the correct, else - false.
     */
    @Override
    public boolean isNormalBrackets(String string) {

        char inputString[] = string.toCharArray();
        Deque<Character> brackets = new ArrayDeque<>();

        for (int i = 0; i < inputString.length; i++) {
            switch (inputString[i]) {
                case '(':
                    brackets.push(inputString[i]);
                    break;
                case ')':
                    if (!brackets.isEmpty() && brackets.peek().equals('(')) {
                        brackets.pop();
                    } else {
                        return false;
                    }
                    break;
                case '[':
                    brackets.push(inputString[i]);
                    break;
                case ']':
                    if (!brackets.isEmpty() && brackets.peek().equals('[')) {
                        brackets.pop();
                    } else {
                        return false;
                    }
                    break;
                case '{':
                    brackets.push(inputString[i]);
                    break;
                case '}':
                    if (!brackets.isEmpty() && brackets.peek().equals('{')) {
                        brackets.pop();
                    } else {
                        return false;
                    }
                    break;
            }
        }
        return brackets.isEmpty();
    }
}
