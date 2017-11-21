package com.epam.courses.jf.practice.SigarevVL.second;


import com.epam.courses.jf.practice.common.second.ITestableTask12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Интерфейс для юнит-тестирования задания №12.
 * <p>
 * Задан список целых чисел и число X.
 * Не используя вспомогательных объектов и не изменяя размера списка,
 * переставить элементы списка так, чтобы сначала шли числа, не превосходящие X, а затем числа, большие X.
 */

public class Task12 implements ITestableTask12 {

    /**
     * Преобразует целочисленный список таким образом, чтобы сначала шли числа меньшие value, затем большие.
     *
     * @param integers Целочисленный список.
     * @param value    Разделительное значение.
     * @return Преобразованный список.
     */

    @Override
    public List<Integer> transform(List<Integer> integers, int value) {
        Collections.sort(integers);
        return integers;
    }
}
