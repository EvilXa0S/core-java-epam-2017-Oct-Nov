package com.epam.courses.jf.practice.sskovalevskiy.second;

import com.epam.courses.jf.practice.common.second.ITestableTask7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by asus on 26.10.2017.
 *
 * Умножить два многочлена заданной степени, если коэффициенты многочленов хранятся в различных списках.
 * Элемент списка с индексом i соответствует коэффициенту i-й степени.

 */
public class ITestableTask7Impl implements ITestableTask7 {

    /**
     * Осуществляет перемножение двух многочленов.
     * @param first Первый многочлен.
     * @param second Второй многочлен.
     * @return Многочлен, полученный в результате перемножения.
     */
    @Override
    public List<Integer> multiplyPolynomials(List<Integer> first, List<Integer> second) {

//        решение #1
//        int[] ints = new int[first.size() + second.size()];
//
//        for (int i = 0; i < first.size(); i++) {
//            for (int j = 0; j < second.size(); j++) {
//                ints[i + j] = ints[i + j] + first.get(i) * second.get(j);
//            }
//        }
//
//        ArrayList<Integer> result = new ArrayList<>();
//        for (int i : ints) {
//            result.add(i);
//        }
//
//        return result;

//        решение #2
//        Integer[] integers = new Integer[first.size() + second.size()];
//        Arrays.setAll(integers, i -> 0);
//
//        for (int i = 0; i < first.size(); i++) {
//            for (int j = 0; j < second.size(); j++) {
//                integers[i + j] = integers[i + j] + first.get(i) * second.get(j);
//            }
//        }
//
//        return Arrays.asList(integers);

//        решение #3
        List<Integer> result = new ArrayList<>(Collections.nCopies(60, 0));
        for (int i = 0; i < first.size(); i++) {
            for (int j = 0; j < second.size(); j++) {
                result.set((i + j), result.get(i + j) + first.get(i) * second.get(j));
            }
        }
        return result;

//        решение #4
//        https://habrahabr.ru/post/113642/
//        Быстрое умножение многочленов при помощи преобразования Фурье — это просто
//        http://rain.ifmo.ru/cat/view.php/theory/math/fft-2004
    }
}
