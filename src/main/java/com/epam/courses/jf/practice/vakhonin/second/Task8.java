package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask8;

import java.util.*;

/**
 * Задана строка, возможно содержащая символы '(', ')', '[', ']', '{', '}'.
 * Проверить правильность расстановки скобок.
 * При реализации использовать стек.
 */

public class Task8 implements ITestableTask8{
    Map<Character, Character> bracketsMap = new HashMap<>();
    List<Character> bracketsList = new ArrayList<>();
    Map<Character, Character> closedBracketsMap = new HashMap<>();

    /**
     * Создание map с ключами - закрытыми скобками
     */


    public void createClosedBracketsMap() {
        closedBracketsMap.put(')','(');
        closedBracketsMap.put('}','{');
        closedBracketsMap.put(']','[');
    }

    /**
     * Создание map с ключами - скобками и знаечниями соответствующими скобками закрытия или открытия
     */

    public void createBracketsMap() {
        bracketsMap.put('{','}');
        bracketsMap.put('[',']');
        bracketsMap.put('(',')');
        bracketsMap.put('}','{');
        bracketsMap.put(']','[');
        bracketsMap.put(')','(');
    }

    /**
     * Выделение из строки списка скобок.
     * @param string строка символов
     */

    public void stringToBracketsArray(String string) {
        Set<Character> keySet = bracketsMap.keySet();

        for (Character ch: string.toCharArray()) {
            if (keySet.contains(ch)) {
                bracketsList.add(ch);
            }
        }
    }

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
        createBracketsMap();
        createClosedBracketsMap();
        stringToBracketsArray(string);
        Character temp;
        Stack<Character> stack = new Stack<>();
        stack.push('0');

        for (Character bracket: bracketsList) {
            if (closedBracketsMap.keySet().contains(bracket)) {
                temp = stack.peek();
                if (bracket.equals(bracketsMap.get(temp))) {
                    stack.pop();
                }
                else {
                    return false;
                }
            }
            else {
                stack.push(bracket);
            }
        }
        if (!stack.peek().equals('0')) {
            return false;
        }

        return true;
    }
}
