package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask6;

import java.util.HashMap;
import java.util.Map;

/**
 * Сложить два многочлена, если коэффициенты многочленов хранятся в объекте HashMap.
 * Степень элемента многочлена – ключ, коэффициент элемента многочлена – значение.
 * Коэффициенты многочления, ключи для которых отсутствуют в карте, равны нулю.
 */

public class Task6 implements ITestableTask6{

    /**
     * Осуществляет сложение двух многочленов.
     * @param first Первый многочлен.
     * @param second Второй многочлен.
     * @return Многочлен, полученный в результате сложения.
     */

    @Override
    public HashMap<Integer, Integer> addPolynomials(HashMap<Integer, Integer> first, HashMap<Integer, Integer> second) {
        HashMap<Integer, Integer> sumSet = new HashMap<>(first);
        Integer value1, value2, sum;

        for (Integer key: second.keySet()) {
            value2 = second.get(key);
            if (first.containsKey(key)) {
                value1 = first.get(key);
                sum = value1 + value2;
                sumSet.put(key, sum);
            }
            else{
                sumSet.put(key, value2);
            }
        }

        return sumSet;
    }

}
