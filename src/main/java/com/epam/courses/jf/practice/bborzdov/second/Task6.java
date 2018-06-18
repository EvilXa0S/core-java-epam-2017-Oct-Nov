package com.epam.courses.jf.practice.bborzdov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask6;

import java.util.HashMap;
import java.util.Set;


/**
 * Created by bogdan on 17.11.17.
 */

/**
 * Сложить два многочлена, если коэффициенты многочленов хранятся в объекте HashMap.
 * Степень элемента многочлена – ключ, коэффициент элемента многочлена – значение.
 * Коэффициенты многочления, ключи для которых отсутствуют в карте, равны нулю.
 */
public class Task6 implements ITestableTask6 {
    /**
     * Осуществляет сложение двух многочленов.
     * @param first Первый многочлен.
     * @param second Второй многочлен.
     * @return Многочлен, полученный в результате сложения.
     */
    @Override
    public HashMap<Integer, Integer> addPolynomials(HashMap<Integer, Integer> first, HashMap<Integer, Integer> second) {
        Set<Integer> firstKeys = first.keySet();
        for (Integer firstKey : firstKeys) {
            if(second.containsKey(firstKey)){
                second.put(firstKey, second.get(firstKey) + first.get(firstKey));
            } else{
                second.put(firstKey,first.get(firstKey));
            }
        }
        return second;
    }
}
