package com.epam.courses.jf.practice.sskovalevskiy.second;

import com.epam.courses.jf.practice.common.second.ITestableTask6;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus on 24.10.2017.
 *
 * Сложить два многочлена, если коэффициенты многочленов хранятся в объекте HashMap.
 * Степень элемента многочлена – ключ, коэффициент элемента многочлена – значение.
 * Коэффициенты многочления, ключи для которых отсутствуют в карте, равны нулю.
 */
public class ITestableTask6Impl implements ITestableTask6 {

    /**
     * Осуществляет сложение двух многочленов.
     * @param first Первый многочлен.
     * @param second Второй многочлен.
     * @return Многочлен, полученный в результате сложения.
     */
    @Override
    public HashMap<Integer, Integer> addPolynomials(HashMap<Integer, Integer> first, HashMap<Integer, Integer> second) {

        for (Map.Entry<Integer, Integer> entry : second.entrySet()) {
            first.computeIfPresent(entry.getKey(), (k, v) -> v + entry.getValue());
            first.computeIfAbsent(entry.getKey(), v -> entry.getValue());
        }

        return first;
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> first = new HashMap<>();
        first.put(6, 2);
        first.put(5, 23);
        first.put(4, 12);
        first.put(2, 71);

        HashMap<Integer, Integer> second = new HashMap<>();
        second.put(4, 2);
        second.put(3, 23);
        second.put(2, 12);
        second.put(1, 71);

        HashMap<Integer, Integer> result = new ITestableTask6Impl().addPolynomials(first, second);

        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            System.out.println("Степень =  " + entry.getKey() + " Коэффициент = " + entry.getValue());
        }

    }
}
