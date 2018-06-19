package com.epam.courses.jf.practice.sskovalevskiy.second;

import com.epam.courses.jf.practice.common.second.ITestableTask8;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by asus on 26.10.2017.
 *
 * Задана строка, возможно содержащая символы '(', ')', '[', ']', '{', '}'.
 * Проверить правильность расстановки скобок.
 * При реализации использовать стек.
 */
public class ITestableTask8Impl implements ITestableTask8 {

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
        Deque<Character> deque = new ArrayDeque<>();

        for (Character character : string.toCharArray()) {
            if (character.equals('[') || character.equals('{') || character.equals('(')) {
                deque.push(character);
                continue;
            } else if (character.equals(')') && (deque.isEmpty() || deque.peek() != '(')) {
                return false;
            } else if (character.equals('}') && (deque.isEmpty() || deque.peek() != '{')) {
                return false;
            } else if (character.equals(']') && (deque.isEmpty() || deque.peek() != '[')) {
                return false;
            }
            if (character.equals(')') || character.equals('}') || character.equals(']')) {
                deque.poll();
            }
        }

        return deque.size() == 0;
    }
}
