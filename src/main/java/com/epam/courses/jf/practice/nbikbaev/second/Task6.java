package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask6;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Task6 implements ITestableTask6 {
    @Override
    public HashMap<Integer, Integer> addPolynomials(HashMap<Integer, Integer> first, HashMap<Integer, Integer> second) {
        Set<Integer> coefficientsUnion = new HashSet<>();
        coefficientsUnion.addAll(first.keySet());
        coefficientsUnion.addAll(second.keySet());
        HashMap<Integer, Integer> result = new HashMap<>();
        int sum;
        for (int i : coefficientsUnion) {
            sum = 0;
            if (first.containsKey(i)) {
                sum += first.get(i);
            }
            if (second.containsKey(i)) {
                sum += second.get(i);
            }
            result.put(i, sum);
        }
        return result;
    }

}
