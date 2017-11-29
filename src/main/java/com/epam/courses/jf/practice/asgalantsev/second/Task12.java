package com.epam.courses.jf.practice.asgalantsev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask12;

import java.util.Collections;
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
        if(integers.size() == 0)
            return integers;

        int counter = integers.size() - 1;
        int j = 0;

        for(int k=0; k < 2; k++)
            while (counter >= j){
                int elem = integers.get(j);
                if(elem > value) {
                    Collections.swap(integers, j, counter);
                    counter--;
                }
                j++;
            }

        for(int i=0; i < integers.size() - counter; i++) {
            int elem = integers.get(i);
            if(elem == value) {
                Collections.swap(integers, i, integers.size() - counter + 1);
                counter--;
            }
        }

        System.out.println(integers);
        return integers;
    }
}
