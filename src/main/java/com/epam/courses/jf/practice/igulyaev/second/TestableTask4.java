package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask4;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TestableTask4 implements ITestableTask4 {
    @Override
    public Set<Integer> intersection(Set<Integer> first, Set<Integer> second) {
        Set<Integer> integerSet = new HashSet<>();
        for(Integer i : first){
            if(second.contains(i)){
                integerSet.add(i);
            }
        }
        return integerSet;
    }

    @Override
    public Set<Integer> union(Set<Integer> first, Set<Integer> second) {
        Set<Integer> integerSet = new HashSet<>();
        integerSet.addAll(first);
        integerSet.addAll(second);
        return integerSet;
    }
}
