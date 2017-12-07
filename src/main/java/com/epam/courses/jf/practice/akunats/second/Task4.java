package com.epam.courses.jf.practice.akunats.second;

import com.epam.courses.jf.practice.common.second.ITestableTask4;

import java.util.HashSet;
import java.util.Set;

public class Task4 implements ITestableTask4 {

    public Set<Integer> intersection(Set<Integer> first, Set<Integer> second) {
        Set<Integer> result = new HashSet<>();
        for (Integer s : second) {
            for (Integer f : first) {
                if (f.equals(s)) {
                    result.add(f);
                }
            }
        }
        return result;
    }

    public Set<Integer> union(Set<Integer> first, Set<Integer> second) {
        Set<Integer> result = new HashSet<>();
        result.addAll(first);
        result.addAll(second);
        return result;
    }
}
