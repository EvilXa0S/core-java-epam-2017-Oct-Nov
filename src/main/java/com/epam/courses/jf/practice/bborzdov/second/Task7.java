package com.epam.courses.jf.practice.bborzdov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask7;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bogdan on 17.11.17.
 */

/**
 *  Умножить два многочлена заданной степени, если коэффициенты многочленов хранятся в различных списках.
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
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < first.size() + second.size(); i++) {
            result.add(0);
        }
        for (int i = 0; i < first.size(); i++) {
            for (int j = 0; j < second.size(); j++) {
                if(result.get(i+j)!=0){
                    result.set(i + j, result.get(i+j) + (first.get(i) * second.get(j)));
                }else {
                    result.set(i + j, first.get(i) * second.get(j));
                }
            }
        }
        return result;
    }
}
