package com.epam.courses.jf.practice.vplaksin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask12;

import java.util.Collections;
import java.util.List;

/**
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
        loop:
        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i) >= value) {
                for (int j = integers.size() - 1; j > i; j--) {
                    if (integers.get(j) < value) {
                        Collections.swap(integers, i, j);
                        continue loop;
                    }
                }

                if (integers.get(i) > value) {
                    for (int j = integers.size() - 1; j > i; j--) {
                        if (integers.get(j) == value) {
                            Collections.swap(integers, i, j);
                            continue loop;
                        }
                    }
                }
            }
        }

        return integers;
    }

}

