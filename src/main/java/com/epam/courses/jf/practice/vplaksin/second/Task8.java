package com.epam.courses.jf.practice.vplaksin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask8;

import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Задана строка, возможно содержащая символы '(', ')', '[', ']', '{', '}'.
 * Проверить правильность расстановки скобок.
 * При реализации использовать стек.
 */
public class Task8 implements ITestableTask8 {

    /**
     * Проверяет правильность расстановки скобок.
     * Правильная расстановка:
     * 1) Каждой открывающей скобке соответствует закрывающая того же типа.
     * 2) Нет пересечения областей, обрамленных скобками.
     *
     * @param string Анализируемая строка.
     * @return true - скобки расставлены верно, иначе - false.
     */
    @Override
    public boolean isNormalBrackets(String string) {
        char[] chars = string.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (Character c : chars) {
            if (Pattern.matches("[({\\[]", c.toString())) {
                stack.push(c);
            } else if (Pattern.matches("[)}\\]]", c.toString())) {
                if (stack.empty()) {
                    return false;
                } else {
                    char checkChar = stack.pop();
                    if (!(checkChar == '{' && c == '}'
                            || checkChar == '(' && c == ')'
                            || checkChar == '[' && c == ']')) {
                        return false;
                    }
                }
            }
        }

        return stack.empty();
    }

}
