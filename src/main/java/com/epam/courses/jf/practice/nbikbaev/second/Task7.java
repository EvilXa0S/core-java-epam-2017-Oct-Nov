package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask7;

import java.util.*;

public class Task7 implements ITestableTask7 {
    @Override
    public List<Integer> multiplyPolynomials(List<Integer> first, List<Integer> second) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < first.size(); i++) {
            for (int j = 0; j < second.size(); j++) {
                int r = 0;
                if (map.containsKey(i + j)) {
                    r = map.get(i + j);
                }
                int sum = r + first.get(i) * second.get(j);
                map.put(i + j, sum);
            }
        }
        for (int i = 0; i < map.size(); i++) {
            result.add(map.get(i));
        }
        return result;
    }
}
