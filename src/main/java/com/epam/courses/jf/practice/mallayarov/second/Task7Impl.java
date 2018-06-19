package com.epam.courses.jf.practice.mallayarov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask7;

import java.util.ArrayList;
import java.util.List;

/**
 * Умножить два многочлена заданной степени, если коэффициенты многочленов хранятся в различных списках.
 * Элемент списка с индексом i соответствует коэффициенту i-й степени.
 */
public class Task7Impl implements ITestableTask7 {

    /**
     * Осуществляет перемножение двух многочленов.
     * @param first Первый многочлен.
     * @param second Второй многочлен.
     * @return Многочлен, полученный в результате перемножения.
     */
    @Override
    public List<Integer> multiplyPolynomials(List<Integer> first, List<Integer> second) {
        List<Integer> result = new ArrayList<>(first.size() + second.size());

        // 2^3 * 2^6 = 2^(2+6)
        for (int i = 0; i < first.size() + second.size(); ++i) {
            result.add(i, 0);
        }

        for (int i = 0; i < first.size(); ++i) {
            for (int j = 0; j < second.size(); ++j) {
                // (ax + bx^2) * (cx^3) = (a + c)x^4 + (b + c)x^5
                result.set(i + j, result.get(i + j) + first.get(i) * second.get(j));
            }
        }

        return result;
    }
}
