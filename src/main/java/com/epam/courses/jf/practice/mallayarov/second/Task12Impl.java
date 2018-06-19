package com.epam.courses.jf.practice.mallayarov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask12;

import java.util.List;

/**
 * Задан список целых чисел и число X.
 * Не используя вспомогательных объектов и не изменяя размера списка,
 *    переставить элементы списка так, чтобы сначала шли числа, не превосходящие X, а затем числа, большие X.
 */
public class Task12Impl implements ITestableTask12 {

    /**
     * Преобразует целочисленный список таким образом, чтобы сначала шли числа меньшие value, затем большие.
     * @param integers Целочисленный список.
     * @param value Разделительное значение.
     * @return Преобразованный список.
     */
    @Override
    public List<Integer> transform(List<Integer> integers, int value) {
        // we can use 3-quick-sort solution
        int low = 0;
        int high = integers.size() - 1;
        int i = 0;

        while (i <= high) {
            int cmp = integers.get(i) - value;

            if (cmp < 0) {
                swap(integers, low++, i++);
            } else {
                if (cmp > 0) {
                    swap(integers, i, high--);
                } else {
                    ++i;
                }
            }
        }
        return integers;
    }

    private void swap(List<Integer> list, int first, int second) {
        int temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    }
}
