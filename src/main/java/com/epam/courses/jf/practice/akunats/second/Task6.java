package com.epam.courses.jf.practice.akunats.second;

import com.epam.courses.jf.practice.common.second.ITestableTask6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Task6 implements ITestableTask6 {
    public HashMap<Integer, Integer> addPolynomials(HashMap<Integer, Integer> first, HashMap<Integer, Integer> second) {
        HashMap<Integer, Integer> result = new HashMap<>();
        second.keySet().stream()
                .filter(i -> first.containsKey(second.get(i)) && second.get(i) != null && first.get(i) != null)
                .forEach(i -> result.put(i, first.get(i) + second.get(i)));
        for (Integer key : result.keySet()) {
            first.remove(key);
            second.remove(key);
        }
        result.putAll(first);
        result.putAll(second);
        return result;
    }
}