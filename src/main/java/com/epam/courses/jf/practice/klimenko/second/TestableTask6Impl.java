package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.ITestableTask6;

import java.util.HashMap;
import java.util.Map;

public class TestableTask6Impl implements ITestableTask6 {
    @Override
    public HashMap<Integer, Integer> addPolynomials(HashMap<Integer, Integer> first, HashMap<Integer, Integer> second) {
        HashMap<Integer, Integer> ret = new HashMap<>();
        ret.putAll(first);

        for (Map.Entry<Integer, Integer> powerValuePair : second.entrySet()) {
            Integer power = powerValuePair.getKey();
            Integer value = powerValuePair.getValue();

            ret.putIfAbsent(power, 0);
            ret.put(power, ret.get(power) + value);
        }

        return ret;
    }
}
