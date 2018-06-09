package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.ITestableTask4;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TestableTask4Impl implements ITestableTask4 {
    @Override
    public Set<Integer> intersection(Set<Integer> first, Set<Integer> second) {
        return first.stream().filter(second::contains).collect(Collectors.toSet());
    }

    @Override
    public Set<Integer> union(Set<Integer> first, Set<Integer> second) {
        Set<Integer> ret = new HashSet<>();
        ret.addAll(first);
        ret.addAll(second);
        return ret;
    }
}
