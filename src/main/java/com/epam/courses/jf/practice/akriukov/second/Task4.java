package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask4;

import java.util.HashSet;
import java.util.Set;

public class Task4 implements ITestableTask4{

    @Override
    public Set<Integer> intersection(Set<Integer> first, Set<Integer> second) {
        Set<Integer> firstCopy = new HashSet<>(first);
        firstCopy.retainAll(second);
        return firstCopy;
    }

    @Override
    public Set<Integer> union(Set<Integer> first, Set<Integer> second) {
        Set<Integer> firstCopy = new HashSet<>(first);
        firstCopy.addAll(second);
        return firstCopy;
    }
}
