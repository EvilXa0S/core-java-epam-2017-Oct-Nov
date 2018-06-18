package com.epam.courses.jf.practice.bborzdov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask8;

import java.util.Stack;

/**
 * Created by bogdan on 17.11.17.
 */

/**
 * Задана строка, возможно содержащая символы '(', ')', '[', ']', '{', '}'.
 * Проверить правильность расстановки скобок.
 * При реализации использовать стек.
 */
public class Task8 implements ITestableTask8 {

    /**
     * Проверяет правильность расстановки скобок.
     * Правильная расстановка:
     *      1) Каждой открывающей скобке соответствует закрывающая того же типа.
     *      2) Нет пересечения областей, обрамленных скобками.
     * @param string Анализируемая строка.
     * @return true - скобки расставлены верно, иначе - false.
     */
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
