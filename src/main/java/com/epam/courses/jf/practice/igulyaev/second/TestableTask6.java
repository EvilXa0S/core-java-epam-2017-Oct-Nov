package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask6;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class TestableTask6 implements ITestableTask6 {
    @Override
    public HashMap<Integer, Integer> addPolynomials(HashMap<Integer, Integer> first, HashMap<Integer, Integer> second) {
        int n  = Math.max(first.keySet().stream().max(Integer::compareTo).orElse(0),
                second.keySet().stream().max(Integer::compareTo).orElse(0));
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i <= n; ++i){
            final int k = i;
            Stream.of(first.get(i), second.get(i))
                    .filter(Objects::nonNull)
                    .reduce((i1, i2) -> i1 + i2)
                    .ifPresent(v -> map.put(k, v));
        }
        return (HashMap<Integer,Integer>)map;
    }
}