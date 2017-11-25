package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask6;

import java.util.HashMap;
import java.util.Map;

public class Task6 implements ITestableTask6{

    @Override
    public HashMap<Integer, Integer> addPolynomials(HashMap<Integer, Integer> first, HashMap<Integer, Integer> second) {

        HashMap<Integer, Integer> result = new HashMap<>(first);

        for (Map.Entry<Integer, Integer> entry : second.entrySet()) {
            if (result.containsKey(entry.getKey())) {
                int sum = result.get(entry.getKey()) + entry.getValue();
                result.put(entry.getKey(), sum);
            } else {
                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }
}
