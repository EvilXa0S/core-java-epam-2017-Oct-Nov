package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.ITestableTask4;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Task4 implements ITestableTask4 {

    @Override
    public Set<Integer> intersection(Set<Integer> first, Set<Integer> second) {
        if(first.containsAll(second)){
            return first;
        }
        Set<Integer> resultSet = first.stream().filter(e -> second.contains(e)).collect(Collectors.toSet());

        return resultSet;
    }

    @Override
    public Set<Integer> union(Set<Integer> first, Set<Integer> second) {

        Set<Integer> resultSet = new HashSet<>();

        if(first.containsAll(second)){
            return first;
        }

        resultSet.addAll(first);
        resultSet.addAll(second);

        return resultSet;
    }
}
