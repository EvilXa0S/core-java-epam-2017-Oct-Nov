package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.ITestableTask7;

import java.util.ArrayList;
import java.util.List;


/**
 * Умножить два многочлена заданной степени, если коэффициенты многочленов хранятся в различных списках.
 * Элемент списка с индексом i соответствует коэффициенту i-й степени.
 * Степень результирующего многочлена равна сумме максимальных ненулевых степеней исходных многочленов.
 * Случай, когда многочлен вырождается в 0 описывается состоянием [0].
 */
public class Task7 implements ITestableTask7{


    /**
     * Осуществляет перемножение двух многочленов.
     *
     * @param first  Первый многочлен.
     * @param second Второй многочлен.
     * @return Многочлен, полученный в результате перемножения.
     */
    @Override
    public List<Integer> multiplyPolynomials(List<Integer> first, List<Integer> second) {

        List<Integer> list = new ArrayList<>();

        if (first.size() == 1 && first.get(0) == 0
                || second.size() == 1 && second.get(0) == 0) {
            list.add(0);
            return list;
        }

        for (int i = 0; i < first.size() + second.size() ; i++){
            list.add(0);
        }

        for (int i = 0; i < first.size(); i++){
            for (int x = 0; x < second.size(); x++){
                list.set(i + x, list.get(i + x) + first.get(i) * second.get(x));
            }
        }

        return list;
    }
}
