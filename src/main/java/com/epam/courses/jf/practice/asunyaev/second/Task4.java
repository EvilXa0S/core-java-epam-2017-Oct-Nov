package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask4;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Task4 implements ITestableTask4 {
    public Set<Integer> intersection(Set<Integer> first, Set<Integer> second) {
        Set<Integer> intersection = new HashSet<>();
        for (Integer number : first) {
            if (second.contains(number)) {
                intersection.add(number);
            }
        }
        return intersection;
    }

    public Set<Integer> union(Set<Integer> first, Set<Integer> second) {
        Set<Integer> union = new HashSet<>();
        union.addAll(first);
        union.addAll(second);
        return union;
    }
}
