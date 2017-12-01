package com.epam.courses.jf.practice.asgalantsev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask6;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

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
        HashMap<Integer, Integer> result = new HashMap<>();
        TreeMap<Integer, Integer> firstMap = new TreeMap<>(first);
        TreeMap<Integer, Integer> secondMap = new TreeMap<>(second);
        int maxKey = 0;
        if(firstMap.isEmpty() && secondMap.isEmpty())
            return result;
        if(firstMap.isEmpty())
            maxKey = secondMap.lastKey();
        else if(secondMap.isEmpty())
            maxKey = firstMap.lastKey();
        else
            maxKey = Math.max(firstMap.lastKey(), secondMap.lastKey());

        for (int i = 0; i <= maxKey; i++) {
            if (!firstMap.containsKey(new Integer(i)))
                firstMap.put(i, 0);
            if (!secondMap.containsKey(new Integer(i)))
                secondMap.put(i, 0);
            result.put(i, (firstMap.get(i) + secondMap.get(i)));
        }

        Iterator it = result.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) it.next();
            if(entry.getValue().intValue() == 0)
                it.remove();
        }

        return result;
    }
}
