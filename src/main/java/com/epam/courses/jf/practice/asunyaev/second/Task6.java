package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask6;

import java.util.HashMap;

public class Task6 implements ITestableTask6 {
    public HashMap<Integer, Integer> addPolynomials(HashMap<Integer, Integer> first, HashMap<Integer, Integer> second) {
        HashMap<Integer,Integer> result = new HashMap<>();
        for (HashMap.Entry<Integer, Integer> monom : first.entrySet()) {
            Integer currentKey = monom.getKey();
            if (second.containsKey(currentKey)) {
                result.put(currentKey, first.get(currentKey) + second.get(currentKey));
            } else {
                result.put(currentKey, first.get(currentKey));
            }
        }
        for (HashMap.Entry<Integer, Integer> monom : second.entrySet()) {
            Integer currentKey = monom.getKey();
            if (first.containsKey(currentKey)) {
                continue;
            } else {
                result.put(currentKey, second.get(currentKey));
            }
        }
        return result;
    }
}
