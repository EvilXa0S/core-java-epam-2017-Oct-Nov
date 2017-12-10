package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.ITestableTask6;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task6 implements ITestableTask6 {
    @Override
    public HashMap<Integer, Integer> addPolynomials(HashMap<Integer, Integer> first, HashMap<Integer, Integer> second) {

        HashMap<Integer, Integer> finalResult = new HashMap<>();

        Map<Integer, Integer> result = Stream.concat(first.entrySet().stream(), second.entrySet()
                .stream()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));

        result.entrySet().forEach(entry -> finalResult.put(entry.getKey(), entry.getValue()));

        return finalResult;
    }
}
