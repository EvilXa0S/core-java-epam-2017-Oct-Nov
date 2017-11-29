package com.epam.courses.jf.practice.asgalantsev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask7;

import java.util.ArrayList;
import java.util.List;

/**
 * Умножить два многочлена заданной степени, если коэффициенты многочленов хранятся в различных списках.
 * Элемент списка с индексом i соответствует коэффициенту i-й степени.
 */

public class Task7 implements ITestableTask7 {

    /**
     * Осуществляет перемножение двух многочленов.
     * @param first Первый многочлен.
     * @param second Второй многочлен.
     * @return Многочлен, полученный в результате перемножения.
     */

    @Override
    public List<Integer> multiplyPolynomials(List<Integer> first, List<Integer> second) {
        int size = first.size() * second.size();
        List<Integer> result = new ArrayList<>(size);
        for(int i=0; i < size; i++)
            result.add(0);

        for(int i=0; i < first.size(); i++) {
            for(int j=0; j < second.size(); j++) {
                int number = first.get(i) * second.get(j);
                int numberInResultList = result.get(i + j);
                result.set(i + j, number + numberInResultList);
            }
        }
        return result;
    }
}
