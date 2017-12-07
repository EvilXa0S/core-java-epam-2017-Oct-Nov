package com.epam.courses.jf.practice.akunats.second;

import com.epam.courses.jf.practice.common.second.ITestableTask8;

import java.util.Arrays;
import java.util.Stack;

public class Task8 implements ITestableTask8 {
    //  1 не подходит, требуется следующая итерация
    // 0 подходит
    private int control(String a, String b) {
        int result = 1;
        for (int i = 0; i < a.length(); i++) {
            char previousElement = a.toCharArray()[i];
            char nowStr = b.toCharArray()[a.length() - 1 - i];
            if ((nowStr == '(' && previousElement == ')') ||
                    (nowStr == '[' && previousElement == ']') ||
                    (nowStr == '{' && previousElement == '}')) {
                result = 0;
            }
        }
        return result;
    }

    public boolean isNormalBrackets(String string) {
        Stack<String> stackSymbols = new Stack<>();
        // убираем все элементы кроме скобок и записываемв стек
        Arrays.stream(string.replaceAll("[^\\[\\]\\(\\)\\{\\}]+", "").split(""))
                .forEach(stackSymbols::push);
        String previousElement = "";
        String nowStr = "";
        int i = 0;
        while (stackSymbols.size() > 0) {
            if (i == 0) {
                String popPrevious = stackSymbols.pop();
                previousElement = previousElement.concat(popPrevious);
                i++;
            } else if (i == 1) {
                String popNext = stackSymbols.pop();
                nowStr = nowStr.concat(popNext);
                if (popNext.equals("(") || popNext.equals("{") || popNext.equals("[")){
                    if (control(previousElement, nowStr) == 0){
                            previousElement = "";
                            nowStr = "";
                            i = 0;
                    } else {
                        nowStr = nowStr.replaceAll("([)][(])|([}][{])|([\\]][\\[])", "");
                    }
                }
            }
        }
        return nowStr.length() == 0 && previousElement.length() == 0;
    }
}
