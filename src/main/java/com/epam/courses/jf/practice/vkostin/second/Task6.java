package com.epam.courses.jf.practice.vkostin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask6;

import java.util.HashMap;
import java.util.Map;

public class Task6 implements ITestableTask6 {

    // + put if absent method
    @Override
    public HashMap<Integer, Integer> addPolynomials(HashMap<Integer, Integer> first, HashMap<Integer, Integer> second) {
        HashMap<Integer,Integer> resultPoly = new HashMap<>();
        Integer currentKey;

        resultPoly.putAll(first);

        for (Map.Entry<Integer,Integer> pair : second.entrySet()) {
            currentKey = pair.getKey();

            if (null != resultPoly.putIfAbsent(currentKey, pair.getValue())) {
                resultPoly.put(currentKey,
                        pair.getValue() + resultPoly.get(currentKey));
            }
        }

        return resultPoly;
    }
}
