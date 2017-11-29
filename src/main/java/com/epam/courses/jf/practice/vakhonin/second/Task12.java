package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask12;

import java.util.List;

/**
 * Задан список целых чисел и число X.
 * Не используя вспомогательных объектов и не изменяя размера списка,
 *    переставить элементы списка так, чтобы сначала шли числа, не превосходящие X, а затем числа, большие X.
 */

public class Task12 implements ITestableTask12 {

    /**
     * Преобразует целочисленный список таким образом, чтобы сначала шли числа меньшие value, затем большие.
     * @param integers Целочисленный список.
     * @param value Разделительное значение.
     * @return Преобразованный список.
     */

    @Override
    public List<Integer> transform(List<Integer> integers, int value) {
        int k = 0;
        for (int j = integers.size() - 1; j > k; j--) {
            while ((integers.get(j) <= value) && (j > k)) {
                integers.set(j, integers.get(j) ^ integers.get(k));
                integers.set(k, integers.get(j) ^ integers.get(k));
                integers.set(j, integers.get(j) ^ integers.get(k));
                k++;
            }

            k--;
        }

        return integers;
    }
}
