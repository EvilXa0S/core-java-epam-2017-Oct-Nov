package com.epam.courses.jf.practice.mallayarov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask6;

import java.util.HashMap;

/**
 * Сложить два многочлена, если коэффициенты многочленов хранятся в объекте HashMap.
 * Степень элемента многочлена – ключ, коэффициент элемента многочлена – значение.
 * Коэффициенты многочления, ключи для которых отсутствуют в карте, равны нулю.
 */
public class Task6Impl implements ITestableTask6 {

    /**
     * Осуществляет сложение двух многочленов.
     * @param first Первый многочлен.
     * @param second Второй многочлен.
     * @return Многочлен, полученный в результате сложения.
     */
    @Override
    public HashMap<Integer, Integer> addPolynomials(HashMap<Integer, Integer> first, HashMap<Integer, Integer> second) {
        HashMap<Integer, Integer> result = new HashMap<>();
        HashMap<Integer, Integer> biggestPolynomial = first.size() >= second.size() ? first : second;
        HashMap<Integer, Integer> lowestPolynomial = first.size() < second.size() ? first : second;

        result.putAll(biggestPolynomial);
        for (Integer key : lowestPolynomial.keySet()) {
            int firstValue = first.getOrDefault(key, 0);
            int secondValue = second.getOrDefault(key, 0);

            result.putIfAbsent(key, 0);
            result.put(key, firstValue + secondValue);
        }

        return result;
    }
}
