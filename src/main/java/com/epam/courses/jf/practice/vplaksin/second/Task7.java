package com.epam.courses.jf.practice.vplaksin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask7;

import java.util.ArrayList;
import java.util.List;

/**
 * Умножить два многочлена заданной степени, если коэффициенты многочленов хранятся в различных списках.
 * Элемент списка с индексом i соответствует коэффициенту i-й степени.
 * Степень результирующего многочлена равна сумме максимальных ненулевых степеней исходных многочленов.
 * Случай, когда многочлен вырождается в 0 описывается состоянием [0].
 */
public class Task7 implements ITestableTask7 {

    /**
     * Осуществляет перемножение двух многочленов.
     *
     * @param first  Первый многочлен.
     * @param second Второй многочлен.
     * @return Многочлен, полученный в результате перемножения.
     */
    @Override
    public List<Integer> multiplyPolynomials(List<Integer> first, List<Integer> second) {
        List<Integer> result = new ArrayList<>(first.size() + second.size() - 1);

        if (first.size() == 1 && first.get(0) == 0
                || second.size() == 1 && second.get(0) == 0) {
            result.add(0);
            return result;
        }

        for (int i = 0; i < first.size() + second.size() - 1; i++) {
            result.add(0);
        }

        for (int i = 0; i < first.size(); i++) {
            for (int j = 0; j < second.size(); j++) {
                result.set(i + j, result.get(i + j) + first.get(i) * second.get(j));
            }
        }

        return result;
    }

}
