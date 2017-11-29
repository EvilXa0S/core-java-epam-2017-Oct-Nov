package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask7;

import java.util.ArrayList;
import java.util.List;

/**
 * Умножить два многочлена заданной степени, если коэффициенты многочленов хранятся в различных списках.
 * Элемент списка с индексом i соответствует коэффициенту i-й степени.
 */

public class Task7 implements ITestableTask7{

    /**
     * Осуществляет перемножение двух многочленов.
     * @param first Первый многочлен.
     * @param second Второй многочлен.
     * @return Многочлен, полученный в результате перемножения.
     */

    @Override
    public List<Integer> multiplyPolynomials(List<Integer> first, List<Integer> second){
        List<Integer> result = new ArrayList<>();
        int size1 = first.size();
        int size2 = second.size();
        int val1, val2;

        for (int j = 0; j < (size1 + size2 - 1); j++) {
            result.add(0);
        }

        for (int j = 0; j < size1; j++) {
            for (int k = 0; k < size2; k++) {
                val1 = first.get(j);
                val2 = second.get(k);
                result.set(j+k, val1*val2 + result.get(j+k));
            }
        }

        return result;
    }

}
